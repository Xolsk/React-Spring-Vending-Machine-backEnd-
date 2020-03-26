package com.vendingMachine.vendingMachine.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vendingMachine.vendingMachine.model.Brand;
import com.vendingMachine.vendingMachine.model.VendingMachine;

@Entity
public
class BrandStock {
 
    @EmbeddedId
    @JsonIgnore
    BrandStockKey id;
 
    @ManyToOne
    @MapsId("brand_id")
    @JoinColumn(name = "brand_id")
    Brand brand;
 
    @ManyToOne
    @MapsId("vendingMachine_id")
    @JoinColumn(name = "vendingMachine_id")
    @JsonIgnore
    VendingMachine vendingMachine;
 
    private int stock;

    public BrandStock() {}
    
    
	public BrandStock( BrandStockKey id,Brand brand, VendingMachine vendingMachine, int stock) {
		super();
		this.id=id;
		this.brand = brand;
		this.setVendingMachine(vendingMachine);
		this.setStock(stock);
	}
	
	public Brand getBrand() {
		return brand;
	}


	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setId(BrandStockKey id) {
		this.id = id;
	}
	
	public BrandStockKey getId() {
		return id;
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