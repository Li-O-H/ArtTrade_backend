package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.UserPhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotoRepository extends CrudRepository<UserPhoto, Long> {
}
