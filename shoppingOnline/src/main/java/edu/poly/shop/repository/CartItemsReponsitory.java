package edu.poly.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.CartItem;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Product;

@Repository
public interface CartItemsReponsitory extends JpaRepository<CartItem, Integer>{
	List<CartItem> findByCustomer (Customer customer);
	
	CartItem findByCustomerAndProduct (Customer customer, Product product);
	Integer countByCustomer (Customer customerm);
	
	@Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.product.productId = ?2 "
			+ "AND c.customer.customerId = ?3")
	@Modifying
	void updateQuantity(Integer quantity,Integer productId, Integer customerId);
	
	
	@Query("DELETE FROM CartItem c WHERE c.customer.customerId = ?1 AND c.product.productId = ?2")
	@Modifying 
	public void deleteByCustomerAndProduct(Integer customerId, Integer productId);
	
	@Query("DELETE FROM CartItem c WHERE c.customer.customerId = ?1")
	@Modifying 
	public void deleteByCustomer(Integer customerId);
}
