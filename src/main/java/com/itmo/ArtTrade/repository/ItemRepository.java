package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.Category;
import com.itmo.ArtTrade.entity.Item;
import com.itmo.ArtTrade.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findAllByStatus(Status status);

    List<Item> findAllByStatusAndCategory(Status status, Category category);
}
