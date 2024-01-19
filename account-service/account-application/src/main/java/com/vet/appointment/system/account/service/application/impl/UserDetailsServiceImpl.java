package com.vet.appointment.system.account.service.application.impl;

import com.vet.appointment.system.account.service.application.dto.UserDetailsDto;
import com.vet.appointment.system.account.service.dataaccess.account.entity.AccountEntity;
import com.vet.appointment.system.account.service.dataaccess.account.repository.AccountJpaRepository;
import com.vet.appointment.system.account.service.domain.ports.output.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountJpaRepository accountJpaRepository;

    public UserDetailsServiceImpl(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public UserDetailsDto loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountJpaRepository.findByEmail(email).map(account ->
                new UserDetailsDto(account.getId(), account.getEmail(), account.getPassword())
        ).orElseThrow(() -> new UsernameNotFoundException("Email of " + email + " not found!"));
    }
}
