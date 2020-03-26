package com.vendingMachine.vendingMachine.model;


import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vendingMachine.vendingMachine.helpers.CoinSize;


@Entity
public class Coin {

	@Id @GeneratedValue long coinId;
	String name;
	BigDecimal value;
	
	@Enumerated(EnumType.STRING)
	private
	CoinSize size;
	@OneToMany(mappedBy = "coin")
	@JsonIgnore
	Set<CoinStock> stock;
	@OneToMany(mappedBy = "coin")
	@JsonIgnore
	Set<CoinPool> coinPool;
	
	
	public Coin() {}
	
	
	public Coin( String name, BigDecimal value, Set<CoinStock> stock,CoinSize size,Set<CoinPool> pool) throws Exception {
		super();
	
		this.name = name;
		this.value = value;
		this.stock = stock;
		this.size=size;
		this.coinPool=pool;
		
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (coinId ^ (coinId >>> 32));
		result = prime * result + ((coinPool == null) ? 0 : coinPool.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coin other = (Coin) obj;
		if (coinId != other.coinId)
			return false;
		if (coinPool == null) {
			if (other.coinPool != null)
				return false;
		} else if (!coinPool.equals(other.coinPool))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size)
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	

	public Set<CoinPool> getCoinPool() {
		return coinPool;
	}


	public void setCoinPool(Set<CoinPool> coinPool) {
		this.coinPool = coinPool;
	}


	public long getCoinId() {
		return coinId;
	}


	public void setCoinId(long coinId) {
		this.coinId = coinId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BigDecimal getValue() {
		return value;
	}


	public void setValue(BigDecimal value) {
		this.value = value;
	}


	public CoinSize getSize() {
		return size;
	}


	public void setSize(CoinSize size) {
		this.size = size;
	}


	public Set<CoinStock> getStock() {
		return stock;
	}


	public void setStock(Set<CoinStock> stock) {
		this.stock = stock;
	}

}


	

