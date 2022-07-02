package com.sms_sys.business.impl;

import com.sms_sys.authentication.UserDetailsImpl;
import com.sms_sys.database.entity.User;
import com.sms_sys.database.repo.UserRepository;
import com.sms_sys.exception.LogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailBusinessImpl implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserBusinessImpl.class);

    @Autowired
    private UserRepository userRepository;
    /*
    * @author: KuBon
    * @function:loadUserByUsername
    * @request: username
    * @response: UserDetailsImpl
    * @since:  5/6/2022 10:00 AM
    * @description:  Override method in class UserDetailBusiness
    * @update:
    *
    * */
    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("USERNAME :"+username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new LogicException("can.not.found.username"));

        return UserDetailsImpl.build(user);
    }
}
