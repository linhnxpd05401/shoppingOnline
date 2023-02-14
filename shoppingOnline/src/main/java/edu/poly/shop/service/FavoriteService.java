package edu.poly.shop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Favorite;

public interface FavoriteService {

	void delete(Favorite entity);

	void deleteById(Integer id);

	long count();

	Optional<Favorite> findById(Integer id);

	<S extends Favorite> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Favorite> findAll(Sort sort);

	Page<Favorite> findAll(Pageable pageable);

	List<Favorite> findAll();

	<S extends Favorite> S save(S entity);

	List<Favorite> findByCustomer(Customer customer);

	List<Favorite> findByCustomerAndProduct(Integer customerId, Integer productId);

}
