package com.example.wallet.service.impl;

import com.example.wallet.entity.Bank;
import com.example.wallet.entity.Transaction;
import com.example.wallet.entity.Wallet;
import com.example.wallet.entity.enums.Status;
import com.example.wallet.entity.enums.Type;
import com.example.wallet.exception.BadRequestException;
import com.example.wallet.service.BankService;
import com.example.wallet.service.TransactionService;
import com.example.wallet.service.TransferService;
import com.example.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TransferServiceImpl implements TransferService {

    private final WalletService walletService;
    private final BankService bankService;
    private final TransactionService transactionService;
    private final Transaction transaction;

    public TransferServiceImpl(WalletService walletService, BankService bankService, TransactionService transactionService, Transaction transaction) {
        this.walletService = walletService;
        this.bankService = bankService;
        this.transactionService = transactionService;
        this.transaction = transaction;
    }

    @Override
    @Transactional
    public Transaction transferBankToWallet(Wallet wallet, Bank bank, Double amount) {
        if (amount > 0) {
            Wallet destinationWallet = walletService.findById(wallet.getId());
            if (destinationWallet.getStatus().equals(Status.active)) {
                Bank sourceBank = bankService.findById(bank.getId());
                if (bank.getAmount() > amount) {
                    Double newAmountBank = (sourceBank.getAmount() - amount);
                    sourceBank.setAmount(newAmountBank);
                    bankService.saveOrUpdate(sourceBank);
                    Double newWalletAmount = (destinationWallet.getCurrentBalance() + amount);
                    destinationWallet.setCurrentBalance(newWalletAmount);
                    walletService.saveOrUpdate(destinationWallet);
                    transaction.setWallet(destinationWallet);
                    transaction.setBank(sourceBank);
                    transaction.setType(Type.success);
                    transactionService.saveOrUpdate(transaction);

                } else {
                    transaction.setType(Type.fail);
                    transaction.setDescription("amount greater than balance");
                    transactionService.saveOrUpdate(transaction);
                    log.error(transaction.toString());
                    throw new BadRequestException("amount greater than balance!!!");
                }
            } else {
                transaction.setType(Type.fail);
                transaction.setDescription("this is not active");
                transactionService.saveOrUpdate(transaction);
                log.error(transaction.toString());
                throw new BadRequestException("this is not active");
            }
        } else {
            transaction.setType(Type.fail);
            transaction.setDescription("not acceptable");
            transactionService.saveOrUpdate(transaction);
            log.error(transaction.toString());
            throw new BadRequestException("not acceptable!!!");
        }
        return transaction;
    }

    @Override
    @Transactional
    public Transaction transferWalletToBank(Wallet wallet, Bank bank, Double amount) {
        if (amount > 0) {
            Bank destinationBank = bankService.findById(bank.getId());
            Wallet sourceWallet = walletService.findById(wallet.getId());
            if (sourceWallet.getStatus().equals(Status.active)) {
                if (sourceWallet.getCurrentBalance() > amount) {
                    Double newAmountWallet = (sourceWallet.getCurrentBalance() - amount);
                    sourceWallet.setCurrentBalance(newAmountWallet);
                    walletService.saveOrUpdate(sourceWallet);
                    Double newAmountBank = destinationBank.getAmount() + amount;
                    destinationBank.setAmount(newAmountBank);
                    bankService.saveOrUpdate(destinationBank);
                    transaction.setWallet(sourceWallet);
                    transaction.setBank(destinationBank);
                    transaction.setType(Type.success);
                    transactionService.saveOrUpdate(transaction);
                } else {
                    transaction.setType(Type.fail);
                    transaction.setDescription("amount greater than balance");
                    transactionService.saveOrUpdate(transaction);
                    log.error(transaction.toString());
                    throw new BadRequestException("amount greater than balance!!!");
                }
            } else {
                transaction.setType(Type.fail);
                transaction.setDescription("this is not active");
                transactionService.saveOrUpdate(transaction);
                log.error(transaction.toString());
                throw new BadRequestException("this is not active");
            }
        } else {
            transaction.setType(Type.fail);
            transaction.setDescription("not acceptable");
            transactionService.saveOrUpdate(transaction);
            log.error(transaction.toString());
            throw new BadRequestException("not acceptable!!!");
        }
        return transaction;
    }


    @Override
    @Transactional
    public Transaction transferWalletToSelfWallet(Double amount, Wallet firstWallet, Wallet secondWallet) {
        if (!(firstWallet.equals(secondWallet))) {
            if (amount > 0) {
                Wallet sourceWallet = walletService.findById(firstWallet.getId());
                Wallet destinationWallet = walletService.findById(secondWallet.getId());
                if (sourceWallet.getStatus().equals(Status.active) && destinationWallet.getStatus().equals(Status.active)) {
                    if (sourceWallet.getUser().getId().equals(destinationWallet.getUser().getId())) {
                        checkedAmount(amount, sourceWallet, destinationWallet);
                        transaction.setWallet(sourceWallet);
                        transaction.setType(Type.success);
                        transactionService.saveOrUpdate(transaction);
                    } else {
                        log.info("It went to someone else's wallet");
                        transferWalletToOtherWallet(amount, firstWallet, secondWallet);
                    }
                } else {
                    transaction.setType(Type.fail);
                    transaction.setDescription("this is not active");
                    transactionService.saveOrUpdate(transaction);
                    log.error(transaction.toString());
                    throw new BadRequestException("this is not active");
                }
            } else {
                transaction.setType(Type.fail);
                transaction.setDescription("not acceptable");
                transactionService.saveOrUpdate(transaction);
                log.error(transaction.toString());
                throw new BadRequestException("not acceptable!!!");
            }
        } else {
            transaction.setType(Type.fail);
            transaction.setDescription("are the same");
            transactionService.saveOrUpdate(transaction);
            log.error(transaction.toString());
            throw new BadRequestException("are the same!!!");
        }
        return transaction;
    }

    private void checkedAmount(Double amount, Wallet sourceWallet, Wallet destinationWallet) {
        if (sourceWallet.getCurrentBalance() > amount) {
            Double newAmountFirstWallet = sourceWallet.getCurrentBalance() - amount;
            sourceWallet.setCurrentBalance(newAmountFirstWallet);
            walletService.saveOrUpdate(sourceWallet);
            Double newAmountSecondWallet = destinationWallet.getCurrentBalance() + amount;
            destinationWallet.setCurrentBalance(newAmountSecondWallet);
            walletService.saveOrUpdate(destinationWallet);
        } else {
            transaction.setType(Type.fail);
            transactionService.saveOrUpdate(transaction);
            throw new BadRequestException("amount greater than balance!!!");
        }
    }

    @Override
    public Transaction transferWalletToOtherWallet(Double amount, Wallet firstWallet, Wallet secondWallet) {
        if (!firstWallet.getId().equals(secondWallet.getId())) {
            if (amount > 0) {
                Wallet sourceWallet = walletService.findById(firstWallet.getId());
                Wallet destinationWallet = walletService.findById(secondWallet.getId());
                if (sourceWallet.getStatus().equals(Status.active) && destinationWallet.getStatus().equals(Status.active)) {
                    if (sourceWallet.getUser().getId().equals(destinationWallet.getUser().getId())) {
                        transferWalletToSelfWallet(amount, firstWallet, secondWallet);
                    } else {
                        checkedAmount(amount, sourceWallet, destinationWallet);
                    }
                }else {
                    transaction.setType(Type.fail);
                    transaction.setDescription("this is not active");
                    transactionService.saveOrUpdate(transaction);
                    log.error(transaction.toString());
                    throw new BadRequestException("this is not active");
                }
            } else {
                transaction.setType(Type.fail);
                transactionService.saveOrUpdate(transaction);
                throw new BadRequestException("not acceptable!!!");
            }

        } else {
            transaction.setType(Type.fail);
            transactionService.saveOrUpdate(transaction);
            throw new BadRequestException("are the same!!!");
        }
        return transaction;
    }
}
