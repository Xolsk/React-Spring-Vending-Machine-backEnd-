package com.vendingMachine.vendingMachine.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendingMachine.vendingMachine.model.Coin;
import com.vendingMachine.vendingMachine.model.CoinStock;
import com.vendingMachine.vendingMachine.model.VendingMachine;

@Repository
public interface CoinStockRepository extends JpaRepository<CoinStock,Long> {

	Collection<CoinStock> findByVendingMachine(VendingMachine foundMachine);

	Optional<CoinStock>findByVendingMachineAndCoin(@Valid VendingMachine vendingMachine, @Valid Coin coin);




}
