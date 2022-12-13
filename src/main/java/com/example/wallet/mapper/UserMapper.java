package com.example.wallet.mapper;

import com.example.wallet.base.BaseMapper;
import com.example.wallet.dto.UserDto;
import com.example.wallet.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDto> {
}
