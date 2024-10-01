package com.sunny.conoyabackend.Service;


import com.sunny.conoyabackend.DTO.JoinRequest;
import com.sunny.conoyabackend.DTO.LoginRequest;
import com.sunny.conoyabackend.Entity.Owner;
import com.sunny.conoyabackend.Repository.OwnerRepository;
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
        return ownerRepository.existByLoginEmail(ownerEmail);
    }

    public boolean checkNicknameDuplicate(String ownerNickname) {
        return ownerRepository.existByNickname(ownerNickname);
    }

    public void join2(JoinRequest ownerReq) {
        ownerRepository.save(ownerReq.ownerEntity());
    }

    public Owner login2(LoginRequest ownerReq) {
        Optional<Owner> optionalOwner = ownerRepository.findByLoginEmail(ownerReq.getOwnerEmail());

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

        Optional<Owner> optionalOwner = ownerRepository.findByLoginEmail(ownerEmail);
        if(optionalOwner.isEmpty()) return null;

        return optionalOwner.get();
    }
}
