package com.neueda.test.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neueda.test.data.AtmEntity;
import com.neueda.test.data.repositories.AccountRepository;
import com.neueda.test.data.repositories.AtmRepository;
import com.neueda.test.exceptions.AtmException;
import com.neueda.test.response.WithdrawalResponse;
import com.neueda.test.data.AccountEntity;

@Service
public class AtmService {
	
	@Autowired
	private AccountRepository accountRepos;
	
	@Autowired
	private AtmRepository atmRepos;
	
    // cache account information
	private Map<Long, AccountEntity> userAccounts = new HashMap<>();
	
	// id of atm 
	private static final long atmId = 1;


	public AtmService() {
		super();
	}
	

	// calculate funds in atm
	public int getAtmTotal() {
		AtmEntity atm = atmRepos.findById(atmId).orElseThrow(() -> new AtmException("ATM has not been initialized"));
		return (atm.getFifties()*50)+(atm.getTwenties()*20)+(atm.getTens()*10)+(atm.getFives()*5);
	}

	// check account number and pin are valid, if not throw exception
	public boolean validateAccount(long accountNumber, int pin) {
		// look up the account
		AccountEntity account = accountRepos.findById(accountNumber).orElseThrow(() -> new AtmException("Account number does not exist, please check and try again"));
		userAccounts.put(account.getAccountNumber(), account);

		if(account.getPinNumber() != pin) {
			throw new AtmException("PIN number is incorrent, please check and try again");
		}
		return true;
	}
	
	// check amount requested is a multiple of 5
	public boolean validateAmount(int amount) {
		if(amount%5 == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	// retrieve the account balance
	public Map<String, Integer> getBalance(long accountNumber, int pin) {
		// validate the account
		validateAccount(accountNumber, pin);
		// retrieve the balance and the withdrawal limit and store them in a Map
		Map<String, Integer> balances = new HashMap<>();
		AccountEntity account = userAccounts.get(accountNumber);
		if(account != null) {
			balances.put("balance",  account.getAccountBalance());
			balances.put("limit",  account.getAccountBalance()+account.getOverdraft());
		}else {
			balances.put("balance",  0);
			balances.put("limit",  0);
		}
		return balances;
	}
	

	// withdraw funds from account
	public WithdrawalResponse withdrawFunds(long accountNumber, int pin, int amount) {
		validateAccount(accountNumber, pin);
		AccountEntity account = userAccounts.get(accountNumber);
		// initialize the atm
		AtmEntity atm = atmRepos.findById(atmId).orElseThrow(() -> new AtmException("ATM has not been initialized"));
		int balance = account.getAccountBalance();
		int fifties = 0;
		int twenties = 0;
		int tens = 0;
		int fives = 0;
		int atmFifties = atm.getFifties();
		int atmTwenties = atm.getTwenties();
		int atmTens = atm.getTens();
		int atmFives = atm.getFives();
		int amountLeft = amount;

		// if the amount requested is greater than the amount currently in the atm throw an exception
		int atmTotal = getAtmTotal();
		if(amount > atmTotal) {
			throw new AtmException("Not enough funds in ATM");
		}	
		
		

		int limit = account.getAccountBalance()+account.getOverdraft();
		// if the amount requested is greater than the customer withdrawal limit throw an exception
		if(amount > limit) {
			throw new AtmException("Not enough funds in account");
		}
		
		// if the amount requested is not a multiple of 5 throw an exception
		if(!validateAmount(amount)) {
			throw new AtmException("Amount requested must be in denominations of 5");
		}	

		// calculate the number of 50 euro notes to dispense
		// if there are enough notes in the atm to fill the request then take them
		// otherwise only take what's available
		fifties = amountLeft/50;
		if(fifties > 0) {
			if(atmFifties >= fifties) {
				amountLeft = amountLeft - (fifties*50);
				atmFifties = atmFifties - fifties;
			}else {
				amountLeft = amountLeft - (atmFifties*50);
				fifties=atmFifties;
				atmFifties = 0;
			}
		}

		// calculate the number of 20 euro notes to dispense
		twenties = amountLeft/20;
		if(twenties > 0) {
			if(atmTwenties >= twenties) {
				amountLeft = amountLeft - (twenties*20);
				atmTwenties = atmTwenties - twenties;
			}else {
				amountLeft = amountLeft - (atmTwenties*20);
				twenties=atmTwenties;
				atmTwenties = 0;
			}
		}

		// calculate the number of 10 euro notes to dispense
		tens = amountLeft/10;
		if(tens > 0) {
			if(atmTens >= tens) {
				amountLeft = amountLeft - (tens*10);
				atmTens = atmTens - tens;
			}else {
				amountLeft = amountLeft - (atmTens*10);
				tens=atmTens;
				atmTens = 0;
			}
		}

		// calculate the number of 5 euro notes to dispense
		fives = amountLeft/5;
		if(fives > 0) {
			if(atmFives >= fives) {
				amountLeft = amountLeft - (fives*5);
				atmFives = atmFives - fives;
			}else {
				amountLeft = amountLeft - (atmFives*5);
				fives=atmFives;
				atmFives = 0;
			}
		}

		// Throw an exception if the atm runs out of a particular denomination
		if(amountLeft > 0) {
			throw new AtmException("ATM has run out of the required notes for this amount, please try again");
		}
		
		// update account with new balance
		account.setAccountBalance(balance-amount);
		userAccounts.put(accountNumber, account);
		accountRepos.save(account);
		
		// update atm with new total
		atm.setFifties(atmFifties);
		atm.setTwenties(atmTwenties);
		atm.setTens(atmTens);
		atm.setFives(atmFives);
		atmRepos.save(atm);
		
		// create response to return
		WithdrawalResponse wr = new WithdrawalResponse();
		wr.setBalance(account.getAccountBalance());
		wr.setFifties(fifties);
		wr.setTwenties(twenties);
		wr.setTens(tens);
		wr.setFives(fives);
		
		return wr;
		
		
	}
	


}
