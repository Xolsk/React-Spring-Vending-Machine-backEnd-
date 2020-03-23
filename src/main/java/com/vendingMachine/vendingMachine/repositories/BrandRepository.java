package com.vendingMachine.vendingMachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.vendingMachine.vendingMachine.model.Brand;


public interface BrandRepository extends JpaRepository <Brand,Long>{

}
