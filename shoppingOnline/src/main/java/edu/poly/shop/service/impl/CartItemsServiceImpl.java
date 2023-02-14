package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.CartItem;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Product;
import edu.poly.shop.repository.CartItemsReponsitory;
import edu.poly.shop.repository.ProductRepository;
import edu.poly.shop.service.CartItemsService;


@Service
@Transactional
public class CartItemsServiceImpl implements CartItemsService{
	
	@Autowired
	private CartItemsReponsitory cartItemsReponsitory;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public <S extends CartItem> S save(S entity) {
		return cartItemsReponsitory.save(entity);
	}

	@Override
	public <S extends CartItem> Optional<S> findOne(Example<S> example) {
		return cartItemsReponsitory.findOne(example);
	}

	@Override
	public List<CartItem> findAll() {
		return cartItemsReponsitory.findAll();
	}
	
	@Override
	public Integer addProduct(Integer productId, Integer quantity, Customer customer) {
		Integer addedQuantity = quantity;
		
		Product product = productRepository.findById(productId).get();
		
		CartItem cartItem = cartItemsReponsitory.findByCustomerAndProduct(customer, product);
		
		if(cartItem != null) {
			addedQuantity = cartItem.getQuantity() + quantity;
			cartItem.setQuantity(addedQuantity);
		}else {
			cartItem = new CartItem();
			cartItem.setQuantity(quantity);
			cartItem.setCustomer(customer);
			cartItem.setProduct(product);
		}
		
		cartItemsReponsitory.save(cartItem);
		
		return addedQuantity;
	}
	

	@Override
	public List<CartItem> findByCustomer(Customer customer) {
		return cartItemsReponsitory.findByCustomer(customer);
	}


	@Override
	public Page<CartItem> findAll(Pageable pageable) {
		return cartItemsReponsitory.findAll(pageable);
	}

	@Override
	public List<CartItem> findAll(Sort sort) {
		return cartItemsReponsitory.findAll(sort);
	}

	@Override
	public List<CartItem> findAllById(Iterable<Integer> ids) {
		return cartItemsReponsitory.findAllById(ids);
	}

	@Override
	public <S extends CartItem> List<S> saveAll(Iterable<S> entities) {
		return cartItemsReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		cartItemsReponsitory.flush();
	}

	@Override
	public <S extends CartItem> S saveAndFlush(S entity) {
		return cartItemsReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends CartItem> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cartItemsReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends CartItem> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cartItemsReponsitory.findAll(example, pageable);
	}

	@Override
	public Optional<CartItem> findById(Integer id) {
		return cartItemsReponsitory.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return cartItemsReponsitory.existsById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<CartItem> entities) {
		cartItemsReponsitory.deleteAllInBatch(entities);
	}

	@Override
	public <S extends CartItem> boolean exists(Example<S> example) {
		return cartItemsReponsitory.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		cartItemsReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends CartItem, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return cartItemsReponsitory.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return cartItemsReponsitory.count();
	}

	@Override
	public void deleteAllInBatch() {
		cartItemsReponsitory.deleteAllInBatch();
	}

	@Override
	public void deleteById(Integer id) {
		cartItemsReponsitory.deleteById(id);
	}

	@Override
	public CartItem getOne(Integer id) {
		return cartItemsReponsitory.getOne(id);
	}

	@Override
	public void delete(CartItem entity) {
		cartItemsReponsitory.delete(entity);
	}

	@Override
	public CartItem getById(Integer id) {
		return cartItemsReponsitory.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		cartItemsReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends CartItem> entities) {
		cartItemsReponsitory.deleteAll(entities);
	}

	@Override
	public CartItem getReferenceById(Integer id) {
		return cartItemsReponsitory.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		cartItemsReponsitory.deleteAll();
	}

	@Override
	public <S extends CartItem> List<S> findAll(Example<S> example) {
		return cartItemsReponsitory.findAll(example);
	}

	@Override
	public <S extends CartItem> List<S> findAll(Example<S> example, Sort sort) {
		return cartItemsReponsitory.findAll(example, sort);
	}
	
	@Override
	public Integer countByCustomer(Customer customer) {
		return cartItemsReponsitory.countByCustomer(customer);
	}
	
	@Override
	public Page<CartItem> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		return this.cartItemsReponsitory.findAll(pageable);
	}
	
	@Override
	public double updateQuantity(Integer productId, Integer quantity, Customer customer) {
		cartItemsReponsitory.updateQuantity(quantity, productId, customer.getCustomerId());
		Product product = productRepository.findById(productId).get();
		
		double subTotal = product.getUnitPrice() * quantity;
		return subTotal;
	}
	
	@Override
	public void deleteByCustomerAndProduct(Integer customerId, Integer productId) {
		cartItemsReponsitory.deleteByCustomerAndProduct(customerId, productId);
	}
	
	@Override
	public void deleteByCustomer(Integer customerId) {
		cartItemsReponsitory.deleteByCustomer(customerId);
	}
	
	
}
