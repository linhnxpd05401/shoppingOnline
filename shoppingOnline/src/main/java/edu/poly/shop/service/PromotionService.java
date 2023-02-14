package edu.poly.shop.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import edu.poly.shop.domain.Promotion;

public interface PromotionService{

	<S extends Promotion> List<S> findAll(Example<S> example, Sort sort);

	<S extends Promotion> List<S> findAll(Example<S> example);

	void deleteAll();

	Promotion getReferenceById(Integer id);

	void deleteAll(Iterable<? extends Promotion> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	Promotion getById(Integer id);

	void delete(Promotion entity);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	<S extends Promotion, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteAllInBatch(Iterable<Promotion> entities);

	<S extends Promotion> long count(Example<S> example);

	boolean existsById(Integer id);

	Optional<Promotion> findById(Integer id);

	<S extends Promotion> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Promotion> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Promotion> S saveAndFlush(S entity);

	void flush();

	<S extends Promotion> List<S> saveAll(Iterable<S> entities);

	List<Promotion> findAllById(Iterable<Integer> ids);

	List<Promotion> findAll(Sort sort);

	Page<Promotion> findAll(Pageable pageable);

	List<Promotion> findAll();

	<S extends Promotion> Optional<S> findOne(Example<S> example);

	<S extends Promotion> S save(S entity);

	Page<Promotion> findPaginated(int pageNo, int pageSize);
	
}
