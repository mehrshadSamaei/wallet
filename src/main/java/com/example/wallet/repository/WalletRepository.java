package com.example.wallet.repository;

import com.example.wallet.entity.Wallet;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Override
    <S extends Wallet> List<S> findAll(Example<S> example);

    List<Wallet> findAllByOrderByCreateDateAsc();

}
