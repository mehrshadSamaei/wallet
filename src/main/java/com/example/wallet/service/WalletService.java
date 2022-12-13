package com.example.wallet.service;

import com.example.wallet.base.BaseService;
import com.example.wallet.entity.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WalletService extends BaseService<Wallet , Long> {

    List<Wallet> findByExample(Wallet wallet);

    List<Wallet> findAllByOrderByCreateDateAsc();
}
