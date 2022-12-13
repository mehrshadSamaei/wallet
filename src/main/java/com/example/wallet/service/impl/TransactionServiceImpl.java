package com.example.wallet.service.impl;

import com.example.wallet.base.BaseServiceImpl;
import com.example.wallet.entity.Transaction;
import com.example.wallet.repository.TransactionRepository;
import com.example.wallet.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl extends BaseServiceImpl<Transaction , Long , TransactionRepository> implements TransactionService {
    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }

    @Override
    public List<Transaction> findAllByOrderByCreateDateAsc() {
        return repository.findAllByOrderByCreateDateAsc();
    }
}
