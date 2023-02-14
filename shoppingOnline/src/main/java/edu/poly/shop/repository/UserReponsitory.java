package edu.poly.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.shop.domain.Customer;

public interface UserReponsitory extends JpaRepository<Customer, Integer>{
	Customer findByEmail (String email);
}
