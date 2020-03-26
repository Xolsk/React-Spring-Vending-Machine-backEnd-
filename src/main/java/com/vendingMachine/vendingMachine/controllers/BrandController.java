package com.vendingMachine.vendingMachine.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendingMachine.vendingMachine.model.Brand;
import com.vendingMachine.vendingMachine.model.VendingMachine;
import com.vendingMachine.vendingMachine.repositories.BrandRepository;

@RestController
@RequestMapping("api/brands")
@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {
	
	@Autowired
	BrandRepository brandRepository;
	
	@GetMapping("/")
	Collection<Brand> getAllBrands(){
		return (Collection<Brand>) brandRepository.findAll();
	}

	public Optional<Brand> find(String brandId) {
		Optional<Brand> brand = brandRepository.findById(Long.parseLong(brandId));
		return brand;
	}

	public boolean checkPrice(VendingMachine vendingMachine, Brand brand) {
		
		if (vendingMachine.getInsertedCoinValue().compareTo(brand.getPrice())>-1) {
			return true;
		}
	return false;
}


	
}
