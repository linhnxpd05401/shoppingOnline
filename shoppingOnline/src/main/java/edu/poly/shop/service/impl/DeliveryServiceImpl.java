package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.Delivery;
import edu.poly.shop.repository.DeliveryReponsitory;
import edu.poly.shop.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService{
	@Autowired
	DeliveryReponsitory deliveryReponsitory;

	@Override
	public <S extends Delivery> S save(S entity) {
		return deliveryReponsitory.save(entity);
	}

	@Override
	public List<Delivery> findAll() {
		return deliveryReponsitory.findAll();
	}

	@Override
	public Page<Delivery> findAll(Pageable pageable) {
		return deliveryReponsitory.findAll(pageable);
	}

	@Override
	public List<Delivery> findAll(Sort sort) {
		return deliveryReponsitory.findAll(sort);
	}

	@Override
	public <S extends Delivery> Page<S> findAll(Example<S> example, Pageable pageable) {
		return deliveryReponsitory.findAll(example, pageable);
	}

	@Override
	public Optional<Delivery> findById(Integer id) {
		return deliveryReponsitory.findById(id);
	}

	@Override
	public long count() {
		return deliveryReponsitory.count();
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		deliveryReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAll() {
		deliveryReponsitory.deleteAll();
	}

	@Override
	public <S extends Delivery> List<S> findAll(Example<S> example, Sort sort) {
		return deliveryReponsitory.findAll(example, sort);
	}
	
	
}
