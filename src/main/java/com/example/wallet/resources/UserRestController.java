package com.example.wallet.resources;

import com.example.wallet.base.BaseRestController;
import com.example.wallet.dto.UserDto;
import com.example.wallet.entity.User;
import com.example.wallet.mapper.UserMapper;
import com.example.wallet.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserRestController extends BaseRestController<
        UserDto , User, UserMapper , UserService , Long> {
    public UserRestController(UserService service, UserMapper mapper) {
        super(service, mapper);
    }
    @PostMapping
    public ResponseEntity<UserDto> add(@Valid @RequestBody UserDto userDto) {
        User user = mapper.convertDToE(userDto);
        return ResponseEntity.ok(
                mapper.convertEToD(
                        service.saveOrUpdate(user)
                )
        );
    }
}
