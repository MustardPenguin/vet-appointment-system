package com.vet.appointment.system.appointment.service.dataaccess.account.adapter;

import com.vet.appointment.system.appointment.service.dataaccess.account.entity.AccountEntity;
import com.vet.appointment.system.appointment.service.dataaccess.account.repository.AccountJpaRepository;
import com.vet.appointment.system.appointment.service.domain.dto.message.AccountModel;
import com.vet.appointment.system.appointment.service.domain.ports.output.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    public AccountRepositoryImpl(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public AccountModel save(AccountModel accountModel) {
        AccountEntity accountEntity = accountJpaRepository.save(
                new AccountEntity(
                        UUID.fromString(accountModel.getId()),
                        accountModel.getEmail(),
                        accountModel.getFirstName(),
                        accountModel.getLastName()));
        return new AccountModel(
                accountEntity.getId().toString(),
                accountEntity.getEmail(),
                accountEntity.getFirstName(),
                accountEntity.getLastName());
    }

    @Override
    public Optional<AccountModel> findById(UUID id) {
        return accountJpaRepository.findById(id)
                .map(accountEntity -> new AccountModel(
                        accountEntity.getId().toString(),
                        accountEntity.getEmail(),
                        accountEntity.getFirstName(),
                        accountEntity.getLastName()));
    }
}
