package com.vendingMachine.vendingMachine.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vendingMachine.vendingMachine.helpers.BrandStock;

@Entity
public class Brand {
	
	@Id @GeneratedValue long brandId;
	String name;
	double price;
	@OneToMany(mappedBy = "brand")
	@JsonIgnore
	Set<BrandStock> brandStock;
	
	public Brand() {}
	
	
	public Brand(String name, double price, Set<BrandStock> stock) {
		super();
		this.setName(name);
		this.price = price;
		this.brandStock = stock;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public long getBrandId() {
		return brandId;
	}
	
	
	public void setBrandStock(Set<BrandStock> stock) {
		this.brandStock = stock;
	}
	

}
