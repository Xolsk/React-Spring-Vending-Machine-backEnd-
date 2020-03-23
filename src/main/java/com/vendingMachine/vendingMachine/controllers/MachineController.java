package com.vendingMachine.vendingMachine.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendingMachine.vendingMachine.repositories.BrandRepository;
import com.vendingMachine.vendingMachine.repositories.VendingMachineRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MachineController {
	
	private VendingMachineRepository vendingMachineRepository;
	private BrandRepository brandRepository;
	
	
	public MachineController(VendingMachineRepository vendingMachineRepository, BrandRepository brandRepository) {
		super();
		this.setVendingMachineRepository(vendingMachineRepository);
		this.setBrandRepository(brandRepository);
	}


	public VendingMachineRepository getVendingMachineRepository() {
		return vendingMachineRepository;
	}


	public void setVendingMachineRepository(VendingMachineRepository vendingMachineRepository) {
		this.vendingMachineRepository = vendingMachineRepository;
	}


	public BrandRepository getBrandRepository() {
		return brandRepository;
	}


	public void setBrandRepository(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}
	
	

}
