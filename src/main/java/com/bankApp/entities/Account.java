package com.bankApp.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal balance;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
	private List<Transaction> transactionList =new ArrayList<>();
    
	public Account(Integer id, String name, BigDecimal balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
	}
	public Account(String name, BigDecimal balance) {
		this.name = name;
		this.balance = balance;
	}
	public Account() {}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	} 
	@JsonIgnore
	public List<Transaction> getTransactions(){
		return transactionList;
	}
	public void addTransaction(TransactionType type, BigDecimal amount) {
		Transaction tx = new Transaction();
        tx.setTransactionType(type);
        tx.setAmount(amount);
        tx.setDate(LocalDateTime.now());
        tx.setAccount(this);

        transactionList.add(tx);

        // update balance here
//        if (type == TransactionType.DEPOSIT) {
//            balance = balance.add(amount);
//        } else {
//            balance = balance.subtract(amount);
//        }
	}
}