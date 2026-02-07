package com.bankApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.bankApp.dto.ClerkDTO;
import com.bankApp.dto.ManagerClerkDTO;
import com.bankApp.entities.Manager;
import com.bankApp.exceptions.BankEmployeeNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

@Repository
public class ManagerDaoImpl implements ManagerDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Manager getById(Integer id) {
		Manager manager = em.find(Manager.class, id);
		if(manager == null) {
			throw new BankEmployeeNotFoundException("Manager with this id= "+id+" doesnt exist.");
		}
		return manager;
	}

	@Override
	@EntityGraph(attributePaths = {"clerks", "accounts"})
//	public List<Manager> getAll() {
//		return em.createQuery("select m from Manager m", Manager.class).getResultList();
//	}
	public List<ManagerClerkDTO> getAll() {
	    List<Manager> managers = em.createQuery("SELECT m FROM Manager m LEFT JOIN FETCH m.clerks", Manager.class)
	                               .getResultList();

	    // Map to DTOs
	    return managers.stream()
	        .map(m -> new ManagerClerkDTO(
	            m.getManagerId(),
	            m.getManagerName(),
	            m.getClerks().stream()
	                .map(c -> new ClerkDTO(c.getClerkId(), c.getClerkName()))
	                .toList()
	        ))
	        .toList();
	}


	@Override
	public void addManager(Manager manager) {
		try {
			em.persist(manager);
		} catch(PersistenceException ex) {
			System.out.println("Need to handle gracefully.");
		}
	}

	@Override
	public void deleteManager(Integer id) {
		Manager manager = getById(id);
		em.remove(manager);
	}

	@Override
	public void updateManager(Manager manager) {
		try{
			if(manager == null) {
				throw new IllegalArgumentException("Manager or manager Id is not found.");
			}
			em.merge(manager);
		}
		catch(IllegalArgumentException ex) {
			throw new BankEmployeeNotFoundException("Manager not found");
		}
	}

}
