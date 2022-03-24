package com.museum.bank.backend.controllers;

import com.museum.bank.backend.model.Transaction;
import com.museum.bank.backend.repositories.TransactionEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionEntityRepository repository;
    private final ModelMapper modelMapper;


    public TransactionController(TransactionEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/transactions")
    public List<Transaction> findAll() {
        return this.repository.findAll().stream().map(transaction -> modelMapper.map(transaction, Transaction.class)).toList();
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> findAllByAccountId(@PathVariable Integer id) {
        return this.repository.findAll().stream().filter(transaction -> transaction.getBankAccount().getId().equals(id))
                .map(transaction -> modelMapper.map(transaction, Transaction.class)).toList();
    }

    @GetMapping("/verify/{id}")
    public Transaction findById(@PathVariable Integer id){
        return this.repository.findById(id).map(transactionEntity -> modelMapper.map(transactionEntity, Transaction.class))
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
