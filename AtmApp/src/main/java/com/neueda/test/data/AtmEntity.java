package com.neueda.test.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="atm")
public class AtmEntity {
	@Id
	private long id;
	
	@Column(nullable=false, length=10)
	private int fifties;
	
	@Column(nullable=false, length=10)
	private int twenties;
	
	@Column(nullable=false, length=10)
	private int tens;
	
	@Column(nullable=false, length=10)
	private int fives;
	
	
	
	public AtmEntity(int fifties, int twenties, int tens, int fives) {
		super();
		this.fifties = fifties;
		this.twenties = twenties;
		this.tens = tens;
		this.fives = fives;
	}
	
	public AtmEntity() {
		super();
	}
	
	
	public int getFifties() {
		return fifties;
	}
	public void setFifties(int fifties) {
		this.fifties = fifties;
	}
	public int getTwenties() {
		return twenties;
	}
	public void setTwenties(int twenties) {
		this.twenties = twenties;
	}
	public int getTens() {
		return tens;
	}
	public void setTens(int tens) {
		this.tens = tens;
	}
	public int getFives() {
		return fives;
	}
	public void setFives(int fives) {
		this.fives = fives;
	}


}
