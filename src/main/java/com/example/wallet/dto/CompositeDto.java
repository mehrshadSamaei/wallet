package com.example.wallet.dto;

import com.example.wallet.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompositeDto extends BaseDto {
    private Long sourceWalletId;
    private Long destinationWalletId;
    private Double amount;
    private Long bankId;
}
