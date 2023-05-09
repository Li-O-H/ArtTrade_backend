package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.Category;
import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.Status;
import com.itmo.ArtTrade.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAllByStatus(Status status);

    List<Item> findAllByStatusAndCategory(Status status, Category category);

    List<Item> findAllByUserAndStatusNot(User user, Status status);

    List<Item> findAllByUser(User user);
}
