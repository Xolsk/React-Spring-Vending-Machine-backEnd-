package com.vendingMachine.vendingMachine.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendingMachine.vendingMachine.model.Brand;
import com.vendingMachine.vendingMachine.model.BrandStock;
import com.vendingMachine.vendingMachine.model.CoinStock;
import com.vendingMachine.vendingMachine.model.VendingMachine;

@Repository
public interface BrandStockRepository extends JpaRepository<BrandStock,Long> {

	Optional<BrandStock> findByVendingMachineAndBrand(VendingMachine vendingMachine, Brand brand);

	Collection<CoinStock> findByVendingMachine(VendingMachine selectedMachine);


}
