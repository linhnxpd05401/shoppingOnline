package edu.poly.shop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Order;


@Repository
public interface OrderReponsitory extends JpaRepository<Order, Integer>{
	
	@Query("SELECT o FROM Order o WHERE o.customer.customerId = ?1 AND o.orderedDate = ?2")
	@Modifying
	List<Order> findByCustomerAndDate(Integer customerId, Date orderDate);
	
	@Query("SELECT o FROM Order o WHERE o.customer.customerId = ?1")
	@Modifying
	List<Order> findByCustomer(Integer customerId);
}
