package edu.poly.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.shop.domain.Delivery;

public interface DeliveryService {

	<S extends Delivery> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

	void deleteAllById(Iterable<? extends Integer> ids);

	long count();

	Optional<Delivery> findById(Integer id);

	<S extends Delivery> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Delivery> findAll(Sort sort);

	Page<Delivery> findAll(Pageable pageable);

	List<Delivery> findAll();

	<S extends Delivery> S save(S entity);

}
