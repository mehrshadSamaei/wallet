package com.example.wallet.dto;

import com.example.wallet.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BankDto extends BaseDto {
    private Long id;
    private Double amount;
}
