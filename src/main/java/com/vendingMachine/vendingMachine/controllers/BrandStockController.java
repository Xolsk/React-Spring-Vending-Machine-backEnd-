package com.vendingMachine.vendingMachine.controllers;

import java.util.Collection;
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
import com.vendingMachine.vendingMachine.model.BrandStock;
import com.vendingMachine.vendingMachine.model.CoinStock;
import com.vendingMachine.vendingMachine.model.VendingMachine;
import com.vendingMachine.vendingMachine.repositories.BrandStockRepository;

@RestController
@RequestMapping("api/brandStock")
@CrossOrigin(origins = "http://localhost:3000")
public class BrandStockController {

	@Autowired
	BrandStockRepository brandStockRepository;
	
	VendingMachineController vendingMachineController;
	
	@GetMapping("/")
	Collection<BrandStock> getAllBrandStock(){
		return (Collection<BrandStock>) brandStockRepository.findAll();
	}
	
	@PutMapping("/{vendingMachineId}/modifyBrandStock")
	Collection<BrandStock> modifyBrandStock(@PathVariable (name="vendingMachineId") String vendingMachineId,
						 @RequestBody @Valid String brandStock	)
	
		{	//TODO
			
			return  (Collection<BrandStock> )brandStockRepository.findAll(); 
	}

	public boolean checkStock(VendingMachine vendingMachine, Brand brand)
	{	
		Optional<BrandStock> brandStock = brandStockRepository.findByVendingMachineAndBrand(vendingMachine,brand);
		
		if (brandStock.isPresent() && brandStock.get().getStock()>0)
		{
			 return true;
		}	
		return false ;		
	}

	public void removeOne(VendingMachine vendingMachine, Brand brand) {
		
		Optional<BrandStock> brandStock = brandStockRepository.findByVendingMachineAndBrand(vendingMachine,brand);
		
		brandStock.get().setStock(brandStock.get().getStock()-1);
		
		brandStockRepository.save(brandStock.get());
		
		
	}

	public int findStock(VendingMachine vendingMachine, Brand brand) {
		
		Optional <BrandStock> stock = brandStockRepository.findByVendingMachineAndBrand(vendingMachine,brand);
		
		if (stock.isPresent()) 
		{
			return stock.get().getStock();
		}
		return 0;
	}


	
	
	
}
