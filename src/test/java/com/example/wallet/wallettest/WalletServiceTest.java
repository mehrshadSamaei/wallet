package com.example.wallet.wallettest;

import com.example.wallet.WalletApplication;
import com.example.wallet.entity.User;
import com.example.wallet.entity.Wallet;
import com.example.wallet.exception.NotFoundException;
import com.example.wallet.service.UserService;
import com.example.wallet.service.WalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletApplication.class)
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;

    @Test
    public void should_When_Save_Wallet_Return_Entity() {

        var user = User.builder().id(1L).firstName("merhshad").lastName("samaei")
                .username("mm7474").password("123456789").build();
        user = userService.saveOrUpdate(user);
        var wallet = Wallet.builder()
                .user(user).name("bill").build();
        wallet = walletService.saveOrUpdate(wallet);

        assertThat(wallet).isNotNull();
    }
    @Test
    public void update_Wallet_Service(){
        var user = User.builder().id(2L).firstName("merhshad").lastName("samaei")
                .username("m7485").password("123456789").build();
        user = userService.saveOrUpdate(user);
        var wallet = Wallet.builder()
                .user(user).name("bill2").build();
        user = userService.saveOrUpdate(user);
        wallet = walletService.saveOrUpdate(wallet);
        wallet.setName("my bill");
        Wallet wallet1 = walletService.saveOrUpdate(wallet);
        Assertions.assertEquals(wallet1.getId(), wallet.getId());
    }
    @Test
    public void should_Return_Throw_Exception_Not_Found_Entity() {
        Assertions.assertThrows(NotFoundException.class,
                () -> walletService.findById(200L));
    }
}
