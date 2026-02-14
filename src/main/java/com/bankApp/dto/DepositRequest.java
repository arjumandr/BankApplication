package com.bankApp.dto;

import java.math.BigDecimal;

public class DepositRequest {
    private BigDecimal amount;
    private Integer clerkId;

    public Integer getClerkId() {
		return clerkId;
	}

	public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

