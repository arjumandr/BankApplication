package com.bankApp.dao;

import java.util.List;


import com.bankApp.entities.Account;

public interface AccountDao {
	public List<Account> getAll();
	public Account getById(int id);
	public void updateAccount(Account account);
	public void addAccount(Account account);
	public void deleteAccount(Integer id);
//	public List<Transaction> getTransactions(int id);
}