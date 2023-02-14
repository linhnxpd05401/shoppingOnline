package edu.poly.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.OrderDetail;

@Repository
public interface OrderDetailReponsitory extends JpaRepository<OrderDetail, Integer>{
	
}
