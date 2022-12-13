package com.example.wallet.mapper;

import com.example.wallet.base.BaseMapper;
import com.example.wallet.dto.BankDto;
import com.example.wallet.entity.Bank;
import org.mapstruct.Mapper;

@Mapper
public interface BankMapper extends BaseMapper<Bank , BankDto> {
}
