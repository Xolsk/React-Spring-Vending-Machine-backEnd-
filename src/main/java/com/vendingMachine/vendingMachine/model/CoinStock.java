package com.vendingMachine.vendingMachine.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CoinStock {

	
	 @EmbeddedId
	 @JsonIgnore
	 CoinStockKey id;
	 
	 @ManyToOne
	 @MapsId("coin_id")
	 @JoinColumn(name = "coin_id")
	 Coin coin;
	 
	 @ManyToOne
	 @MapsId("vendingMachine_id")
	 @JoinColumn(name = "vendingMachine_id")
	 @JsonIgnore
	 VendingMachine vendingMachine;
	 
	 int stock;
	 
	public CoinStock() {}

	public CoinStock(CoinStockKey key,Coin coin, VendingMachine vendingMachine, int stock) {
		super();
		this.id=key;
		this.coin = coin;
		this.vendingMachine = vendingMachine;
		this.stock = stock;
	}
	
	public CoinStockKey getId() {
		return id;
	}

	public void setId(CoinStockKey id) {
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	  
}
