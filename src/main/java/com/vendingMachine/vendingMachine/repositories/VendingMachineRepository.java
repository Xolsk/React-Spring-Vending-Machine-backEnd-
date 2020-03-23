package com.vendingMachine.vendingMachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendingMachine.vendingMachine.model.VendingMachine;

@Repository
public interface VendingMachineRepository extends JpaRepository<VendingMachine, Long> {

}
