package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.OrderFeedback;
import com.itmo.ArtTrade.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFeedbackRepository extends CrudRepository<OrderFeedback, Long> {

    List<OrderFeedback> findAllByUser(User user);

}
