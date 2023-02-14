package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.repository.UserReponsitory;
import edu.poly.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserReponsitory userReponsitory;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Customer loggin(String email, String password) {
		Customer customer = userReponsitory.findByEmail(email);
		if (customer != null && bCryptPasswordEncoder.matches(password, customer.getPassword())) {
			customer.setPassword("");
			return customer;
		}
		return null;
	}

	@Override
	public Customer findByEmail(String email) {
		Customer customer = userReponsitory.findByEmail(email);
		if (customer != null) {
			return customer;
		}
		return null;
	}

	@Override
	public <S extends Customer> S save(S entity) {
		return userReponsitory.save(entity);
	}

	@Override
	public <S extends Customer> Optional<S> findOne(Example<S> example) {
		return userReponsitory.findOne(example);
	}

	@Override
	public List<Customer> findAll() {
		return userReponsitory.findAll();
	}

	@Override
	public Page<Customer> findAll(Pageable pageable) {
		return userReponsitory.findAll(pageable);
	}

	@Override
	public List<Customer> findAll(Sort sort) {
		return userReponsitory.findAll(sort);
	}

	@Override
	public List<Customer> findAllById(Iterable<Integer> ids) {
		return userReponsitory.findAllById(ids);
	}

	@Override
	public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
		return userReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		userReponsitory.flush();
	}

	@Override
	public <S extends Customer> S saveAndFlush(S entity) {
		return userReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userReponsitory.findAll(example, pageable);
	}

	@Override
	public Optional<Customer> findById(Integer id) {
		return userReponsitory.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return userReponsitory.existsById(id);
	}

	@Override
	public <S extends Customer> long count(Example<S> example) {
		return userReponsitory.count(example);
	}

	@Override
	public <S extends Customer> boolean exists(Example<S> example) {
		return userReponsitory.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		userReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Customer, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userReponsitory.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return userReponsitory.count();
	}

	@Override
	public Customer getOne(Integer id) {
		return userReponsitory.getOne(id);
	}

	@Override
	public void delete(Customer entity) {
		userReponsitory.delete(entity);
	}

	@Override
	public Customer getById(Integer id) {
		return userReponsitory.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		userReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Customer> entities) {
		userReponsitory.deleteAll(entities);
	}

	@Override
	public Customer getReferenceById(Integer id) {
		return userReponsitory.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		userReponsitory.deleteAll();
	}

	@Override
	public <S extends Customer> List<S> findAll(Example<S> example) {
		return userReponsitory.findAll(example);
	}

	@Override
	public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
		return userReponsitory.findAll(example, sort);
	}

}
