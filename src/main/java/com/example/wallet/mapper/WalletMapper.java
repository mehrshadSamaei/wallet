package com.example.wallet.mapper;

import com.example.wallet.base.BaseMapper;
import com.example.wallet.dto.WalletDto;
import com.example.wallet.entity.Wallet;
import org.mapstruct.Mapper;

@Mapper
public interface WalletMapper extends BaseMapper<Wallet , WalletDto> {
}
