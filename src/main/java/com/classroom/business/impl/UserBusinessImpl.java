package com.classroom.business.impl;

import com.classroom.authentication.UserDetailsImpl;
import com.classroom.business.UserBusiness;
import com.classroom.database.bo.AccessTokenBO;
import com.classroom.database.dto.UserDTO;
import com.classroom.database.entity.Role;
import com.classroom.database.entity.User;
import com.classroom.database.repo.RoleRepository;
import com.classroom.database.repo.UserRepository;
import com.classroom.exception.LogicException;
import com.classroom.jwt.JwtUtils;
import com.classroom.service.response.BaseResponse;
import com.classroom.service.response.ReCaptchaResponse;
import com.classroom.service.response.UserResponse;
import com.classroom.utils.CommonUtils;
import com.classroom.utils.Constant;
import com.classroom.utils.DataUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/*
* @author: KuBon
* @since:  3/31/2022 9:23 AM
* @description:  Class process user logic
* @update:
*
* */
@Service
public class UserBusinessImpl implements UserBusiness {

    private final Logger log = LoggerFactory.getLogger(UserBusinessImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    /*
    * @author: KuBon
    * @function: registerUser
    * @request: UserDTO, String
    * @response: UserDTO
    * @since:  3/31/2022 9:28 AM
    * @description: function register new user
    * @update:
    *
    * */
    @Override
    public BaseResponse<UserResponse> registerUser(UserDTO userDTO, String captcha, String language)
            throws LogicException {

        if(DataUtils.isNullObject(userDTO)){
            log.debug("--------INPUT INVALID REGISTER USER--------");
            throw new LogicException("input.invalid");
        }

        if(!validateCaptcha(captcha)){
            log.debug("--------INVALID CAPTHCA--------");
            throw new LogicException("invalid.captcha");
        }

        if(DataUtils.isNullOrEmpty(userDTO.getUsername()) || DataUtils.isNullOrEmpty(userDTO.getPassword())){
            log.debug("--------INPUT INVALID REGISTER USER--------");
            throw new LogicException("err.username.or.password.can.not.empty.or.null");
        }

        if(!CommonUtils.checkUsernameCharacters(userDTO.getUsername())) {
            log.debug("--------USERNAME NOT RIGHT FORMAT--------");
            throw new LogicException("err.username.not.right.format");
        }

        if(!CommonUtils.checkPasswordPolicy(userDTO.getPassword())){
            log.debug("--------PASSWORD NOT RIGHT FORMAT--------");
            throw new LogicException("err.password.not.right.format");
        }

        if(!CommonUtils.checkEmail(userDTO.getEmail())){
            log.debug("--------EMAIL NOT RIGHT FORMAT--------");
            throw new LogicException("err.email.not.right.format");
        }

        if(!CommonUtils.checkPhoneNumber(userDTO.getPhone())){
            log.debug("--------PHONENUMBER NOT RIGHT FORMAT--------");
            throw new LogicException("err.phone.not.right.format");
        }

        // get by username check it already exist or no
        Optional<User> lstUser = userRepository.getAllByUsername(userDTO.getUsername());
        if(lstUser.isPresent()){
            throw new LogicException("err.username.already.exist");
        }

        //unique phone and email
        Optional<User> lstUserPhone = userRepository.getAllByPhone(userDTO.getPhone());
        if(lstUserPhone.isPresent()){
            throw new LogicException("err.phone.already.exist");
        }

        Optional<User> lstUserEamil = userRepository.getAllByEmail(userDTO.getEmail());
        if(lstUserEamil.isPresent()){
            throw new LogicException("err.email.already.exist");
        }

        //get Role for normal user
        Optional<Role> role = roleRepository.findAllByRoleName(Constant.ROLE.ROLE_CODE_USER);
        if(!role.isPresent()){
            throw new LogicException("err.role.does.not.exist");
        }

        // convert from dto -> entity
        User user = modelMapper.map(userDTO,User.class);
        user.getRoles().add(role.get());
        user.setExpiredTimePassword(new Date(new Date().getTime()+Constant.TIMES.expiredTimePassword));
        userRepository.save(user);
        //set response
        UserResponse userResponse = UserResponse.builder()
                .userDTO(userDTO)
                .build();
        return BaseResponse.ok(userResponse).build();
    }

    @Override
    public BaseResponse<AccessTokenBO> getToken(UserDTO user, String language) throws LogicException {

        if(DataUtils.isNullObject(user)){
            log.debug("--------INPUT INVALID REGISTER USER--------");
            throw new LogicException("input.invalid");
        }

        if(!CommonUtils.checkUsernameCharacters(user.getUsername())) {
            log.debug("--------USERNAME NOT RIGHT FORMAT--------");
            throw new LogicException("err.username.not.right.format");
        }

        if(!CommonUtils.checkPasswordPolicy(user.getPassword())){
            log.debug("--------PASSWORD NOT RIGHT FORMAT--------");
            throw new LogicException("err.password.not.right.format");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());
        claims.put("roles",roles);
        String jwt = jwtUtils.generateJwtToken(userDetails,claims);
        return BaseResponse.ok(new AccessTokenBO(jwt)).build();
    }

    public boolean validateCaptcha(String captcha) {
        String params = "?secret={secret}&response={response}";
        String url = Constant.RE_CAPTCHA.URL+params;
        URI expanded = new UriTemplate(url).expand(Constant.RE_CAPTCHA.SECRET,
                captcha); // this is what RestTemplate uses
        ReCaptchaResponse captchaResponse = restTemplate.exchange(expanded,
                HttpMethod.POST,null,ReCaptchaResponse.class).getBody();
        //check success ReCaptchaResponse
        if(captchaResponse.isSuccess()){
            return true;
        }else{
            return false;
        }
    }
}
