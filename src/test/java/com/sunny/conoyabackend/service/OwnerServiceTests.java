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
    @DisplayName("점주 정보 업데이트 테스트")
    public void test_updateStoreInfo() {
        OwnerEntity initialOwner = OwnerEntity.builder()
                .ownerEmail("initial@example.com")
                .ownerNum("1234567890")
                .ownerPassword("password123")
                .storeName("Initial Karaoke")
                .location("Initial Location")
                .imageUrl("http://example.com/initial.jpg")
                .description("Initial description")
                .build();
        ownerRepository.save(initialOwner);

        OwnerDTO updateInfo = OwnerDTO.builder()
                .storeName("Updated Karaoke")
                .location("Updated Location")
                .description("Updated description")
                .imageUrl("http://example.com/updated.jpg")
                .build();

        OwnerEntity updatedOwner = ownerService.updateStoreInfo(initialOwner.getOwnerId(), updateInfo);

        assertThat(updatedOwner.getStoreName()).isEqualTo("Updated Karaoke");
        assertThat(updatedOwner.getLocation()).isEqualTo("Updated Location");
        assertThat(updatedOwner.getDescription()).isEqualTo("Updated description");
        assertThat(updatedOwner.getImageUrl()).isEqualTo("http://example.com/updated.jpg");
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


