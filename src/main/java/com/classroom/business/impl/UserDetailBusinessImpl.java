package com.classroom.business.impl;

import com.classroom.authentication.UserDetailsImpl;
import com.classroom.database.entity.User;
import com.classroom.database.repo.UserRepository;
import com.classroom.exception.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailBusinessImpl implements UserDetailsService {
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
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new LogicException("can.not.found.user"));
        return UserDetailsImpl.build(user);
    }
}
