package com.examonline.business.impl;

import com.examonline.business.UserBusiness;
import com.examonline.database.dto.UserDTO;
import com.examonline.database.entity.Role;
import com.examonline.database.entity.User;
import com.examonline.database.entity.UserRole;
import com.examonline.database.repo.RoleRepository;
import com.examonline.database.repo.UserRepository;
import com.examonline.exception.LogicException;
import com.examonline.service.response.BaseResponse;
import com.examonline.service.response.UserResponse;
import com.examonline.util.Constant;
import com.examonline.util.DataUtils;
import com.examonline.util.DicHelper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    private final DicHelper dicHelper;

    @Autowired
    public UserBusinessImpl(DicHelper dicHelper) {
        this.dicHelper = dicHelper;
    }

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
    public BaseResponse<UserResponse> registerUser(UserDTO userDTO, String language) throws LogicException {
        if(DataUtils.isNullObject(userDTO)){
            log.debug("--------INPUT INVALID REGISTER USER--------");
            throw new LogicException(
                    dicHelper.getMessage("input.invalid",language)
            );
        }

        if(DataUtils.isNullOrEmpty(userDTO.getUsername()) || DataUtils.isNullOrEmpty(userDTO.getPassword())){
            log.debug("--------INPUT INVALID REGISTER USER--------");
            throw new LogicException(
                    dicHelper.getMessage("err.usernam.or.password.can.not.empty.or.null",language)
            );
        }

        // get by username check it already exist or no
        Optional<User> lstUser = Optional.ofNullable(userRepository.getAllByUsername(userDTO.getUsername())
                .orElseThrow(() -> new LogicException(
                        dicHelper.getMessage("err.can.not.get.username", language))));

        if(lstUser.isPresent()){
            throw  new LogicException(dicHelper.getMessage("username.already.exist",language));
        }
        //get Role for normal user
        Optional<Role> role = Optional.ofNullable(roleRepository.findAllByRoleName(Constant.ROLE.ROLE_CODE_USER)
                .orElseThrow(() -> new LogicException(
                        dicHelper.getMessage("err.can.not.get.role", language))));
        if(!role.isPresent()){
            throw new LogicException(dicHelper.getMessage("err.role.does.not.exist",language));
        }
        User user = modelMapper.map(userDTO,User.class);
        //default role
        UserRole userRole = UserRole.builder()
                .role(role.get()).user(user).build();
        // convert from dto -> entity
        user.getUserRoles().add(userRole);
        //set response
        UserResponse userResponse = UserResponse.builder()
                .userDTO(userDTO)
                .build();

        return new BaseResponse<UserResponse>(userResponse, HttpStatus.CREATED,
                dicHelper.getMessage("create.user.successfully",language)
        );
    }
}
