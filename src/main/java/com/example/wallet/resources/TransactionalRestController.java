package com.example.wallet.resources;

import com.example.wallet.dto.CompositeDto;
import com.example.wallet.dto.TransactionDto;
import com.example.wallet.entity.Bank;
import com.example.wallet.entity.Transaction;
import com.example.wallet.entity.Wallet;
import com.example.wallet.mapper.TransactionMapper;
import com.example.wallet.service.BankService;
import com.example.wallet.service.TransactionService;
import com.example.wallet.service.TransferService;
import com.example.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class TransactionalRestController {

    private final TransactionService service;
    private final TransactionMapper mapper;
    private final TransferService transferService;
    private final WalletService walletService;
    private final BankService bankService;

    @GetMapping("/find_all_with_create_date")
    public ResponseEntity<List<TransactionDto>> findAllWithCreateDate() {
        List<Transaction> transactions = service.findAllByOrderByCreateDateAsc();
        return ResponseEntity.ok(
                mapper.convertListEToD(transactions)
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findOneTransaction(@PathVariable Long id) {
        Transaction transaction = service.findById(id);
        return ResponseEntity.ok(
                mapper.convertEToD(transaction)
        );
    }
    @PostMapping("/bank_to_wallet")
    public ResponseEntity<TransactionDto> transferBankToWallet(@RequestBody CompositeDto compositeDto) {
        Bank bank = bankService.findById(compositeDto.getBankId());
        Wallet wallet = walletService.findById(compositeDto.getDestinationWalletId());
        Transaction transaction = transferService.transferBankToWallet(wallet, bank, compositeDto.getAmount());
        return ResponseEntity.ok(
                mapper.convertEToD(transaction)
        );
    }
    @PostMapping("/wallet_to_bank")
    public ResponseEntity<TransactionDto> transferWalletToBank(@RequestBody CompositeDto compositeDto) {
        Bank bank = bankService.findById(compositeDto.getBankId());
        Wallet wallet = walletService.findById(compositeDto.getSourceWalletId());
        Transaction transaction = transferService.transferWalletToBank(wallet, bank, compositeDto.getAmount());
        return ResponseEntity.ok(
                mapper.convertEToD(transaction)
        );
    }
    @PostMapping("/wallet_to_self_wallet")
    public ResponseEntity<TransactionDto> transferWalletToSelfWallet(
             @RequestBody CompositeDto compositeDto) {
        Wallet firstWallet = walletService.findById(compositeDto.getSourceWalletId());
        Wallet secondWallet = walletService.findById(compositeDto.getDestinationWalletId());
        Transaction transaction = transferService.transferWalletToSelfWallet(
                compositeDto.getAmount(),  firstWallet , secondWallet
        );
        return ResponseEntity.ok(
                mapper.convertEToD(transaction)
        );
    }
    @PostMapping("/wallet_to_any_wallet")
    public ResponseEntity<TransactionDto> transferWalletToOtherWallet(
            @RequestBody CompositeDto compositeDto) {
        Wallet firstWallet = walletService.findById(compositeDto.getSourceWalletId());
        Wallet secondWallet = walletService.findById(compositeDto.getDestinationWalletId());
        Transaction transaction = transferService.transferWalletToSelfWallet(
                compositeDto.getAmount(),  firstWallet , secondWallet
        );
        return ResponseEntity.ok(
                mapper.convertEToD(transaction)
        );
    }
}
