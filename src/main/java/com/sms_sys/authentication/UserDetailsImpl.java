package com.sms_sys.authentication;

import com.sms_sys.database.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/*
* @author: KuBon
* @Class: UserDetailsImpl
* @since:  5/6/2022 9:26 AM
* @description:  User Details for security
* @update:
*
* */
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    /*
    * @author: KuBon
    * @function: build
    * @request: User
    * @response: UserDetails
    * @since:  5/6/2022 9:27 AM
    * @description: build UserDetails
    * @update:
    *
    * */
    public static UserDetailsImpl build(User user){
//        System.out.println(user.getRoles().toString());
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
            r.getPermissions().forEach(p -> {
//                System.out.println(p.toString());
                authorities.add(new SimpleGrantedAuthority(p.getName()));
            });
        });
        return new UserDetailsImpl(user.getId(),user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
