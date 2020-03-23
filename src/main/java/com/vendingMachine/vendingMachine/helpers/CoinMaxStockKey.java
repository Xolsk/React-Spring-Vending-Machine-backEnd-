package com.vendingMachine.vendingMachine.helpers;

import java.io.Serializable;

import javax.persistence.Column;

public class CoinMaxStockKey implements Serializable{

	@Column(name = "coin_id")
    Long coinId;
 
    @Column(name = "vendingMachine_id")
    Long vendingMachineId;

	public CoinMaxStockKey(Long coinId, Long vendingMachineId) {
		super();
		this.coinId = coinId;
		this.vendingMachineId = vendingMachineId;
	}
}


