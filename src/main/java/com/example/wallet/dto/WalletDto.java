package com.example.wallet.dto;

import com.example.wallet.base.BaseDto;
import com.example.wallet.entity.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class WalletDto extends BaseDto {
    private Long id;
    @NotBlank
    @Size(min = 2 , max = 50)
    private String name;
    private Long walletNumber;
    private Status status;
    @Size(max = 100)
    private String description;
//    @Min(1)
    @Range(min = 0)
    private Double currentBalance;
}
