package com.bankApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bankApp.dao.ManagerDao;
import com.bankApp.dto.ManagerClerkDTO;
import com.bankApp.entities.Manager;
import com.bankApp.exceptions.BankEmployeeNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{
	private ManagerDao managerDao;
	
	public ManagerServiceImpl(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	public void approveTransaction(int transactionId) {
//	    to be done later
//		needs logic to access transactions by id
	}


	@Override
	public Manager getById(Integer id) {
		try {
			return managerDao.getById(id);
		} catch(EntityNotFoundException ex) {
			throw new BankEmployeeNotFoundException("Manager with id= "+id+" not found.");
		}
	}

	@Override
	public List<ManagerClerkDTO> getAll() {
		return managerDao.getAll();
	}

	@Override
	public void addManager(Manager manager) {
		if (manager == null) {
	        throw new IllegalArgumentException("Account cannot be null");
	    }
	    try {
	    	managerDao.addManager(manager);
	    } catch (PersistenceException ex) {
	        // Wrap lower-level exceptions in a service-specific exception if desired
//	    	System.out.println("Persistence exc occurred.");
	        throw new RuntimeException("Failed to add account", ex);
	    }
	}

	@Override
	public void deleteManager(Integer id) {
		managerDao.deleteManager(id);
	}

	@Override
	public void updateManager(Manager manager) {
		managerDao.updateManager(manager);
	}

}
