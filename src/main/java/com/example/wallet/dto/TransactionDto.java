package com.example.wallet.dto;

import com.example.wallet.base.BaseDto;
import com.example.wallet.entity.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDto extends BaseDto {
    private Long id;
    private Type type;
    private WalletDto wallet;
    private BankDto bank;
}
