package com.helloworld.learn.app.services;

import java.util.ArrayList;
import java.util.UUID;

import com.helloworld.learn.app.dao.UserDao;
import com.helloworld.learn.app.models.user.DAOUser;
import com.helloworld.learn.app.models.user.UserDTO;
import com.helloworld.learn.app.models.user.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DAOUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public DAOUser findUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }

    public DAOUser findUserByUuid(String uuid) throws UsernameNotFoundException {
        return userDao.findByUsername(uuid);
    }

    public DAOUser save(UserDTO user) {
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(this.bcryptEncoder.encode(user.getPassword()));
        newUser.setRole(UserRoles.USER);
        newUser.setUuid(UUID.randomUUID().toString());
        return userDao.save(newUser);
    }
}