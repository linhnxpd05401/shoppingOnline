package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.OrderDetail;
import edu.poly.shop.repository.OrderDetailReponsitory;
import edu.poly.shop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	OrderDetailReponsitory orderDetailReponsitory;

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return orderDetailReponsitory.save(entity);
	}

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailReponsitory.findAll();
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return orderDetailReponsitory.findAll(pageable);
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return orderDetailReponsitory.findAll(sort);
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Integer> ids) {
		return orderDetailReponsitory.findAllById(ids);
	}

	@Override
	public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderDetailReponsitory.findAll(example, pageable);
	}

	@Override
	public Optional<OrderDetail> findById(Integer id) {
		return orderDetailReponsitory.findById(id);
	}

	@Override
	public long count() {
		return orderDetailReponsitory.count();
	}

	@Override
	public void deleteById(Integer id) {
		orderDetailReponsitory.deleteById(id);
	}

	@Override
	public void delete(OrderDetail entity) {
		orderDetailReponsitory.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		orderDetailReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAll() {
		orderDetailReponsitory.deleteAll();
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example) {
		return orderDetailReponsitory.findAll(example);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return orderDetailReponsitory.findAll(example, sort);
	}
	
	
}
