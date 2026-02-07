package com.bankApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.dto.ManagerClerkDTO;
import com.bankApp.entities.Manager;
import com.bankApp.service.ManagerService;

@RestController
@RequestMapping(path = "v1/managers")
public class ManagerController {
	private ManagerService managerService;

	public ManagerController(ManagerService managerService) {
		this.managerService = managerService;
	}
	@GetMapping()
	public List<ManagerClerkDTO> getAll(){
		return managerService.getAll();
	}
	
	@GetMapping(path = "getById/{id}")
	public Manager getById(@PathVariable int id ){
		return managerService.getById(id);
	}
	
	@PostMapping(path = "addManager")
	public void addManager(@RequestBody Manager manager) {
//		System.out.println("add account controller method called");
		managerService.addManager(manager);
	}
	
	@DeleteMapping(path = "{id}")
    public void deleteManager(@PathVariable Integer id) {
		managerService.deleteManager(id);
    }
	
	@PostMapping(path = "updateManager")
    public void updateManager(Manager manager) {
		managerService.updateManager(manager);
    }
}
