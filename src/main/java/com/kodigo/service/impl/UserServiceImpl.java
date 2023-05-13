package com.kodigo.service.impl;

import com.kodigo.model.Client;
import com.kodigo.repo.IClientRepo;
import com.kodigo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private IClientRepo iClientRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Client client = iClientRepo.findOneByUserClient(username);

        if (client == null) {
            throw new UsernameNotFoundException(String.format(Constant.UNREGISTER_USER, username));
        }

        List<GrantedAuthority> roles = new ArrayList<>(); //roles
        UserDetails userDetails = new User(client.getUserClient(), client.getPasswordClient(), roles);

        return userDetails;
    }

}
