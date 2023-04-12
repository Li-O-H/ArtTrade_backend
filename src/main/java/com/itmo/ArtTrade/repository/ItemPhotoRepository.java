package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.ItemPhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPhotoRepository extends CrudRepository<ItemPhoto, Long> {
}
