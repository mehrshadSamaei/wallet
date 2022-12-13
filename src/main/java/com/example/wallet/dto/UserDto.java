package com.example.wallet.dto;

import com.example.wallet.base.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
public class UserDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Set<WalletDto> wallets;
    private Set<BankDto> banks;
}
