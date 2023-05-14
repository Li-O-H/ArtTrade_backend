package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.ItemBid;
import com.itmo.ArtTrade.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemBidRepository extends CrudRepository<ItemBid, Long> {

    List<ItemBid> findAllByUser(User user);

    List<ItemBid> findAllByItem(Item item);
}
