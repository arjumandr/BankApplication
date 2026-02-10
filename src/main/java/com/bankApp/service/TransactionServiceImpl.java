package com.bankApp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bankApp.dao.AccountDao;
import com.bankApp.dao.TransactionDao;
import com.bankApp.dto.PendingTransactionDto;
import com.bankApp.dto.TransactionList;
import com.bankApp.entities.Account;
import com.bankApp.entities.Clerk;
import com.bankApp.entities.Transaction;
import com.bankApp.entities.TransactionStatus;
import com.bankApp.entities.TransactionType;
import com.bankApp.exceptions.BankAccountNotFoundException;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
	private AccountDao accountDao;
	private AccountService accountService;
	private TransactionDao transactionDao;
	
	public TransactionServiceImpl(AccountDao accountDao, AccountService accountService, TransactionDao transactionDao) {
		this.accountDao = accountDao;
		this.accountService = accountService;
		this.transactionDao = transactionDao;
	}

	@Override
	public void transfer(int fromAccId, int toAccId, BigDecimal amount, Clerk clerk) {
	    Account fromAcc = accountService.getById(fromAccId);
	    Account toAcc = accountService.getById(toAccId);

	    fromAcc.addTransaction(TransactionType.TRANSFER_OUT, amount, clerk);
	    if (fromAcc.getBalance().compareTo(amount) < 0) {
	        throw new IllegalArgumentException(
	            "Insufficient balance in account " + fromAcc.getId()
	        );
	    }
	    Transaction tx =
	        fromAcc.getTransactions().get(fromAcc.getTransactions().size() - 1);

	    if (tx.getStatus() == TransactionStatus.APPROVED) {
	        fromAcc.setBalance(fromAcc.getBalance().subtract(amount));
	        toAcc.setBalance(toAcc.getBalance().add(amount));

	        toAcc.addTransaction(TransactionType.TRANSFER_IN, amount, clerk);
	    }

	    accountDao.updateAccount(fromAcc);
	    accountDao.updateAccount(toAcc);
	}

	@Override
	public void deposit(int accId, BigDecimal amount, Clerk clerk) {
	    Account acc = accountService.getById(accId);

	    acc.setBalance(acc.getBalance().add(amount));
	    acc.addTransaction(TransactionType.DEPOSIT, amount, clerk);

	    accountDao.updateAccount(acc);
	}
	
	// added new logic to ensure mgr approval is asked for withdrawal > 2 lakhs
	@Override
	public void withdraw(int accId, BigDecimal amount, Clerk clerk) {
	    Account acc = accountService.getById(accId);

	    // create transaction first
	    acc.addTransaction(TransactionType.WITHDRAWAL, amount, clerk);

	    Transaction lastTx =
	        acc.getTransactions().get(acc.getTransactions().size() - 1);

	    // only deduct if approved
	    if (lastTx.getStatus() == TransactionStatus.APPROVED) {
	        acc.setBalance(acc.getBalance().subtract(amount));
	    }

	    accountDao.updateAccount(acc);
	}
	
	@Override
	public List<TransactionList> getTransactions(int id){
		Account account = accountDao.getById(id);
		if(account == null) {
			throw new BankAccountNotFoundException("Account not found");
		}
		
		List<Transaction> transactions = account.getTransactions();
		System.out.println(
			    "TX: " + transactions.get(0).getAmount()
			);

		// Map to DTO for REST response
		List<TransactionList> dtoList = transactions.stream()
//		        .map(tx -> new TransactionList(tx.getAmount(), tx.getTransactionType(), tx.getDate()))
//		        .collect(Collectors.toList());
				.map(tx -> new TransactionList(
				        tx.getAmount(),
				        tx.getTransactionType(),
				        tx.getDate()
				    ))
				    .collect(Collectors.toList());
		
		return dtoList;
		
	}

	@Override
	public Transaction getById(int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PendingTransactionDto> findPendingTransactionsForManager(Integer managerId) {
        return transactionDao.findPendingTransactionsForManager(
                TransactionStatus.PENDING,
                managerId
        );
    }
}
