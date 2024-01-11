package com.vet.appointment.system.account.service.domain.ports.output.repository;

import com.vet.appointment.service.account.service.domain.entity.Account;
import org.springframework.stereotype.Repository;

public interface AccountRepository {
    Account registerAccount(Account account);
}
