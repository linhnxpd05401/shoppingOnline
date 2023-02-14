package edu.poly.shop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.shop.domain.Order;

public interface OrderService {

	<S extends Order> List<S> findAll(Example<S> example, Sort sort);

	<S extends Order> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Order> entities);

	void delete(Order entity);

	void deleteById(Integer id);

	long count();

	Optional<Order> findById(Integer id);

	<S extends Order> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Order> findAllById(Iterable<Integer> ids);

	List<Order> findAll(Sort sort);

	Page<Order> findAll(Pageable pageable);

	List<Order> findAll();

	<S extends Order> S save(S entity);

	List<Order> findByCustomerAndDate(Integer customerId, Date orderedDate);

	List<Order> findByCustomer(Integer customerId);

}
