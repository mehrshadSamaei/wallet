package com.example.wallet.dto;

import com.example.wallet.base.BaseDto;
import com.example.wallet.entity.enums.Status;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class WalletDtoSearch extends BaseDto {
    private Long id;
    private String name;
    private Status status;
    private String description;
    private Double currentBalance;
}
