package com.vet.appointment.system.payment.service.dataaccess.transaction.adapter;

import com.vet.appointment.system.payment.service.dataaccess.transaction.entity.TransactionEntity;
import com.vet.appointment.system.payment.service.dataaccess.transaction.mapper.TransactionDataAccessMapper;
import com.vet.appointment.system.payment.service.dataaccess.transaction.repository.TransactionJpaRepository;
import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import com.vet.appointment.system.payment.service.domain.ports.output.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionDataAccessMapper transactionDataAccessMapper;
    private final TransactionJpaRepository transactionJpaRepository;

    public TransactionRepositoryImpl(TransactionDataAccessMapper transactionDataAccessMapper,
                                     TransactionJpaRepository transactionJpaRepository) {
        this.transactionDataAccessMapper = transactionDataAccessMapper;
        this.transactionJpaRepository = transactionJpaRepository;
    }

    @Override
    public TransactionModel save(TransactionModel transactionModel) {
        try {
            TransactionEntity response = transactionJpaRepository.save(
                    transactionDataAccessMapper.transactionModelToEntity(transactionModel));
            return transactionDataAccessMapper.transactionEntityToModel(response);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TransactionModel findById(UUID id) {
        Optional<TransactionEntity> response = transactionJpaRepository.findById(id);
        if(response.isEmpty()) {
            return null;
        }
        return transactionDataAccessMapper.transactionEntityToModel(response.get());
    }

    @Override
    public List<TransactionModel> findTransactionsByAccountId(UUID accountId) {
        return transactionJpaRepository.findTransactionEntitiesByAccountId(accountId)
                .stream().map(transactionDataAccessMapper::transactionEntityToModel).collect(Collectors.toList());
    }
}
