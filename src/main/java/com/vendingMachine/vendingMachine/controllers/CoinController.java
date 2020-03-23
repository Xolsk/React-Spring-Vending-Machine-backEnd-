package com.vendingMachine.vendingMachine.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendingMachine.vendingMachine.repositories.CoinRepository;

@RestController
@RequestMapping("/api/coins")
@CrossOrigin(origins = "http://localhost:3000")
public class CoinController {
	
	private CoinRepository coinRepository;

	public CoinController(CoinRepository coinRepository) {
		super();
		this.coinRepository = coinRepository;
	}
	
	

}
