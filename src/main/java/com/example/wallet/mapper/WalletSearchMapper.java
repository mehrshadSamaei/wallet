package com.example.wallet.mapper;

import com.example.wallet.base.BaseMapper;
import com.example.wallet.dto.WalletDtoSearch;
import com.example.wallet.entity.Wallet;
import org.mapstruct.Mapper;

@Mapper
public interface WalletSearchMapper extends BaseMapper<Wallet , WalletDtoSearch> {
}
