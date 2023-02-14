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

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Favorite;
import edu.poly.shop.repository.FavoriteRepository;
import edu.poly.shop.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService{
	
	@Autowired
	FavoriteRepository favoriteRepository;

	@Override
	public <S extends Favorite> S save(S entity) {
		return favoriteRepository.save(entity);
	}

	@Override
	public List<Favorite> findAll() {
		return favoriteRepository.findAll();
	}

	@Override
	public Page<Favorite> findAll(Pageable pageable) {
		return favoriteRepository.findAll(pageable);
	}

	@Override
	public List<Favorite> findAll(Sort sort) {
		return favoriteRepository.findAll(sort);
	}

	@Override
	public <S extends Favorite> Page<S> findAll(Example<S> example, Pageable pageable) {
		return favoriteRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Favorite> findById(Integer id) {
		return favoriteRepository.findById(id);
	}

	@Override
	public long count() {
		return favoriteRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		favoriteRepository.deleteById(id);
	}

	@Override
	public void delete(Favorite entity) {
		favoriteRepository.delete(entity);
	}
	
	@Override
	public List<Favorite> findByCustomer(Customer customer) {
		return favoriteRepository.findByCustomer(customer);
	}
	
	@Override
	public List<Favorite> findByCustomerAndProduct(Integer customerId, Integer productId) {
		return favoriteRepository.findByCustomerAndProduct(customerId, productId);
	}
	
	
}
