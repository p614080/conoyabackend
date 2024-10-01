package com.sunny.conoyabackend.Service;


import com.sunny.conoyabackend.DTO.JoinRequest;
import com.sunny.conoyabackend.DTO.LoginRequest;
import com.sunny.conoyabackend.Entity.User;
import com.sunny.conoyabackend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /*
     * LoginEmail 중복체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkLoginEmailDuplicate(String userEmail) {
        return userRepository.existByLoginEmail(userEmail);
    }

    /**
     *  nickname 중복체크
     *  회원가입 기능 구현 시 사용
     *  중복되면 true return
     */
    public boolean checkNicknameDuplicate(String userNickname) {
        return userRepository.existByNickname(userNickname);
    }

    /**
     * 회원가입 기능
     * 화면에서 JoinRequest 입력받아 User로 변환 후 저장
     * LoginId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위함
     */
    public void join(JoinRequest userReq) {
        userRepository.save(userReq.userEntity());
    }

    /**
     * 로그인 기능
     * 화면에서 LoginRequest을 입력받아 LoginEmail과 password가 일치하면 User return
     * Id가 존재하지 않거나 password가 일치하지 않으면 null return
     */
    public User login(LoginRequest userReq){
        Optional<User> optionalUser = userRepository.findByLoginEmail(userReq.getUserEmail());

        //LoginEmail와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();

        //찾아온 User의 password와 입력된 password가 다르면 null return
        if(!user.getUserPassword().equals(userReq.getUserPassword())) {
            return null;
        }
        return user;
    }

    /**
     * userId(Long)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * userId가 null이거나(로그인x) userId로 찾아온 User가 없으면 null return
     * userId로 찾아온 User가 존재하면 User return
     */
    public User getLoginUserBy(Long userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

}

