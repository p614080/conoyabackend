package com.sunny.conoyabackend.service;


import com.sunny.conoyabackend.dto.OwnerDTO;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.repository.OwnerRepository;
import com.sunny.conoyabackend.service.OwnerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class OwnerServiceTests {

    private static final Logger log = LoggerFactory.getLogger(OwnerServiceTests.class);

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private OwnerService ownerService;

//    @Test
//    @DisplayName("점주 정보 수정 테스트")
//    @Transactional
//    public void test_updateStoreInfo() {
//        // Given: Initial data setup
//        OwnerEntity initialOwner = OwnerEntity.builder()
//                .ownerEmail("initial@example.com")
//                .ownerNum("1234567890")
//                .ownerPassword("password123")
//                .storeName("Initial Karaoke")
//                .imageUrl("http://example.com/initial.jpg")
//                .description("Initial description")
//                .build();
//
//        OwnerEntity savedOwner = ownerRepository.save(initialOwner);
//
//        // When: Updating store info
//        OwnerDTO updateDTO = new OwnerDTO();
//        updateDTO.setOwnerEmail("updated@example.com");
//        updateDTO.setStoreName("Updated Karaoke");
//        updateDTO.setImageUrl("http://example.com/updated.jpg");
//        updateDTO.setDescription("Updated description");
//
//        OwnerEntity updatedOwner = ownerService.updateStoreInfo(savedOwner.getOwnerId(), updateDTO);
//
//        // Then: Verify the update
//        assertThat(updatedOwner.getOwnerEmail()).isEqualTo("updated@example.com");
//        assertThat(updatedOwner.getStoreName()).isEqualTo("Updated Karaoke");
//        assertThat(updatedOwner.getImageUrl()).isEqualTo("http://example.com/updated.jpg");
//        assertThat(updatedOwner.getDescription()).isEqualTo("Updated description");
//
//        log.info("Updated Owner Entity: {}", updatedOwner);
//    }


    @Test
    @DisplayName("초기 소유자 생성 테스트")
    public void test_createOwner() {
        OwnerEntity newOwner = OwnerEntity.builder()
                .ownerEmail("new@example.com")
                .ownerNum("0987654321")
                .ownerPassword("newpassword123")
                .storeName("New Karaoke")
                .location("New Location")
                .imageUrl("http://example.com/new.jpg")
                .description("New description")
                .build();
        OwnerEntity savedOwner = ownerRepository.save(newOwner);

        // 생성 결과 검증
        assertThat(savedOwner.getOwnerId()).isNotNull();
        assertThat(savedOwner.getStoreName()).isEqualTo("New Karaoke");
        assertThat(savedOwner.getLocation()).isEqualTo("New Location");
        assertThat(savedOwner.getImageUrl()).isEqualTo("http://example.com/new.jpg");
        assertThat(savedOwner.getDescription()).isEqualTo("New description");
    }

    @Test
    @DisplayName("소유자 정보 수정 테스트")
    public void test_updateStoreInfo() {
        OwnerEntity initialOwner = OwnerEntity.builder()
                .ownerEmail("initial@example.com")
                .ownerNum("1234567890")
                .ownerPassword("password123")
                .storeName("Initial conoya")
                .location("Initial Location")
                .imageUrl("http://example.com/initial.jpg")
                .description("Initial description")
                .build();
        ownerRepository.save(initialOwner);

        // 업데이트할 정보가 담긴 DTO 생성
        OwnerDTO updateInfo = OwnerDTO.builder()
                .storeName("Updated conoya")
                .location("Updated Location")
                .description("Updated description")
                .imageUrl("http://example.com/updated.jpg")
                .build();

        // 서비스 메서드를 호출하여 업데이트 수행
        OwnerEntity updatedOwner = ownerService.updateStoreInfo(initialOwner.getOwnerId(), updateInfo);

        // 업데이트 결과 검증
        assertThat(updatedOwner.getStoreName()).isEqualTo("Updated conoya");
        assertThat(updatedOwner.getLocation()).isEqualTo("Updated Location");
        assertThat(updatedOwner.getDescription()).isEqualTo("Updated description");
        assertThat(updatedOwner.getImageUrl()).isEqualTo("http://example.com/updated.jpg");
    }

    @Test
    @DisplayName("소유자 조회 테스트")
    public void test_readOwnerInfo() {
        OwnerEntity owner = OwnerEntity.builder()
                .ownerEmail("read@example.com")
                .ownerNum("1122334455")
                .ownerPassword("readpassword123")
                .storeName("Read conoya")
                .location("Read Location")
                .imageUrl("http://example.com/read.jpg")
                .description("Read description")
                .build();
        OwnerEntity savedOwner = ownerRepository.save(owner);

        Optional<OwnerEntity> retrievedOwner = ownerRepository.findById(savedOwner.getOwnerId());

        // 조회 결과 검증
        assertThat(retrievedOwner).isPresent();
        assertThat(retrievedOwner.get().getStoreName()).isEqualTo("Read conoya");
        assertThat(retrievedOwner.get().getLocation()).isEqualTo("Read Location");
        assertThat(retrievedOwner.get().getImageUrl()).isEqualTo("http://example.com/read.jpg");
        assertThat(retrievedOwner.get().getDescription()).isEqualTo("Read description");
    }

    @Test
    @DisplayName("소유자 삭제 테스트 ")
    public void test_deleteOwner() {
        OwnerEntity owner = OwnerEntity.builder()
                .ownerEmail("delete@example.com")
                .ownerNum("5566778899")
                .ownerPassword("deletepassword123")
                .storeName("Delete conoya")
                .location("Delete Location")
                .imageUrl("http://example.com/delete.jpg")
                .description("Delete description")
                .build();
        OwnerEntity savedOwner = ownerRepository.save(owner);

        // 삭제 수행
        ownerRepository.deleteById(savedOwner.getOwnerId());

        // 삭제 결과 검증
        Optional<OwnerEntity> deletedOwner = ownerRepository.findById(savedOwner.getOwnerId());
        assertThat(deletedOwner).isNotPresent();
    }


    @Test
    @DisplayName("점주 정보 수정 실패 테스트 - 존재하지 않는 ID")
    @Transactional
    public void test_updateStoreInfo_nonExistentId() {
        // Given: Non-existent ownerId
        Long nonExistentId = 9999L;

        OwnerDTO updateDTO = new OwnerDTO();
        updateDTO.setOwnerEmail("nonexistent@example.com");
        updateDTO.setStoreName("Non-existent Karaoke");
        updateDTO.setImageUrl("http://example.com/nonexistent.jpg");
        updateDTO.setDescription("Non-existent description");

        // When & Then: Attempt to update and expect an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ownerService.updateStoreInfo(nonExistentId, updateDTO);
        });

        assertThat(exception.getMessage()).isEqualTo("Owner not found with id: " + nonExistentId);
    }
}


