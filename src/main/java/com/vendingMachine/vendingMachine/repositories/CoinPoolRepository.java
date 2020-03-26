package com.vendingMachine.vendingMachine.repositories;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendingMachine.vendingMachine.model.Coin;
import com.vendingMachine.vendingMachine.model.CoinPool;
import com.vendingMachine.vendingMachine.model.VendingMachine;

@Repository
public interface CoinPoolRepository extends JpaRepository<CoinPool,Long>{


	Optional<CoinPool> findByVendingMachineAndCoin(@Valid VendingMachine vendingMachine, @Valid Coin coin);

	Collection<CoinPool> findByVendingMachine(@Valid VendingMachine vendingMachine);


}
