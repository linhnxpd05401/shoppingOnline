package edu.poly.shop.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import edu.poly.shop.domain.CartItem;
import edu.poly.shop.domain.Customer;



public interface CartItemsService {

	<S extends CartItem> List<S> findAll(Example<S> example, Sort sort);

	<S extends CartItem> List<S> findAll(Example<S> example);

	void deleteAll();

	CartItem getReferenceById(Integer id);

	void deleteAll(Iterable<? extends CartItem> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	CartItem getById(Integer id);

	void delete(CartItem entity);

	CartItem getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	<S extends CartItem, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends CartItem> boolean exists(Example<S> example);

	void deleteAllInBatch(Iterable<CartItem> entities);

	boolean existsById(Integer id);

	Optional<CartItem> findById(Integer id);

	<S extends CartItem> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends CartItem> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends CartItem> S saveAndFlush(S entity);

	void flush();

	<S extends CartItem> List<S> saveAll(Iterable<S> entities);

	List<CartItem> findAllById(Iterable<Integer> ids);

	List<CartItem> findAll(Sort sort);

	Page<CartItem> findAll(Pageable pageable);

	List<CartItem> findAll();

	<S extends CartItem> Optional<S> findOne(Example<S> example);

	<S extends CartItem> S save(S entity);

	List<CartItem> findByCustomer(Customer customerId);

	Integer addProduct(Integer productId, Integer quantity, Customer customer);

	Page<CartItem> findPaginated(int pageNo, int pageSize);

	Integer countByCustomer(Customer customer);

	double updateQuantity(Integer productId, Integer quantity, Customer customer);

	void deleteByCustomerAndProduct(Integer customerId, Integer productId);

	void deleteByCustomer(Integer customerId);

}
