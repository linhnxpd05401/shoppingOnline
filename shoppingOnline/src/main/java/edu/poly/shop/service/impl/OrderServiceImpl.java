package edu.poly.shop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.Order;
import edu.poly.shop.repository.OrderReponsitory;
import edu.poly.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderReponsitory orderReponsitory;

	@Override
	public <S extends Order> S save(S entity) {
		return orderReponsitory.save(entity);
	}

	@Override
	public List<Order> findAll() {
		return orderReponsitory.findAll();
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return orderReponsitory.findAll(pageable);
	}

	@Override
	public List<Order> findAll(Sort sort) {
		return orderReponsitory.findAll(sort);
	}

	@Override
	public List<Order> findAllById(Iterable<Integer> ids) {
		return orderReponsitory.findAllById(ids);
	}

	@Override
	public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderReponsitory.findAll(example, pageable);
	}

	@Override
	public Optional<Order> findById(Integer id) {
		return orderReponsitory.findById(id);
	}

	@Override
	public long count() {
		return orderReponsitory.count();
	}

	@Override
	public void deleteById(Integer id) {
		orderReponsitory.deleteById(id);
	}

	@Override
	public void delete(Order entity) {
		orderReponsitory.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Order> entities) {
		orderReponsitory.deleteAll(entities);
	}

	@Override
	public <S extends Order> List<S> findAll(Example<S> example) {
		return orderReponsitory.findAll(example);
	}

	@Override
	public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
		return orderReponsitory.findAll(example, sort);
	}
	
	@Override
	public List<Order> findByCustomerAndDate(Integer customerId, Date orderedDate) {
		return orderReponsitory.findByCustomerAndDate(customerId, orderedDate);
	}
	
	@Override
	public List<Order> findByCustomer(Integer customerId) {
		return orderReponsitory.findByCustomer(customerId);
	}
	
	
}
