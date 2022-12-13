package com.example.wallet.service.impl;

import com.example.wallet.base.BaseServiceImpl;
import com.example.wallet.entity.Bank;
import com.example.wallet.repository.BankRepository;
import com.example.wallet.service.BankService;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl extends BaseServiceImpl<
        Bank, Long , BankRepository> implements BankService {
    public BankServiceImpl(BankRepository repository) {
        super(repository);
    }

}
