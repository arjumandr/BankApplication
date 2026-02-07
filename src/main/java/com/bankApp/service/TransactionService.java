package com.bankApp.service;

import java.math.BigDecimal;
import java.util.List;

import com.bankApp.dto.TransactionList;

public interface TransactionService {
    // additional methods in the service layer pertaining to our business logic
    void transfer(int fromAccId, int toAccId, BigDecimal amount);
    void deposit(int accId, BigDecimal amount);
    void withdraw(int accId, BigDecimal amount);
    List<TransactionList> getTransactions(int id);
}
