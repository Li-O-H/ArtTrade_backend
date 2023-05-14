package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.ItemFeedback;
import com.itmo.ArtTrade.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemFeedbackRepository extends CrudRepository<ItemFeedback, Long> {

    List<ItemFeedback> findAllByUser(User user);

    List<ItemFeedback> findAllByItem(Item item);
}
