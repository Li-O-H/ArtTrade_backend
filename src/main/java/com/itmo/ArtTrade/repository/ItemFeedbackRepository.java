package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.ItemFeedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFeedbackRepository extends CrudRepository<ItemFeedback, Long> {
}
