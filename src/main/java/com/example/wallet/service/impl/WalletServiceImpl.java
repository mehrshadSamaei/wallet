package com.example.wallet.service.impl;

import com.example.wallet.base.BaseServiceImpl;
import com.example.wallet.entity.Wallet;
import com.example.wallet.repository.WalletRepository;
import com.example.wallet.service.WalletService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class WalletServiceImpl extends BaseServiceImpl<Wallet, Long, WalletRepository> implements WalletService {
    public WalletServiceImpl(WalletRepository repository) {
        super(repository);
    }

    @Override
    public Wallet saveOrUpdate(Wallet wallet) {
        SecureRandom secureRandom = new SecureRandom();
        long walletNumber = secureRandom.nextLong();
        wallet.setWalletNumber(walletNumber);
        return super.saveOrUpdate(wallet);
    }

    @Override
    public List<Wallet> findByExample(Wallet wallet) {
        return repository.findAll(Example.of(wallet));

    }

    @Override
    public List<Wallet> findAllByOrderByCreateDateAsc() {
        return repository.findAllByOrderByCreateDateAsc();
    }
}
