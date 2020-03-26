package com.vendingMachine.vendingMachine.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Brand {
	
	@Id @GeneratedValue long brandId;
	String name;
	BigDecimal price;
	@OneToMany(mappedBy = "brand")
	@JsonIgnore
	Set<BrandStock> brandStock;
	
	public Brand() {}
	
	
	public Brand(String name, BigDecimal price, Set<BrandStock> stock) {
		super();
		this.setName(name);
		this.setPrice(price);
		this.setBrandStock(stock);
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Set<BrandStock> getBrandStock() {
		return brandStock;
	}


	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	
	public long getBrandId() {
		return brandId;
	}
	
	
	public void setBrandStock(Set<BrandStock> stock) {
		this.brandStock = stock;
	}
	
}
