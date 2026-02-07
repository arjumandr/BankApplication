package com.bankApp.dto;

import java.math.BigDecimal;

import com.bankApp.entities.Clerk;

public class WithdrawRequest {
	private BigDecimal amount;
	private Clerk clerk;

    public Clerk getClerk() {
		return clerk;
	}

	public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
