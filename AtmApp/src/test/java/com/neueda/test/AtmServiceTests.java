package com.neueda.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.neueda.test.exceptions.AtmException;
import com.neueda.test.response.WithdrawalResponse;
import com.neueda.test.service.AtmService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AtmServiceTests {
	
    @Autowired
    private AtmService service;

    
    @Test    
    public void testValidAccount() {
    	boolean validAccount = service.validateAccount(123456789, 1234);
    	assertThat(validAccount).isTrue();
    }
    
    
    @Test    
    public void testValidAmount() {
    	boolean validAmount = service.validateAmount(150);
    	assertThat(validAmount).isTrue();
    }
    
    @Test    
    public void testInvalidAmount() {
    	boolean invalidAmount = service.validateAmount(153);
    	assertThat(invalidAmount).isFalse();
    }
    
    
    @Test    
    public void testBalance() {
    	Map<String, Integer> balances = service.getBalance(123456789,1234);
    	int balance = balances.get("balance");
    	assertThat(balance).isEqualTo(800);
    }
    
    @Test    
    public void testWithdrawalLimit() {
    	Map<String, Integer> balances = service.getBalance(123456789,1234);
    	int limit = balances.get("limit");
    	assertThat(limit).isEqualTo(1000);
    }
    
    
    @Test    
    public void testWithdrawal1() {
    	WithdrawalResponse withdrawal = service.withdrawFunds(123456789, 1234, 50);
    	assertThat(withdrawal.getFifties()).isEqualTo(1);
    	assertThat(withdrawal.getBalance()).isEqualTo(750);
    	int total = service.getAtmTotal();
    	assertThat(total).isEqualTo(1450);
    	Map<String, Integer> balances = service.getBalance(123456789, 1234);
    	int balance = balances.get("balance");
    	assertThat(balance).isEqualTo(750);
    	int limit = balances.get("limit");
    	assertThat(limit).isEqualTo(950);
    }
    
    
    @Test    
    public void testWithdrawal2() {
    	WithdrawalResponse withdrawal = service.withdrawFunds(123456789, 1234, 600);
    	assertThat(withdrawal.getFifties()).isEqualTo(9);
    	assertThat(withdrawal.getTwenties()).isEqualTo(7);
    	assertThat(withdrawal.getTens()).isEqualTo(1);
    	assertThat(withdrawal.getBalance()).isEqualTo(150);
    	int total = service.getAtmTotal();
    	assertThat(total).isEqualTo(850);
    	Map<String, Integer> balances = service.getBalance(123456789,1234);
    	int balance = balances.get("balance");
    	assertThat(balance).isEqualTo(150);
    	int limit = balances.get("limit");
    	assertThat(limit).isEqualTo(350);
    }
    
    @Test
    public void testThrowsAtmException1() {
        Throwable exception = assertThrows(AtmException.class, () -> service.withdrawFunds(123456789, 1234, 400));
        assertEquals("Not enough funds in account", exception.getMessage());
    } 
    
    @Test
    public void testThrowsAtmException2() {
        Throwable exception = assertThrows(AtmException.class, () -> service.withdrawFunds(123456789, 1234, 1200));
        assertEquals("Not enough funds in ATM", exception.getMessage());
    } 

    @Test
    public void testThrowsAtmException3() {
        Throwable exception = assertThrows(AtmException.class, () -> {
	        for(int i =0; i <= 20; i++) {
	        	service.withdrawFunds(123456789, 1234, 5);
	        }	
        } 		
       );
        assertEquals("ATM has run out of the required notes for this amount, please try again", exception.getMessage());
    } 
    
    @Test
    public void testThrowsAtmException4() {
        Throwable exception = assertThrows(AtmException.class, () -> service.withdrawFunds(223456789, 1234, 0));
        assertEquals("Account number does not exist, please check and try again", exception.getMessage());
    } 

    @Test
    public void testThrowsAtmException5() {
        Throwable exception = assertThrows(AtmException.class, () -> service.withdrawFunds(123456789, 2234, 0));
        assertEquals("PIN number is incorrent, please check and try again", exception.getMessage());
    } 
    
    @Test
    public void testThrowsAtmException6() {
        Throwable exception = assertThrows(AtmException.class, () -> service.withdrawFunds(987654321, 4321, 18));
        assertEquals("Amount requested must be in denominations of 5", exception.getMessage());
    } 
}
