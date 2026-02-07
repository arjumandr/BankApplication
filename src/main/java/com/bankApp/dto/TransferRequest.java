package com.bankApp.dto;

import java.math.BigDecimal;

import com.bankApp.entities.Clerk;

public class TransferRequest {
    private int fromAccId;
    private int toAccId;
    private BigDecimal amount;
    private Clerk clerk;
    
	public int getFromAccId() {
		return fromAccId;
	}

	public int getToAccId() {
		return toAccId;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public Clerk getClerk() {
		return clerk;
	}

}
