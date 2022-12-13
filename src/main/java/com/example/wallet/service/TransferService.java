package com.example.wallet.service;

import com.example.wallet.entity.Bank;
import com.example.wallet.entity.Transaction;
import com.example.wallet.entity.User;
import com.example.wallet.entity.Wallet;

import java.util.List;

public interface TransferService {
    Transaction transferBankToWallet(Wallet wallet, Bank bank , Double amount);

    Transaction transferWalletToBank(Wallet wallet, Bank bank , Double amount);

    Transaction transferWalletToSelfWallet(Double amount, Wallet firstWallet, Wallet secondWallet);

    Transaction transferWalletToOtherWallet(Double amount, Wallet firstWallet, Wallet secondWallet);

}
