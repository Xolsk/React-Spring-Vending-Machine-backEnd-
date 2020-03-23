package com.vendingMachine.vendingMachine.helpers;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.vendingMachine.vendingMachine.model.Coin;
import com.vendingMachine.vendingMachine.model.VendingMachine;

@Entity
public class CoinMaxStock {

	
	 @EmbeddedId
	 CoinMaxStockKey id;
	 
	 @ManyToOne
	 @MapsId("coin_id")
	 @JoinColumn(name = "coin_id")
	 Coin coin;
	 
	 @ManyToOne
	 @MapsId("vendingMachine_id")
	 @JoinColumn(name = "vendingMachine_id")
	 VendingMachine vendingMachine;
	 
	 int maxStock;

	public CoinMaxStock(CoinMaxStockKey id, Coin coin, VendingMachine vendingMachine, int maxStock) {
		super();
		this.id = id;
		this.coin = coin;
		this.vendingMachine = vendingMachine;
		this.maxStock = maxStock;
	}
	 
	 
}