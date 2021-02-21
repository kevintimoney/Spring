package com.neueda.test.controller;

import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neueda.test.response.BalanceResponse;
import com.neueda.test.response.WithdrawalResponse;
import com.neueda.test.service.AtmService;

@RestController
@RequestMapping("/atm")
public class AtmController {
	
	@Autowired
	AtmService atmService;

	@GetMapping("/balance/{accountnumber}/{pin}")
	public ResponseEntity<BalanceResponse> getBalance(@PathVariable("accountnumber") Long accountNumber, @PathVariable("pin") Integer pin) {
			Map<String,Integer> account = atmService.getBalance(accountNumber, pin);
			int balance = account.get("balance");
			int limit = account.get("limit");
			
			BalanceResponse returnValue = new BalanceResponse();
			returnValue.setBalance(balance);
			returnValue.setMaxWithdrawal(limit);
			
			return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}

	
	@PutMapping("/withdraw/{accountnumber}/{pin}/{amount}")
	public ResponseEntity<WithdrawalResponse> withdraw(@PathVariable("accountnumber") Long accountNumber, @PathVariable("pin") Integer pin,  @PathVariable("amount") Integer amount) {
		
		    WithdrawalResponse returnValue = atmService.withdrawFunds(accountNumber, pin, amount);
			
			return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	
}
