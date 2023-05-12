package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.Category;
import com.itmo.ArtTrade.entity.Order;
import com.itmo.ArtTrade.entity.Status;
import com.itmo.ArtTrade.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByStatus(Status status);

    List<Order> findAllByStatusAndCategory(Status status, Category category);

    List<Order> findAllByUserAndStatusNot(User user, Status status);

    List<Order> findAllByUser(User user);

    List<Order> findAllByFavoriteOfContains(User user);
}
