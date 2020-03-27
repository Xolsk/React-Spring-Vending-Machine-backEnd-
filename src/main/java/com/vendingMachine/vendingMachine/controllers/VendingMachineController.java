package com.vendingMachine.vendingMachine.controllers;

import java.math.BigDecimal;
import java.util.Collection;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendingMachine.vendingMachine.helpers.ResponseGenerator;
import com.vendingMachine.vendingMachine.model.Brand;
import com.vendingMachine.vendingMachine.model.Coin;
import com.vendingMachine.vendingMachine.model.CoinPool;
import com.vendingMachine.vendingMachine.model.VendingMachine;
import com.vendingMachine.vendingMachine.repositories.VendingMachineRepository;



@RestController
@RequestMapping("/api/vendingMachines")
@CrossOrigin(origins = "http://localhost:3000")
public class VendingMachineController {
	
	@Autowired
	VendingMachineRepository vendingMachineRepository;
	
	@Autowired
	CoinController coinController;
	
	@Autowired
	CoinStockController coinStockController;
	
	@Autowired
	BrandStockController brandStockController;
	
	@Autowired
	BrandController brandController;
	
	@Autowired
	CoinPoolController coinPoolController;
	
	
	@GetMapping("/")
	 Collection<VendingMachine> getAllVendingMachines ()
	{
		return (Collection<VendingMachine>) vendingMachineRepository.findAll();
			
	}
	
	
	@GetMapping("/{vendingMachineId}")
	VendingMachine getVendingMachine(@PathVariable(name="vendingMachineId") String vendingMachineId)
	{
		Optional<VendingMachine> selectedMachine = vendingMachineRepository.findById(Long.parseLong(vendingMachineId));
		
		if (selectedMachine.isPresent()) {
			return selectedMachine.get();
		}
		
		return null;
	}
	
	
	@PostMapping("/{vendingMachineId}/insertCoin")
	ResponseEntity<ResponseGenerator> addCoinToMachine (@PathVariable(name="vendingMachineId") String vendingMachineId,
								 @RequestBody @Valid Coin coin)
	{
		
		Optional<VendingMachine> selectedMachine = vendingMachineRepository.findById(Long.parseLong(vendingMachineId));
		Optional<Coin> coinOnDB = coinController.findById(coin.getCoinId());
		String errorMessage="Undisclosed Error";
		  
		if (selectedMachine.isPresent() && coinOnDB.isPresent())
		{
			  
			  int currentCoinCapacity=coinStockController.getAllCoinStockForMachine(selectedMachine.get());
			  Boolean itFits= selectedMachine.get().checkCapacity(currentCoinCapacity);
			  int checkMaxAmount=selectedMachine.get().getMaxMoneyInserted().compareTo(selectedMachine.get().getInsertedCoinValue());
		  
			  if (itFits && checkMaxAmount==1)
			  {
				  
				BigDecimal newCoinAmount =selectedMachine.get().addCoin(coin);
			  	vendingMachineRepository.save(selectedMachine.get());
			  	coinPoolController.addCoinToPool(coin,selectedMachine.get());
			  	coinStockController.addCoinToCoinStock(coin,selectedMachine.get());
			  	ResponseGenerator allGood = new ResponseGenerator(newCoinAmount,"Coin Added to Machine");
			  	return ResponseEntity.ok().body(allGood);
			  } 
			  
			  if (itFits==false) {errorMessage="Machine is full of Coins, cant take no more. Your money of " +coin.getName() + " is returned";}
			  if  (checkMaxAmount<1) {errorMessage="Machine is not accepting more coins because there "
			  		+ "is already enough money to do its maximum purchase";} 
		  }
		
		ResponseGenerator badRequest = new ResponseGenerator (selectedMachine.get().getInsertedCoinValue(),errorMessage);	
		return ResponseEntity.ok().body(badRequest);		
	}
	
	
	@PostMapping("/{vendingMachineId}/purchase/{brandId}")
	ResponseEntity<ResponseGenerator> makePurchase(@PathVariable(name="vendingMachineId") String vendingMachineId,
										@PathVariable(name="brandId") String brandId){
		
		Optional<VendingMachine> selectedMachine = vendingMachineRepository.findById(Long.parseLong(vendingMachineId));
		Optional<Brand> selectedBrand = brandController.find(brandId);
		String errorMessage="";
		String changeMessage="No Change is returned.";
		
		if (selectedMachine.get().getInsertedCoinValue().compareTo(selectedBrand.get().getPrice())==1)
		{
			changeMessage=coinStockController.generateChange(selectedMachine.get(),selectedBrand.get());
			if (changeMessage=="No change Available")
			{
				ResponseGenerator badRequest = new ResponseGenerator(null,changeMessage);
				return ResponseEntity.badRequest().body(badRequest);

			}
		}
		
		if (selectedMachine.isPresent() && selectedBrand.isPresent())
		{	
			boolean enoughMoneyInserted = brandController.checkPrice(selectedMachine.get(),selectedBrand.get());
			boolean enoughStockAvailable = brandStockController.checkStock(selectedMachine.get(),selectedBrand.get());
			
			if (enoughStockAvailable && enoughMoneyInserted)
			{
				brandStockController.removeOne(selectedMachine.get(),selectedBrand.get());
				int stock = brandStockController.findStock(selectedMachine.get(),selectedBrand.get());
				
				coinPoolController.emptyPool(selectedMachine.get());
				selectedMachine.get().EmptyMachine();
				vendingMachineRepository.save(selectedMachine.get());
				ResponseGenerator sendProduct = new ResponseGenerator
				(
				selectedBrand.get(),stock,"You have received a delicious" + selectedBrand.get().getName()+"." + changeMessage
				);
				return ResponseEntity.ok().body(sendProduct);
			}		
			if (!enoughMoneyInserted) {errorMessage="Please insert more coins for the selected product.";}
			if (!enoughStockAvailable) {errorMessage="Product out of Stock.";}	
		}	
		ResponseGenerator badRequest = new ResponseGenerator(null,errorMessage);
		
		return ResponseEntity.badRequest().body(badRequest);	
	}

	
	@PostMapping("/{vendingMachineId}/returnMoney")
    ResponseEntity<String>returnMoney(@PathVariable(name="vendingMachineId") String vendingMachineId)
	{
		Optional<VendingMachine> selectedMachine = vendingMachineRepository.findById(Long.parseLong(vendingMachineId));
		
		if (selectedMachine.isPresent()) {
		
			Collection<CoinPool> toReturn= coinPoolController.getPool(selectedMachine.get());
			int EmptyPoolChecker=0;
			
			for (CoinPool coin : toReturn){
				if (coin.getAmountInsideMachine()==0)
				{
					EmptyPoolChecker++;
				}	
			} 
			if (EmptyPoolChecker==toReturn.size())
			{
				return ResponseEntity.ok().body("Nothing to Return");
			}
			
			selectedMachine.get().EmptyMachine();
			coinStockController.clearPoolFromStock(toReturn);
			String message = coinPoolController.getCoinsAsSttring(selectedMachine.get());
			coinPoolController.emptyPool(selectedMachine.get());
			vendingMachineRepository.save(selectedMachine.get());
			
			return ResponseEntity.ok().body("Coins Returned " + message);
		}
		
		return ResponseEntity.badRequest().body("Something Went Wrong");
		
	}
}
