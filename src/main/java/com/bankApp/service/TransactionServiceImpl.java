package com.bankApp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bankApp.dao.AccountDao;
import com.bankApp.dto.TransactionList;
import com.bankApp.entities.Account;
import com.bankApp.entities.Transaction;
import com.bankApp.entities.TransactionType;
import com.bankApp.exceptions.BankAccountNotFoundException;

import jakarta.transaction.Transactional;


@Service
@Transactional

public class TransactionServiceImpl implements TransactionService{
	private AccountDao accountDao;
	private AccountService accountService;
	
	public TransactionServiceImpl(AccountDao accountDao, AccountService accountService) {
		this.accountDao = accountDao;
		this.accountService = accountService;
	}

	@Override
	public void transfer(int fromAccId, int toAccId, BigDecimal amount) {
		Account fromAcc = accountService.getById(fromAccId);
		Account toAcc = accountService.getById(toAccId);

		fromAcc.setBalance(fromAcc.getBalance().subtract(amount));

		accountDao.updateAccount(fromAcc);

		toAcc.setBalance(toAcc.getBalance().add(amount));

		accountDao.updateAccount(toAcc);
		
		fromAcc.addTransaction(TransactionType.TRANSFER, amount);
		toAcc.addTransaction(TransactionType.TRANSFER, amount);

	}

	@Override
	public void deposit(int accId, BigDecimal amount) {
		Account acc = accountService.getById(accId);
		acc.setBalance(acc.getBalance().add(amount));
		accountDao.updateAccount(acc);
		acc.addTransaction(TransactionType.DEPOSIT, amount);
	}
	
	// need to add logic to ensure mgr approval is asked for withdrawal > 2 lakhs
	@Override
	public void withdraw(int accId, BigDecimal amount) {
		Account acc = accountService.getById(accId);
		acc.setBalance(acc.getBalance().subtract(amount));
		accountDao.updateAccount(acc);
		acc.addTransaction(TransactionType.WITHDRAWAL, amount);
	}
	
	@Override
	public List<TransactionList> getTransactions(int id){
//		return accountDao.getTransactions(id);
		Account account = accountDao.getById(id);
		if(account == null) {
			throw new BankAccountNotFoundException("Account not found");
		}
		
		List<Transaction> transactions = account.getTransactions();

		// Map to DTO for REST response
		List<TransactionList> dtoList = transactions.stream()
		        .map(tx -> new TransactionList(tx.getAmount(), tx.getTransactionType(), tx.getDate()))
		        .collect(Collectors.toList());
		
		return dtoList;
		
	}
}
