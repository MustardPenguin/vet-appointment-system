package com.vet.appointment.system.account.service.application.impl;

import com.vet.appointment.system.account.service.application.dto.AccountLoginRequest;
import com.vet.appointment.system.account.service.application.dto.AccountLoginResponse;
import com.vet.appointment.system.account.service.application.dto.UserDetailsDto;
import com.vet.appointment.system.account.service.application.security.AuthenticationService;
import com.vet.appointment.system.account.service.application.security.jwt.JwtTokenService;
import com.vet.appointment.system.account.service.dataaccess.account.entity.AccountEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenService jwtTokenService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     UserDetailsServiceImpl userDetailsService,
                                     JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
    }


    @Override
    public AccountLoginResponse authenticate(AccountLoginRequest accountLoginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            accountLoginRequest.getEmail(),
                            accountLoginRequest.getPassword()));
        } catch(AuthenticationException e) {
            return new AccountLoginResponse("Invalid credentials!", "");
        }

        UserDetailsDto userDetails = userDetailsService.loadUserByUsername(accountLoginRequest.getEmail());
        Map<String, String> claims = new HashMap<>();
        claims.put("AccountId", userDetails.getAccountId().toString());

//        String jwtToken = jwtTokenService.generateToken(userDetails);
        String jwtToken = jwtTokenService.generateToken(claims, userDetails);

        return new AccountLoginResponse("Successfully authenticated!", jwtToken);
    }
}
