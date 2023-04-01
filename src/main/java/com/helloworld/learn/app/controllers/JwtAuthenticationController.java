package com.helloworld.learn.app.controllers;

import com.helloworld.learn.app.models.auth.LoginRequest;
import com.helloworld.learn.app.models.user.DAOUser;
import com.helloworld.learn.app.models.user.UserDTO;
import com.helloworld.learn.app.models.user.UserRoles;
import com.helloworld.learn.app.responses.AccessTokenResponse;
import com.helloworld.learn.app.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.helloworld.learn.app.services.JwtUserDetailsService;


import com.helloworld.learn.app.config.JwtTokenUtil;
import com.helloworld.learn.app.models.auth.JwtRequest;
import com.helloworld.learn.app.models.auth.JwtResponse;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManagerBuilder authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private EncryptionService encryptionService;

    @CrossOrigin
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        int tokenLength = authenticationRequest.getAccessToken().length() - 24;
        String accessToken = authenticationRequest.getAccessToken().substring(0,tokenLength);
        String encodedIv = authenticationRequest.getAccessToken().substring(tokenLength);

        IvParameterSpec otherIv = new IvParameterSpec(Base64.getDecoder().decode(encodedIv));
        SecretKey key = encryptionService.getKeyFromPassword(authenticationRequest.getUserCode());

        String[] decryptedToken = encryptionService.decrypt(accessToken, key, otherIv).split(";");

        String username = decryptedToken[0];
        String password = decryptedToken[1];

        authenticate(username, password);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        //creates 'access' token and returns it to front end
        IvParameterSpec iv = encryptionService.generateIv();
        SecretKey key = encryptionService.getKeyFromPassword(authenticationRequest.getUsername().substring(0,4));

        String dualKey =  authenticationRequest.getUsername() + ";" + authenticationRequest.getPassword();

        String accessToken = encryptionService.encrypt(dualKey, key, iv);
        String encodedIv = new String(Base64.getEncoder().encode(iv.getIV()), StandardCharsets.UTF_8);
        accessToken += encodedIv;

        System.out.println(accessToken);
        final DAOUser user = userDetailsService.findUserByUsername(authenticationRequest.getUsername());

        return ResponseEntity.ok(new AccessTokenResponse(accessToken, user.getUuid()));
    }

    @CrossOrigin
    @RequestMapping(value = "/admin/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateAdmin(@RequestBody JwtRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok("success");
//        String accessToken = authenticationRequest.getAccessToken().substring(0,24);
//        String encodedIv = authenticationRequest.getAccessToken().substring(24);
//
//        IvParameterSpec otherIv = new IvParameterSpec(Base64.getDecoder().decode(encodedIv));
//        SecretKey key = EncryptionService.getKeyFromPassword(authenticationRequest.getUserCode());
//
//        String[] decryptedToken = EncryptionService.decrypt(accessToken, key, otherIv).split(";");
//
//        String username = decryptedToken[0];
//
//        DAOUser user = userDetailsService.findUserByUsername(username);
//        if(user.getRole() == UserRoles.ADMIN){
//            return ResponseEntity.ok("Success");
//        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        if(userDetailsService.findUserByUsername(user.getUsername()) == null) {
            return ResponseEntity.ok(userDetailsService.save(user));
        }

        return (ResponseEntity<?>) ResponseEntity.badRequest();
    }



    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.getObject().authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
