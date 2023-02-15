package edu.poly.shop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Product;


public interface ProductService{

	void deleteAll();

	Product getReferenceById(Integer id);

	void deleteAll(Iterable<? extends Product> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Product getById(Integer id);

	void delete(Product entity);

	Product getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Product> boolean exists(Example<S> example);

	void deleteAllInBatch(Iterable<Product> entities);

	boolean existsById(Integer id);

	Optional<Product> findById(Integer id);

	<S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Product> S saveAndFlush(S entity);

	void flush();

	<S extends Product> List<S> saveAll(Iterable<S> entities);

	List<Product> findAllById(Iterable<Integer> ids);

	List<Product> findAll(Sort sort);

	Page<Product> findAll(Pageable pageable);

	List<Product> findAll();

	<S extends Product> Optional<S> findOne(Example<S> example);

	<S extends Product> S save(S entity);
	
	Page<Product> findPaginated(int pageNo, int pageSize);

	List<Product> findByNameContaining(String name);

	Page<Product> findByNameContaining(String name, Pageable pageable);

	Page<Product> findMoreItem(int pageSize);

	List<Product> findByCategory(Category category);
	
}
