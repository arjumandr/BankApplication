package com.bankApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankApp.dto.DepositRequest;
import com.bankApp.dto.TransactionList;
import com.bankApp.dto.TransferRequest;
import com.bankApp.dto.WithdrawRequest;
import com.bankApp.service.TransactionService;

/*
 * This controller has additional methods introduced in the service layer pertaining to our business logic
 * TODO: rewrite all localhost links on postman to test these, add "transaction"
 */

@RestController
@RequestMapping(path = "v1")
public class TransactionController {
	
	private TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping("transfer")
	public void transfer(@RequestBody TransferRequest request) {
		transactionService.transfer(
	        request.getFromAccId(),
	        request.getToAccId(),
	        request.getAmount(),
	        request.getClerk()
	    );
	}
	
	@PostMapping(path = "accounts/{accId}/deposit")
    public void deposit(@PathVariable int accId, @RequestBody DepositRequest depositReq) {
		transactionService.deposit(accId, depositReq.getAmount(), depositReq.getClerk());
    }
	
	@PostMapping(path = "accounts/{accId}/withdraw")
    public void withdraw(@PathVariable int accId, @RequestBody WithdrawRequest withdrawReq) {
		transactionService.withdraw(accId, withdrawReq.getAmount(), withdrawReq.getClerk());
    }
	
	@GetMapping(path = "accounts/{accId}/getTransaction")
	public List<TransactionList> getTransactions(@PathVariable(name="accId") Integer id){
		return transactionService.getTransactions(id);
	}
}
