package com.vendingMachine.vendingMachine.model;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;


@Entity
public class VendingMachine {


	@Id @GeneratedValue Long vendingMachineid;
	String model;
    int maxBrandsAvailable;
	int maxStockperBrand;
	BigDecimal maxMoneyInserted;
	BigDecimal insertedCoinValue=BigDecimal.ZERO;
	int coinMaxCapacity;
	
	@OneToMany(mappedBy="vendingMachine")
	Set<CoinPool> insertedCoins;
	@OneToMany(mappedBy="vendingMachine")
	private Set<BrandStock> brandStock;
	@OneToMany(mappedBy="vendingMachine")
	private Set<CoinStock> coinstock;

	
	public VendingMachine() {}
	
	
	public VendingMachine(String model, int maxBrandsAvailable, int maxStockperBrand, Set<BrandStock> brandstock,
			Set<CoinStock> coinstock, int coinMaxCapacity, BigDecimal maxMoney,Set<CoinPool> insertedCoins) {
		super();
		this.model = model;
		this.maxBrandsAvailable = maxBrandsAvailable;
		this.maxStockperBrand = maxStockperBrand;
		this.brandStock = brandstock;
		this.coinstock = coinstock;
		this.coinMaxCapacity = coinMaxCapacity;
		this.maxMoneyInserted=maxMoney;
		this.insertedCoins=insertedCoins;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public int getMaxBrandsAvailable() {
		return maxBrandsAvailable;
	}

	
	public void setMaxBrandsAvailable(int maxBrandsAvailable) {
		this.maxBrandsAvailable = maxBrandsAvailable;
	}

	
	public int getMaxStockperBrand() {
		return maxStockperBrand;
	}

	
	public void setMaxStockperBrand(int maxStockperBrand) {
		this.maxStockperBrand = maxStockperBrand;
	}

	
	public void setCoinstock(Set<CoinStock> coinstock) {
		this.coinstock = coinstock;
	}

	
	public Set<CoinStock> getCoinstock() {
		return coinstock;
	}
	
	
	public int getCoinMaxCapacity() {
		return coinMaxCapacity;
	}


	public void setCoinMaxCapacity(int coinMaxCapacity) {
		this.coinMaxCapacity = coinMaxCapacity;
	}

	
	public Set<BrandStock> getBrandStock() {
		return brandStock;
	}

	
	public void setBrandStock(Set<BrandStock> brandstock) {
		this.brandStock = brandstock;
	}
	


	public void setVendingMachineid(Long vendingMachineid) {
		this.vendingMachineid = vendingMachineid;
	}


	public Long getVendingMachineid() {
		return vendingMachineid;
	}


	public BigDecimal getMaxMoneyInserted() {
		return maxMoneyInserted;
	}


	public void setMaxMoneyInserted(BigDecimal maxMoneyInserted) {
		this.maxMoneyInserted = maxMoneyInserted;
	}

	public Set<CoinPool> getInsertedCoins() {
		return insertedCoins;
	}


	public void setInsertedCoins(Set<CoinPool> insertedCoins) {
		this.insertedCoins = insertedCoins;
	}


	public BigDecimal getInsertedCoinValue() {
		return insertedCoinValue;
	}


	public void setInsertedCoinValue(BigDecimal insertedCoinValue) {
		this.insertedCoinValue = insertedCoinValue;
	}


	public Boolean checkCapacity(int currentCoinCapacity) {
		 if (currentCoinCapacity+1>this.coinMaxCapacity) return false;
		return true;
	}
	
	
	public BigDecimal addCoin(@Valid Coin coin) {
	
	  BigDecimal pastValue=this.getInsertedCoinValue();
      this.setInsertedCoinValue(pastValue.add(coin.value));
		 
	  return this.insertedCoinValue;
	  	 
	 }
	 

	public void EmptyMachine() {
		BigDecimal zero = BigDecimal.ZERO;
	    if (this.getInsertedCoinValue().compareTo(zero) > 0) {
	        this.setInsertedCoinValue(zero);
	    }
	   
	}
	
}
	

	
	
	
	