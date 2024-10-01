package com.sunny.conoyabackend.service;


import com.sunny.conoyabackend.dto.JoinRequest;
import com.sunny.conoyabackend.dto.LoginRequest;
import com.sunny.conoyabackend.entity.Owner;
import com.sunny.conoyabackend.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public boolean checkLoginIdDuplicate2(String ownerEmail) {
        return ownerRepository.existsByownerEmail(ownerEmail);
    }

    public boolean checkNicknameDuplicate(String ownerNickname) {
        return ownerRepository.existsByownerNickname(ownerNickname);
    }

    public void join2(JoinRequest ownerReq) {
        ownerRepository.save(ownerReq.ownerEntity());
    }

    public Owner login2(LoginRequest ownerReq) {
        Optional<Owner> optionalOwner = ownerRepository.findByownerEmail(ownerReq.getOwnerEmail());

        if(optionalOwner.isEmpty()) {
            return null;
        }

        Owner owner = optionalOwner.get();

        if (!owner.getOwnerPassword().equals(ownerReq.getOwnerPassword())) {
            return null;
        }
        return owner;
    }

    public Owner getLoginOwnerById(Long ownerId) {
        if(ownerId == null) return null;

        Optional<Owner> optionalOwner = ownerRepository.findById(ownerId);
        if(optionalOwner.isEmpty()) return null;

        return optionalOwner.get();
    }

    public Owner getLoginOwnerByLoginId(String ownerEmail) {
        if(ownerEmail == null) return null;

        Optional<Owner> optionalOwner = ownerRepository.findByownerEmail(ownerEmail);
        if(optionalOwner.isEmpty()) return null;

        return optionalOwner.get();
    }
}
