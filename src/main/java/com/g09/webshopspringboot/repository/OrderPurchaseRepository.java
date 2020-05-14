package com.g09.webshopspringboot.repository;

import com.g09.webshopspringboot.domain.OrderPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPurchaseRepository extends JpaRepository<OrderPurchase, Long> {

    List<OrderPurchase> findAllByUserId(Long userId);
}
