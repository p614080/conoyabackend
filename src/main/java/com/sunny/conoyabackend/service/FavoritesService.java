package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.domain.Favorites;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.UserEntity;
import com.sunny.conoyabackend.repository.FavoritesRepository;
import com.sunny.conoyabackend.repository.OwnerRepository;
import com.sunny.conoyabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritesService {
    private final FavoritesRepository favoritesRepository;
    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;

    public void addFavorites(Long userId, Long ownerId){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        OwnerEntity ownerEntity = ownerRepository.findById(ownerId).orElseThrow();

        if(!favoritesRepository.findByUserEntityAndOwnerEntity(userEntity, ownerEntity).isPresent()){
            Favorites favorites = new Favorites();
            favorites.setUserEntity(userEntity);
            favorites.setOwnerEntity(ownerEntity);
            favoritesRepository.save(favorites);
        }
    }

    public void removeFavorites(Long userId, Long ownerId){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        OwnerEntity ownerEntity = ownerRepository.findById(ownerId).orElseThrow();

        Favorites favorites = favoritesRepository.findByUserEntityAndOwnerEntity(userEntity, ownerEntity).orElseThrow();
        favoritesRepository.delete(favorites);
    }
}
