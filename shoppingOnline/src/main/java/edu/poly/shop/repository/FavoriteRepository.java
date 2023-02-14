package edu.poly.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

	List<Favorite> findByCustomer(Customer customer);

	@Query("SELECT f FROM Favorite f WHERE f.customer.customerId = ?1 AND f.product.productId = ?2")
	@Modifying
	List<Favorite> findByCustomerAndProduct(Integer customerId, Integer productId);
}
