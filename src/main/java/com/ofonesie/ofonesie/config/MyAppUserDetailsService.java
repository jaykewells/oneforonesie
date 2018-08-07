package com.ofonesie.ofonesie.config;

import com.ofonesie.ofonesie.models.UserInfo;
import com.ofonesie.ofonesie.models.data.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class MyAppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        UserInfo activeUserInfo = userInfoDAO.findByUsername(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
        UserDetails userDetails = new User(activeUserInfo.getUsername(),
                activeUserInfo.getPassword(), Arrays.asList(authority));
        return userDetails;
    }


}
