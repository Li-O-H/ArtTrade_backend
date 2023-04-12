package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.OrderBid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBidRepository extends CrudRepository<OrderBid, Long> {
}
