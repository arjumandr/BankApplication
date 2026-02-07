package com.bankApp.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Clerk {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clerkId;
	
	private String clerkName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id_fk", nullable = false)
	@JsonBackReference
    private Manager manager;
	
	@OneToMany(mappedBy = "clerk")
	@JsonManagedReference("clerk-transactions")
    private List<Transaction> transactions;
	
	public Clerk(Integer clerkId, String clerkName, Manager manager, List<Transaction> transactions) {
		this.clerkName = clerkName;
		this.manager = manager;
		this.transactions = transactions;
	}
	public Clerk() {}
	public Integer getClerkId() {
		return clerkId;
	}
	public void setClerkId(Integer clerkId) {
		this.clerkId = clerkId;
	}
	public String getClerkName() {
		return clerkName;
	}
	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
