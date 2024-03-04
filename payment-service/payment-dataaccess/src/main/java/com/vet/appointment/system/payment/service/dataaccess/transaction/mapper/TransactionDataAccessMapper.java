package com.vet.appointment.system.payment.service.dataaccess.transaction.mapper;

import com.vet.appointment.system.payment.service.dataaccess.transaction.entity.TransactionEntity;
import com.vet.appointment.system.payment.service.domain.dto.model.TransactionModel;
import org.springframework.stereotype.Component;

@Component
public class TransactionDataAccessMapper {

    public TransactionEntity transactionModelToEntity(TransactionModel transactionModel) {
        return TransactionEntity.builder()
                .accountId(transactionModel.getAccountId())
                .createdAt(transactionModel.getCreatedAt())
                .reason(transactionModel.getReason())
                .cost(transactionModel.getCost())
                .id(transactionModel.getId())
                .build();
    }

    public TransactionModel transactionEntityToModel(TransactionEntity transactionEntity) {
        return TransactionModel.builder()
                .accountId(transactionEntity.getAccountId())
                .createdAt(transactionEntity.getCreatedAt())
                .reason(transactionEntity.getReason())
                .cost(transactionEntity.getCost())
                .id(transactionEntity.getId())
                .build();
    }
}
