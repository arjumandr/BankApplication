package com.bankApp.service;

import java.math.BigDecimal;
import java.util.List;

import com.bankApp.dto.TransactionList;
import com.bankApp.entities.Clerk;
import com.bankApp.entities.Transaction;

public interface TransactionService {
    // additional methods in the service layer pertaining to our business logic
    void transfer(int fromAccId, int toAccId, BigDecimal amount, Clerk clerk);
    void deposit(int accId, BigDecimal amount, Clerk clerk);
    void withdraw(int accId, BigDecimal amount, Clerk clerk);
    List<TransactionList> getTransactions(int id);
	Transaction getById(int transactionId);
}
