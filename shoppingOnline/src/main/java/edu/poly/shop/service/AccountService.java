package edu.poly.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.shop.domain.Account;

public interface AccountService {

	Page<Account> findPaginated(int pageNo, int pageSize);

	<S extends Account> List<S> findAll(Example<S> example, Sort sort);

	<S extends Account> List<S> findAll(Example<S> example);

	void deleteAll();

	Account getById(String id);

	void delete(Account entity);

	void deleteById(String id);

	void deleteAllInBatch();

	long count();

	<S extends Account> boolean exists(Example<S> example);

	<S extends Account> long count(Example<S> example);

	boolean existsById(String id);

	Optional<Account> findById(String id);

	<S extends Account> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Account> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Account> S saveAndFlush(S entity);

	void flush();

	<S extends Account> List<S> saveAll(Iterable<S> entities);

	List<Account> findAllById(Iterable<String> ids);

	List<Account> findAll(Sort sort);

	Page<Account> findAll(Pageable pageable);

	List<Account> findAll();

	<S extends Account> Optional<S> findOne(Example<S> example);

	<S extends Account> S save(S entity);

	Account loggin(String username, String password);

}
