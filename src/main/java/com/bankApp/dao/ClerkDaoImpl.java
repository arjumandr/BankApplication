package com.bankApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bankApp.entities.Clerk;
import com.bankApp.exceptions.BankEmployeeNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

@Repository
public class ClerkDaoImpl implements ClerkDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Clerk getById(Integer id) {
		Clerk clerk = em.find(Clerk.class, id);
		if(clerk == null) {
			throw new BankEmployeeNotFoundException("Clerk with id = "+id+ " not found.");
		}
		return clerk;
	}

	@Override
	public List<Clerk> getAll() {
		return em.createQuery("SELECT c FROM Clerk c JOIN FETCH c.manager", Clerk.class).getResultList();
	}

	@Override
	public void addClerk(Clerk clerk) {
		try {
			em.persist(clerk);
		} catch(PersistenceException e) {
			System.out.println("Need to handle gracefully.");
		}
	}

	@Override
	public void deleteClerk(Integer id) {
		Clerk clerk = em.find(Clerk.class, id);
		if(clerk == null) {
			throw new BankEmployeeNotFoundException("Clerk with id="+id+" not found.");
		}
		em.remove(clerk);
	}

	@Override
	public void updateClerk(Clerk clerk) {
//		try{
//			if(clerk == null) {
//			throw new IllegalArgumentException("Clerk or clerk Id is invalid.");
//			}
//		em.merge(clerk);
//		} catch (IllegalArgumentException ex) {
//			throw new BankEmployeeNotFoundException("Clerk not found.");
//		}

		Clerk existing = em.find(Clerk.class, clerk.getClerkId());
		if(existing == null) {
			throw new BankEmployeeNotFoundException("Clerk not found.");
		}
		if(clerk.getClerkName() != null) {
			existing.setClerkName(clerk.getClerkName());
		}		
	}

	@Override
	public Optional<Clerk> findByClerkName(String username) {
		Clerk clerk = em.find(Clerk.class, username);
		if(clerk == null) {
			throw new BankEmployeeNotFoundException("Clerk with name = "+username+ " not found.");
		}
		return Optional.of(clerk);
	}

}
