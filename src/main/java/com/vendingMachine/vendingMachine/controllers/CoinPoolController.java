package com.vendingMachine.vendingMachine.controllers;

import java.util.Optional;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.vendingMachine.vendingMachine.model.Coin;
import com.vendingMachine.vendingMachine.model.CoinPool;
import com.vendingMachine.vendingMachine.model.CoinPoolKey;
import com.vendingMachine.vendingMachine.model.VendingMachine;
import com.vendingMachine.vendingMachine.repositories.CoinPoolRepository;

@RestController
public class CoinPoolController {

	@Autowired
	CoinPoolRepository coinPoolRepository;
	
	
	public void addCoinToPool(@Valid Coin coin, @Valid VendingMachine vendingMachine) {
		
		Optional<CoinPool> foundPool =coinPoolRepository.findByVendingMachineAndCoin(vendingMachine,coin);
		
		if (foundPool.isPresent())
		{
			foundPool.get().setAmountInsideMachine(foundPool.get().getAmountInsideMachine()+1);
			coinPoolRepository.save(foundPool.get());
			
		} else
		{
			CoinPoolKey newKey= new CoinPoolKey(coin.getCoinId(),vendingMachine.getVendingMachineid());
			CoinPool newPool = new CoinPool(newKey,coin,vendingMachine,1);
			coinPoolRepository.save(newPool);
		}
		
		
	}


	public void emptyPool( @Valid VendingMachine vendingMachine)  {
		
		Collection<CoinPool> foundPool = coinPoolRepository.findByVendingMachine(vendingMachine);
		
		for (CoinPool coin : foundPool ) {
			coin.setAmountInsideMachine(0);
			coinPoolRepository.save(coin);
		}
	}


	public Collection<CoinPool> getPool(VendingMachine vendingMachine) {
		
		Collection<CoinPool> foundPool = coinPoolRepository.findByVendingMachine(vendingMachine);
		
		return foundPool;	
	}


	public String getCoinsAsSttring(VendingMachine vendingMachine) {
		Collection<CoinPool> foundPool = coinPoolRepository.findByVendingMachine(vendingMachine);
		String message=":";
		
		for (CoinPool coin : foundPool)
		{
			if (coin.getAmountInsideMachine()>0)
			{
				message= message + (coin.getAmountInsideMachine() + " "+ coin.getCoin().getName()+ " coins, ");
			}
		}
		return message;
	}

}
