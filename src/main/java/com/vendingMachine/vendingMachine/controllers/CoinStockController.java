package com.vendingMachine.vendingMachine.controllers;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendingMachine.vendingMachine.model.Brand;
import com.vendingMachine.vendingMachine.model.Coin;
import com.vendingMachine.vendingMachine.model.CoinPool;
import com.vendingMachine.vendingMachine.model.CoinStock;
import com.vendingMachine.vendingMachine.model.CoinStockKey;
import com.vendingMachine.vendingMachine.model.VendingMachine;
import com.vendingMachine.vendingMachine.repositories.CoinStockRepository;

@RestController
@RequestMapping("api/coinStock")
@CrossOrigin(origins = "http://localhost:3000")
public class CoinStockController {
	
	@Autowired
	CoinStockRepository coinStockRepository;
	
	@Autowired
	VendingMachineController vendingMachineController;
	
	@Autowired
	CoinPoolController coinPoolController;
	
	
	@GetMapping("/")
	Collection<CoinStock> getAllCoinStock(){
		return (Collection<CoinStock> )coinStockRepository.findAll();
	}
	
	
	@PutMapping("/{vendingMachineId}/modifyCoinStock")
	Collection<CoinStock> modifyCoinStock(@PathVariable (name="vendingMachineId") String vendingMachineId,
						 @RequestBody @Valid Collection<CoinStock> coinStock	)
	{
		VendingMachine selectedMachine = vendingMachineController.getVendingMachine(vendingMachineId);
		Collection<CoinStock> foundStock =coinStockRepository.findByVendingMachine(selectedMachine);
		
		foundStock.clear();
		
		for (CoinStock activeCoinStock:coinStock)
		{	
			coinStockRepository.save(activeCoinStock);	
		}
		
		return  (Collection<CoinStock> )coinStockRepository.findAll(); 
	}
	
	
	int getAllCoinStockForMachine(@PathVariable (name="vendingMachineId") @Valid VendingMachine vendingMachine)
	{
		
		int totalStock = 0;
		Collection<CoinStock> foundStock =coinStockRepository.findByVendingMachine(vendingMachine);
		
		for (CoinStock coinStock : foundStock) {
			
			totalStock=totalStock+coinStock.getStock();
		}
		return totalStock;	
	}
	
	public void addCoinToCoinStock(@Valid Coin coin, @Valid VendingMachine vendingMachine)
	{
		
		Optional<CoinStock>coinStock = coinStockRepository.findByVendingMachineAndCoin(vendingMachine,coin);
		
		if (coinStock.isPresent()){
		coinStock.get().setStock(coinStock.get().getStock()+1);
		coinStockRepository.save(coinStock.get());
		}
		else {
		CoinStockKey newStockKey = new CoinStockKey(coin.getCoinId(),vendingMachine.getVendingMachineid());
		CoinStock newCoinStock = new CoinStock (newStockKey,coin,vendingMachine,1);
		coinStockRepository.save(newCoinStock);
		}			
	}


	public String generateChange(VendingMachine vendingMachine, Brand brand) {
		
			BigDecimal dueAmount =vendingMachine.getInsertedCoinValue().subtract(brand.getPrice());
			Collection<CoinStock> currentMachineStock = coinStockRepository.findByVendingMachine(vendingMachine);
			String message="The returned change is: ";
			int sentCoins=0;
			
			for (CoinStock coinStock: currentMachineStock)
			{
				if ((dueAmount.compareTo(coinStock.getCoin().getValue())>=0) && coinStock.getStock()>0)
				{
					while (dueAmount.compareTo(coinStock.getCoin().getValue())>=0 && coinStock.getStock()>0){
					coinStock.setStock(coinStock.getStock()-1);
					dueAmount = dueAmount.subtract(coinStock.getCoin().getValue());
					sentCoins++;	
					}
				message= message + sentCoins + "coins of " + coinStock.getCoin().getName()+",";
				sentCoins=0;
				}

			}
			if (dueAmount.compareTo(BigDecimal.ZERO)==1)
			{
				return "No Change Available";
			}
			else {
				for (CoinStock coinStock :currentMachineStock)
				{
					coinStockRepository.save(coinStock);
				}
			}
		
		return message;
	}


	public void clearPoolFromStock(Collection<CoinPool> toReturn) {

		for (CoinPool coin: toReturn) {
			Optional<CoinStock> toModify = coinStockRepository.findByVendingMachineAndCoin(coin.getVendingMachine(),
			coin.getCoin());
			
			if (toModify.isPresent())
			{
				toModify.get().setStock(toModify.get().getStock()-coin.getAmountInsideMachine());
				coinStockRepository.save(toModify.get());
			}
		}
	}

}
