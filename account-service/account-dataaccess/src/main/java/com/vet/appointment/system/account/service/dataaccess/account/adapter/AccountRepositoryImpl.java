package com.vet.appointment.system.account.service.dataaccess.account.adapter;

import com.vet.appointment.service.account.service.domain.entity.Account;
import com.vet.appointment.system.account.service.dataaccess.account.entity.AccountEntity;
import com.vet.appointment.system.account.service.dataaccess.account.mapper.AccountDataAccessMapper;
import com.vet.appointment.system.account.service.dataaccess.account.repository.AccountJpaRepository;
import com.vet.appointment.system.account.service.domain.ports.output.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;
    private final AccountDataAccessMapper accountDataAccessMapper;

    public AccountRepositoryImpl(AccountJpaRepository accountJpaRepository,
                                 AccountDataAccessMapper accountDataAccessMapper) {
        this.accountJpaRepository = accountJpaRepository;
        this.accountDataAccessMapper = accountDataAccessMapper;
    }


    @Override
    public Account registerAccount(Account account) {
        AccountEntity accountEntity = accountJpaRepository.save(
                accountDataAccessMapper.accountToAccountEntity(account));
        return accountDataAccessMapper.accountEntityToAccount(accountEntity);
    }
}
