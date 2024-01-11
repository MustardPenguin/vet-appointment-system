package com.vet.appointment.system.account.service.dataaccess.account.mapper;

import com.vet.appointment.service.account.service.domain.entity.Account;
import com.vet.appointment.system.account.service.dataaccess.account.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountDataAccessMapper {
    public AccountEntity accountToAccountEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId().getValue())
                .email(account.getEmail())
                .password(account.getPassword())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .build();
    }

    public Account accountEntityToAccount(AccountEntity account) {
        return Account.builder()
                .email(account.getEmail())
                .password(account.getPassword())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .build();
    }
}
