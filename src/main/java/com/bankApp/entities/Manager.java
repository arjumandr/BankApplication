package com.bankApp.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer managerId;
	
	private String managerName;
	private String managerEmail;
	
	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Clerk> clerks;

    @OneToMany
    @JoinColumn(name = "manager_id_fk")
    @JsonManagedReference
    private List<Account> accounts;
    
    private String password; 

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Manager() {}

	public Manager(String name, String email, List<Account> accounts) {
		this.managerName = name;
		this.managerEmail = email;
		this.accounts = accounts;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String name) {
		this.managerName = name;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String email) {
		this.managerEmail = email;
	}

	public List<Clerk> getClerks() {
		return clerks;
	}

	public void setClerks(List<Clerk> clerks) {
		this.clerks = clerks;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
}
