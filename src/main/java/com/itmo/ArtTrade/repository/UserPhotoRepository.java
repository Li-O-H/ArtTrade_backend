package com.itmo.ArtTrade.repository;

import com.itmo.ArtTrade.entity.User;
import com.itmo.ArtTrade.entity.UserPhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPhotoRepository extends CrudRepository<UserPhoto, Long> {

    List<UserPhoto> findAllByUser(User user);
}
