package com.vendingMachine.vendingMachine.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendingMachine.vendingMachine.model.Coin;
import com.vendingMachine.vendingMachine.repositories.CoinRepository;

@RestController
@RequestMapping("/api/coins")
@CrossOrigin(origins = "http://localhost:3000")
public class CoinController {
	
	@Autowired
	private CoinRepository coinRepository;

	public CoinController(CoinRepository coinRepository) {
		super();
		this.coinRepository = coinRepository;
	}
	
	@GetMapping("/")
	 Collection<Coin> getAllCoins () {
		return (Collection<Coin>) coinRepository.findAll();			
	}

	
	public Optional<Coin> findById(long coinId) {
		
		Optional<Coin> coin = coinRepository.findById(coinId);
		return  coin;
	}
	
	

}
