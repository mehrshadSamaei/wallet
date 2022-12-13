package com.example.wallet.mapper;

import com.example.wallet.base.BaseMapper;
import com.example.wallet.dto.TransactionDto;
import com.example.wallet.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper extends BaseMapper<Transaction , TransactionDto> {
}
