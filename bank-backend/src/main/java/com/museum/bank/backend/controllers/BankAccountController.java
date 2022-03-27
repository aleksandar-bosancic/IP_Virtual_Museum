package com.museum.bank.backend.controllers;

import com.museum.bank.backend.model.PaymentRequest;
import com.museum.bank.backend.model.PaymentResponse;
import com.museum.bank.backend.model.entities.BankAccountEntity;
import com.museum.bank.backend.model.entities.TransactionEntity;
import com.museum.bank.backend.repositories.BankAccountRepository;
import com.museum.bank.backend.repositories.TransactionEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class BankAccountController {
    private final BankAccountRepository repository;
    private final TransactionEntityRepository transactionRepository;


    public BankAccountController(BankAccountRepository repository, TransactionEntityRepository transactionRepository) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
    }

    public BankAccountEntity findByCardNumber(String cardNumber) {
        return this.repository.findAll().stream().filter(account -> account.getNumber().equals(cardNumber))
                .findAny().orElse(null);
    }


    @GetMapping("/accounts")
    public List<BankAccountEntity> findAll() {
        return this.repository.findAll();
    }

    @PostMapping("/payment")
    @Transactional
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PaymentResponse processPayment(@RequestBody PaymentRequest paymentRequest) {
        BankAccountEntity account = this.findByCardNumber(paymentRequest.getNumber());
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (Boolean.TRUE.equals(account.getBlocked())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        if (paymentRequest.getAmount() > account.getBalance()) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
        if (paymentRequest.getHolderName().equals(account.getHolderName())
                && paymentRequest.getValidThru().equals(account.getValidThru().toString())
                && paymentRequest.getType().equals(account.getType())
                && paymentRequest.getPin().equals(account.getPin())) {
            repository.updateBalance(account.getId(), paymentRequest.getAmount());
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setBankAccount(account);
            transactionEntity.setAmount(paymentRequest.getAmount());
            transactionEntity.setTransactionTime(timestamp);
            transactionEntity.setId(null);
            transactionEntity = transactionRepository.saveAndFlush(transactionEntity);
            return new PaymentResponse(true, transactionEntity.getId());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
