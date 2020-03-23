package com.vendingMachine.vendingMachine.model;


import java.util.Set;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vendingMachine.vendingMachine.helpers.BrandStock;
import com.vendingMachine.vendingMachine.helpers.CoinMaxStock;
//import com.vendingMachine.vendingMachine.helpers.CoinMaxCapacity;
import com.vendingMachine.vendingMachine.helpers.CoinStock;

@Entity
public class VendingMachine {
	
	
	
	@Id @GeneratedValue Long vendingMachineid;
	String model;
    int maxBrandsAvailable;
	int maxStockperBrand;
	@OneToMany(mappedBy="vendingMachine")
	@JsonIgnore
	private Set<BrandStock> brandstock;
	@OneToMany(mappedBy="vendingMachine")
	private Set<CoinStock> coinstock;
	@OneToMany(mappedBy="vendingMachine")
	Set<CoinMaxStock> coinMaxCapacity;
	
	
	public VendingMachine() {}
	
	
	public VendingMachine(String model, int maxBrandsAvailable, int maxStockperBrand, Set<BrandStock> brandstock,
			Set<CoinStock> coinstock, Set<CoinMaxStock> coinMaxCapacity) {
		super();
		this.model = model;
		this.maxBrandsAvailable = maxBrandsAvailable;
		this.maxStockperBrand = maxStockperBrand;
		this.brandstock = brandstock;
		this.coinstock = coinstock;
		this.coinMaxCapacity = coinMaxCapacity;
	}


	public void setBrandstock(Set<BrandStock> brandstock) {
		this.brandstock = brandstock;
	}
	

}
	

	
	
	
	