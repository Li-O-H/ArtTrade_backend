package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.ItemBid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemBidRepository extends CrudRepository<ItemBid, Long> {
}
