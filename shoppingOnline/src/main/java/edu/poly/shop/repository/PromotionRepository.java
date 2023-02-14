package edu.poly.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.poly.shop.domain.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer>{
	
}
