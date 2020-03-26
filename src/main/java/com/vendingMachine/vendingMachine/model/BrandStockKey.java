package com.vendingMachine.vendingMachine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public
class BrandStockKey implements Serializable {
 

	private static final long serialVersionUID = 1L;

	@Column(name = "brand_id")
    Long brandId;
 
    @Column(name = "vendingMachine_id")
    Long vendingMachineId;
    
    public BrandStockKey() {}

	public BrandStockKey(Long brandId, Long vendingMachineId) {
		super();
		this.brandId = brandId;
		this.vendingMachineId = vendingMachineId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brandId == null) ? 0 : brandId.hashCode());
		result = prime * result + ((vendingMachineId == null) ? 0 : vendingMachineId.hashCode());
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
		BrandStockKey other = (BrandStockKey) obj;
		if (brandId == null) {
			if (other.brandId != null)
				return false;
		} else if (!brandId.equals(other.brandId))
			return false;
		if (vendingMachineId == null) {
			if (other.vendingMachineId != null)
				return false;
		} else if (!vendingMachineId.equals(other.vendingMachineId))
			return false;
		return true;
	}
}
