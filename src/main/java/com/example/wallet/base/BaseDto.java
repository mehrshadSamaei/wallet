package com.example.wallet.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public abstract class BaseDto {
    private Date createDate;
    private Date updateDate;
}
