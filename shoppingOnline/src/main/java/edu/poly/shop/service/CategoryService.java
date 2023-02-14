package edu.poly.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.shop.domain.Category;

public interface CategoryService {

	void deleteAll();

	Category getReferenceById(Integer id);

	void deleteAll(Iterable<? extends Category> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Category getById(Integer id);

	void delete(Category entity);

	Category getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Category> boolean exists(Example<S> example);

	void deleteAllInBatch(Iterable<Category> entities);

	boolean existsById(Integer id);

	Optional<Category> findById(Integer id);

	<S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Category> S saveAndFlush(S entity);

	void flush();

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	List<Category> findAllById(Iterable<Integer> ids);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> Optional<S> findOne(Example<S> example);

	<S extends Category> S save(S entity);
	
	Page<Category> findPaginated(int pageNo, int pageSize);
	
}
