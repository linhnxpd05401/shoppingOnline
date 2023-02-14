package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.Product;
import edu.poly.shop.domain.Promotion;
import edu.poly.shop.repository.ProductRepository;
import edu.poly.shop.repository.PromotionRepository;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	PromotionRepository promotionRepository;

	@Override
	public <S extends Promotion> S save(S entity) {
		return promotionRepository.save(entity);
	}

	@Override
	public <S extends Promotion> Optional<S> findOne(Example<S> example) {
		return promotionRepository.findOne(example);
	}

	@Override
	public List<Promotion> findAll() {
		return promotionRepository.findAll();
	}

	@Override
	public Page<Promotion> findAll(Pageable pageable) {
		return promotionRepository.findAll(pageable);
	}

	@Override
	public List<Promotion> findAll(Sort sort) {
		return promotionRepository.findAll(sort);
	}

	@Override
	public List<Promotion> findAllById(Iterable<Integer> ids) {
		return promotionRepository.findAllById(ids);
	}

	@Override
	public <S extends Promotion> List<S> saveAll(Iterable<S> entities) {
		return promotionRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		promotionRepository.flush();
	}

	@Override
	public <S extends Promotion> S saveAndFlush(S entity) {
		return promotionRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Promotion> List<S> saveAllAndFlush(Iterable<S> entities) {
		return promotionRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Promotion> Page<S> findAll(Example<S> example, Pageable pageable) {
		return promotionRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Promotion> findById(Integer id) {
		return promotionRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return promotionRepository.existsById(id);
	}

	@Override
	public <S extends Promotion> long count(Example<S> example) {
		return promotionRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Promotion> entities) {
		promotionRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		promotionRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Promotion, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return promotionRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return promotionRepository.count();
	}

	@Override
	public void deleteAllInBatch() {
		promotionRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Integer id) {
		promotionRepository.deleteById(id);
	}

	@Override
	public void delete(Promotion entity) {
		promotionRepository.delete(entity);
	}

	@Override
	public Promotion getById(Integer id) {
		return promotionRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		promotionRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Promotion> entities) {
		promotionRepository.deleteAll(entities);
	}

	@Override
	public Promotion getReferenceById(Integer id) {
		return promotionRepository.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		promotionRepository.deleteAll();
	}

	@Override
	public <S extends Promotion> List<S> findAll(Example<S> example) {
		return promotionRepository.findAll(example);
	}

	@Override
	public <S extends Promotion> List<S> findAll(Example<S> example, Sort sort) {
		return promotionRepository.findAll(example, sort);
	}
	
	@Override
	public Page<Promotion> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		return this.promotionRepository.findAll(pageable);
	}
	
}
