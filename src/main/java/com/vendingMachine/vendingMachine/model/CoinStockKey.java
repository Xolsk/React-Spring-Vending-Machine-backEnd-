package com.vendingMachine.vendingMachine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CoinStockKey implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "coin_id")
    Long coinId;
 
    @Column(name = "vendingMachine_id")
    Long vendingMachineId;
    
    public CoinStockKey() {}

	public CoinStockKey(Long coinId, Long vendingMachineId) {
		super();
		this.coinId = coinId;
		this.vendingMachineId = vendingMachineId;
	}
  
}
