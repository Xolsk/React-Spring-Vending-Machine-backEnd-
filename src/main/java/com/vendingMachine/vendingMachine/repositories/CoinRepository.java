package com.vendingMachine.vendingMachine.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.vendingMachine.vendingMachine.model.Coin;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin,Long> {

}
