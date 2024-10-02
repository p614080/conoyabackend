package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.domain.Favorites;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    Optional<Favorites> findByUserEntityAndOwnerEntity(UserEntity userEntity, OwnerEntity ownerEntity);
}
