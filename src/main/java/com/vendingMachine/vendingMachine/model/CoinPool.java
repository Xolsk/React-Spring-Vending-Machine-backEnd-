package com.vendingMachine.vendingMachine.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CoinPool {
	
	 @EmbeddedId
	 @JsonIgnore
	 CoinPoolKey id;
	 
	 @ManyToOne
	 @MapsId("coin_id")
	 @JoinColumn(name = "coin_id")
	 Coin coin;
	 
	 @ManyToOne
	 @MapsId("vendingMachine_id")
	 @JoinColumn(name = "vendingMachine_id")
	 @JsonIgnore
	 VendingMachine vendingMachine;
	 
	 int amountInsideMachine;
	 
	 public CoinPool() {}

	public CoinPool(CoinPoolKey id, Coin coin, VendingMachine vendingMachine, int amountInsideMachine) {
		super();
		this.id =id;
		this.coin = coin;
		this.vendingMachine = vendingMachine;
		this.amountInsideMachine = amountInsideMachine;
	}

	public CoinPoolKey getId() {
		return id;
	}

	public void setId(CoinPoolKey id) {
		this.id = id;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	public VendingMachine getVendingMachine() {
		return vendingMachine;
	}

	public void setVendingMachine(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	public int getAmountInsideMachine() {
		return amountInsideMachine;
	}

	public void setAmountInsideMachine(int amountInsideMachine) {
		this.amountInsideMachine = amountInsideMachine;
	}
	 
	 

}
