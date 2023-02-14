package edu.poly.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.shop.domain.OrderDetail;

public interface OrderDetailService {

	<S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort);

	<S extends OrderDetail> List<S> findAll(Example<S> example);

	void deleteAll();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(OrderDetail entity);

	void deleteById(Integer id);

	long count();

	Optional<OrderDetail> findById(Integer id);

	<S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable);

	List<OrderDetail> findAllById(Iterable<Integer> ids);

	List<OrderDetail> findAll(Sort sort);

	Page<OrderDetail> findAll(Pageable pageable);

	List<OrderDetail> findAll();

	<S extends OrderDetail> S save(S entity);

}
