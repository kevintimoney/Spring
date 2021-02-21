package com.neueda.test.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accounts")
public class AccountEntity {
	
	@Id
	private long accountNumber;
	
	@Column(nullable=false, length=50)
	private int pinNumber;
	
	@Column(nullable=false, length=50)
	private int accountBalance;
	
	@Column(nullable=false, length=50)
	private int overdraft;
	
	
	public AccountEntity(long accountNumber, int pinNumber, int accountBalance, int overdraft) {
		super();
		this.accountNumber = accountNumber;
		this.pinNumber = pinNumber;
		this.accountBalance = accountBalance;
		this.overdraft = overdraft;
	}

	public AccountEntity() {
		super();
	}

	public long getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}


	public int getPinNumber() {
		return pinNumber;
	}


	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}


	public int getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}


	public int getOverdraft() {
		return overdraft;
	}


	public void setOverdraft(int overdraft) {
		this.overdraft = overdraft;
	}
	
	
	

}
