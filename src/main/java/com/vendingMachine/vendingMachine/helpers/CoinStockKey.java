package com.vendingMachine.vendingMachine.helpers;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CoinStockKey implements Serializable {
	
    @Column(name = "coin_id")
    Long coinId;
 
    @Column(name = "vendingMachine_id")
    Long vendingMachineId;

	public CoinStockKey(Long coinId, Long vendingMachineId) {
		super();
		this.coinId = coinId;
		this.vendingMachineId = vendingMachineId;
	}

    
}
