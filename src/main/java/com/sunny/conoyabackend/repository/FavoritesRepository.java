package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.domain.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
}
