package com.vendingMachine.vendingMachine.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.vendingMachine.vendingMachine.helpers.CoinMaxStock;
import com.vendingMachine.vendingMachine.helpers.CoinSize;
import com.vendingMachine.vendingMachine.helpers.CoinStock;


@Entity
public class Coin {

	@Id @GeneratedValue long coinId;
	String name;
	double value;
	
	@Enumerated(EnumType.STRING)
	private
	CoinSize size;
	@OneToMany(mappedBy = "coin")
	Set<CoinStock> stock;
	@OneToMany(mappedBy = "coin")
	Set<CoinMaxStock> maxCap;
	
	
	public Coin( String name, double value, Set<CoinStock> stock, Set<CoinMaxStock> maxCap,CoinSize size) throws Exception {
		super();
	
		this.name = name;
		this.value = value;
		this.stock = stock;
		this.maxCap = maxCap;
		this.size=size;
		
		
	}

}


	

