package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.OrderPhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPhotoRepository extends CrudRepository<OrderPhoto, Long> {
}
