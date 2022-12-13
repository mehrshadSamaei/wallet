package com.example.wallet.service;

import com.example.wallet.base.BaseService;
import com.example.wallet.entity.Transaction;
import com.example.wallet.entity.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TransactionService extends BaseService<Transaction , Long> {

    List<Transaction> findAllByOrderByCreateDateAsc();
}
