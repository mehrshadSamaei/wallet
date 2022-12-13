package com.example.wallet.transfertest;

import com.example.wallet.WalletApplication;
import com.example.wallet.entity.Bank;
import com.example.wallet.entity.Transaction;
import com.example.wallet.entity.User;
import com.example.wallet.entity.Wallet;
import com.example.wallet.entity.enums.Status;
import com.example.wallet.entity.enums.Type;
import com.example.wallet.exception.BadRequestException;
import com.example.wallet.service.BankService;
import com.example.wallet.service.TransferService;
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


@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalletApplication.class)
public class TransferServiceTest {
    @Autowired
    private WalletService walletService;
    @Autowired
    private BankService bankService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private UserService userService;


    @Test
    public void test_Transfer_Bank_To_Wallet() {
        Double amount = 50000.00;
        User user = User.builder().firstName("mehrshad").lastName("Samaei")
                .username("m7427").password("123456").build();
        Bank bank = Bank.builder().amount(100000.00).user(user).build();
        Wallet wallet = Wallet.builder().user(user).name("bill").currentBalance(0.0).build();
        userService.saveOrUpdate(user);
        bank = bankService.saveOrUpdate(bank);
        wallet = walletService.saveOrUpdate(wallet);

        Transaction transaction = transferService.transferBankToWallet(wallet, bank, amount);
        Assertions.assertAll(
                () -> Assertions.assertEquals(transaction.getBank().getAmount(), 50000.00),
                () -> Assertions.assertEquals(transaction.getWallet().getCurrentBalance(), 50000.00),
                () -> Assertions.assertEquals(transaction.getType(), Type.success)
        );
    }

    @Test
    public void test_Transfer_Wallet_To_Bank_Service() {
        Double amount = 50000.00;
        User user = User.builder().firstName("mehran").lastName("Samaei")
                .username("m7229").password("123456").build();
        Bank bank = Bank.builder().amount(0.0).user(user).build();
        Wallet wallet = Wallet.builder().user(user).name("bill2").currentBalance(100000.00).build();
        userService.saveOrUpdate(user);
        bank = bankService.saveOrUpdate(bank);
        wallet = walletService.saveOrUpdate(wallet);

        Transaction transaction = transferService.transferWalletToBank(wallet, bank, amount);
        Assertions.assertAll(
                () -> Assertions.assertEquals(transaction.getBank().getAmount(), 50000.00),
                () -> Assertions.assertEquals(transaction.getWallet().getCurrentBalance(), 50000.00),
                () -> Assertions.assertEquals(transaction.getType(), Type.success)
        );
    }

    @Test
    public void test_Wallet_To_Self_Wallet_Service() {
        Double amount = 50000.00;
        User user = User.builder().firstName("mohammad").lastName("Samaei")
                .username("m7528").password("123456").build();
        user = userService.saveOrUpdate(user);
        Wallet firstWallet = Wallet.builder().user(user).name("bill3").currentBalance(100000.00).build();
        Wallet secondWallet = Wallet.builder().user(user).name("bill4").currentBalance(0.0).build();
        firstWallet = walletService.saveOrUpdate(firstWallet);
        secondWallet = walletService.saveOrUpdate(secondWallet);
        Transaction transaction = transferService.transferWalletToSelfWallet(amount, firstWallet, secondWallet);

        Assertions.assertAll(
                () -> Assertions.assertEquals(transaction.getWallet().getCurrentBalance(), 50000.00),
                () -> Assertions.assertEquals(transaction.getType(), Type.success)
        );
    }

    @Test
    public void test_Wallet_To_Other_Wallet_Service() {
        Double amount = 50000.00;
        User user = User.builder().firstName("hamid").lastName("Samaei")
                .username("m7823").password("123456").build();
        user = userService.saveOrUpdate(user);
        Wallet firstWallet = Wallet.builder().user(user).name("bill5").currentBalance(100000.00).build();
        Wallet secondWallet = Wallet.builder().user(user).name("bill6").currentBalance(0.0).build();
        firstWallet = walletService.saveOrUpdate(firstWallet);
        secondWallet = walletService.saveOrUpdate(secondWallet);
        Transaction transaction = transferService.transferWalletToOtherWallet(amount, firstWallet, secondWallet);
        Assertions.assertAll(
                () -> Assertions.assertEquals(transaction.getWallet().getCurrentBalance(), 50000.00),
                () -> Assertions.assertEquals(transaction.getType(), Type.success)
        );
    }

    @Test
    public void test_Wallet_To_Bank_When_Amount_Little_Zero() {
        Double amount = -1d;
        User user = User.builder().firstName("amir").lastName("Samaei")
                .username("a6932").password("123456").build();
        Bank bank = Bank.builder().amount(0.0).user(user).build();
        Wallet wallet = Wallet.builder().user(user).name("bill7").currentBalance(100000.00).build();
        userService.saveOrUpdate(user);
        bank = bankService.saveOrUpdate(bank);
        wallet = walletService.saveOrUpdate(wallet);

        Wallet finalWallet = wallet;
        Bank finalBank = bank;
        Assertions.assertThrows(BadRequestException.class,
                () -> transferService.transferWalletToBank(finalWallet, finalBank, amount));

    }
    @Test
    public void test_Wallet_To_Bank_When_Wallet_No_Active(){
        Double amount = 50000.00;
        User user = User.builder().firstName("reza").lastName("Samaei")
                .username("r7328").password("123456").build();
        Bank bank = Bank.builder().amount(0.0).user(user).build();
        Wallet wallet = Wallet.builder().user(user).name("bill8").currentBalance(100000.00).build();
        userService.saveOrUpdate(user);
        bank = bankService.saveOrUpdate(bank);
        wallet.setStatus(Status.nonActive);
        wallet = walletService.saveOrUpdate(wallet);

        Wallet finalWallet = wallet;
        Bank finalBank = bank;
        Assertions.assertThrows(BadRequestException.class,
                () -> transferService.transferWalletToBank(finalWallet, finalBank, amount));
    }
    @Test
    public void test_Wallet_To_Bank_When_Source_Wallet_Amount_Little_Amount(){
        Double amount = 150000.00;
        User user = User.builder().firstName("mehdi").lastName("Samaei")
                .username("m742812").password("123456").build();
        Bank bank = Bank.builder().amount(0.0).user(user).build();
        Wallet wallet = Wallet.builder().user(user).name("bill11").currentBalance(100000.00).build();
        userService.saveOrUpdate(user);
        bank = bankService.saveOrUpdate(bank);
        wallet.setStatus(Status.nonActive);
        wallet = walletService.saveOrUpdate(wallet);

        Wallet finalWallet = wallet;
        Bank finalBank = bank;
        Assertions.assertThrows(BadRequestException.class,
                () -> transferService.transferWalletToBank(finalWallet, finalBank, amount));
    }
    @Test
    public void test_Bank_To_Wallet_When_Amount_Little_Zero() {
        Double amount = -1d;
        User user = User.builder().firstName("amir").lastName("Samaei")
                .username("ab6932").password("123456").build();
        Bank bank = Bank.builder().amount(0.0).user(user).build();
        Wallet wallet = Wallet.builder().user(user).name("bill9").currentBalance(100000.00).build();
        userService.saveOrUpdate(user);
        bank = bankService.saveOrUpdate(bank);
        wallet = walletService.saveOrUpdate(wallet);

        Wallet finalWallet = wallet;
        Bank finalBank = bank;
        Assertions.assertThrows(BadRequestException.class,
                () -> transferService.transferBankToWallet(finalWallet, finalBank, amount));
    }
    @Test
    public void test_Bank_To_Wallet_When_Wallet_No_Active(){
        Double amount = 50000.00;
        User user = User.builder().firstName("reza").lastName("Samaei")
                .username("rf7328").password("123456").build();
        Bank bank = Bank.builder().amount(100000.00).user(user).build();
        Wallet wallet = Wallet.builder().user(user).name("bill10").currentBalance(0.0).build();
        userService.saveOrUpdate(user);
        bank = bankService.saveOrUpdate(bank);
        wallet.setStatus(Status.nonActive);
        wallet = walletService.saveOrUpdate(wallet);

        Wallet finalWallet = wallet;
        Bank finalBank = bank;
        Assertions.assertThrows(BadRequestException.class,
                () -> transferService.transferBankToWallet(finalWallet, finalBank, amount));
    }
    @Test
    public void test_Bank_To_Wallet_When_Source_Wallet_Amount_Little_Amount(){
        Double amount = 150000.00;
        User user = User.builder().firstName("mehdi").lastName("Samaei")
                .username("mf742812").password("123456").build();
        Bank bank = Bank.builder().amount(5000.0).user(user).build();
        Wallet wallet = Wallet.builder().user(user).name("bill15").currentBalance(0.0).build();
        userService.saveOrUpdate(user);
        bank = bankService.saveOrUpdate(bank);
        wallet = walletService.saveOrUpdate(wallet);

        Wallet finalWallet = wallet;
        Bank finalBank = bank;
        Assertions.assertThrows(BadRequestException.class,
                () -> transferService.transferBankToWallet(finalWallet, finalBank, amount));
    }

    @Test
    public void test_Wallet_To_Self_Wallet_When_Source_Wallet_Or_Destination_Wallet_Not_Active(){
        Double amount = 50000.00;
        User user = User.builder().firstName("mohammad").lastName("Samaei")
                .username("mo7528").password("123456").build();
        user = userService.saveOrUpdate(user);
        Wallet firstWallet = Wallet.builder().user(user).name("bill12").currentBalance(100000.00).build();
        Wallet secondWallet = Wallet.builder().user(user).name("bill13").currentBalance(0.0).build();
        firstWallet.setStatus(Status.nonActive);
        firstWallet = walletService.saveOrUpdate(firstWallet);
        secondWallet = walletService.saveOrUpdate(secondWallet);
        Wallet finalSecondWallet = secondWallet;
        Wallet finalFirstWallet = firstWallet;
        Assertions.assertThrows(BadRequestException.class,
                () -> transferService.transferWalletToSelfWallet(amount , finalFirstWallet, finalSecondWallet));
    }
}
