package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.entity.OrderBid;
import com.itmo.ArtTrade.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBidRepository extends CrudRepository<OrderBid, Long> {

    List<OrderBid> findAllByUser(User user);

    List<OrderBid> findAllByOrder(Order order);
}
