package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.OrderFeedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFeedbackRepository extends CrudRepository<OrderFeedback, Long> {
}
