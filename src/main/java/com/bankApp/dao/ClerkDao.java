package com.bankApp.dao;

import java.util.List;

import com.bankApp.entities.Clerk;

public interface ClerkDao {
	Clerk getById(Integer id);
	List<Clerk> getAll();
	void addClerk(Clerk clerk);
	void deleteClerk(Integer id);
	void updateClerk(Clerk clerk);
}
