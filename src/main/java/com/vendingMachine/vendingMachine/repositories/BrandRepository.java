package com.vendingMachine.vendingMachine.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendingMachine.vendingMachine.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository <Brand,Long>{



}
