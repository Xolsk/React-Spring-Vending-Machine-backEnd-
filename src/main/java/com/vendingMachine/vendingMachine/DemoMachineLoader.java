package com.vendingMachine.vendingMachine;


import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vendingMachine.vendingMachine.helpers.BrandStock;
import com.vendingMachine.vendingMachine.helpers.CoinSize;
import com.vendingMachine.vendingMachine.model.Brand;
import com.vendingMachine.vendingMachine.model.Coin;
import com.vendingMachine.vendingMachine.model.VendingMachine;
import com.vendingMachine.vendingMachine.repositories.BrandRepository;
import com.vendingMachine.vendingMachine.repositories.CoinRepository;
import com.vendingMachine.vendingMachine.repositories.VendingMachineRepository;

import antlr.collections.List;

@Component
public class DemoMachineLoader implements CommandLineRunner {

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	VendingMachineRepository vendingMachineRepository;
	
	@Autowired
	CoinRepository coinRepository;

	

	@Override
	public void run(String... strings) throws Exception {
	
	Coin fiveCents = new Coin("5c", 0.05, null, null, CoinSize.SMALL);
	Coin tenCents = new Coin("10c", 0.10, null, null, CoinSize.SMALL);
	Coin twentyCents = new Coin("20c", 0.20, null, null, CoinSize.MEDIUM);
	Coin fiftyCents = new Coin("50c", 0.50, null, null, CoinSize.MEDIUM);
	Coin euro= new Coin("1e", 1, null, null, CoinSize.BIG);
	Coin twoEuro = new Coin("2e", 2, null, null, CoinSize.BIG);
	
	coinRepository.save(fiveCents);
	coinRepository.save(tenCents);
	coinRepository.save(twentyCents);
	coinRepository.save(fiftyCents);
	coinRepository.save(euro);
	coinRepository.save(twoEuro);
	
	
	VendingMachine model01=new VendingMachine("Model01", 5, 10,null, null,null);
	BrandStock cocaColaForModel01 = new BrandStock();
	cocaColaForModel01.setStock(10);
	Set<BrandStock> stockForModel01 = new HashSet<BrandStock>();
	stockForModel01.add(cocaColaForModel01);
	Brand cocaCola = new Brand("CocaCola", 1.75, stockForModel01);
	model01.setBrandstock();
	brandRepository.save(cocaCola);	
	vendingMachineRepository.save(model01);
//	
//	
//	cocaColaForModel01.setVendingMachine(model01);
//	stockForModel01.add(cocaColaForModel01);
//
//	brandRepository.save(cocaCola);	
//	vendingMachineRepository.save(model01);



	

	
	
	

	
	



		


	}

}
