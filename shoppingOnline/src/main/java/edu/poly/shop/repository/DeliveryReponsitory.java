package edu.poly.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Delivery;

@Repository
public interface DeliveryReponsitory extends JpaRepository<Delivery, Integer>{

}
