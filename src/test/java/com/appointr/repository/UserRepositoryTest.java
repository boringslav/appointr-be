package com.appointr.repository;

import com.appointr.dto.user.GetAllUsersResponseDTO;
import com.appointr.dto.user.UserDTO;
import com.appointr.repository.entity.User;
import com.appointr.repository.entity.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired

    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findUserByEmail_shouldReturnUser_whenItExists() {

        entityManager.persist(User.builder()
                .email("bostoycontact@gmail.com")
                .name("Borislav Stoyanov")
                .password("123123")
                .role(UserRole.ADMIN)
                .build());

        User actualUser = userRepository.findUserByEmail("bostoycontact@gmail.com");

        User expectedUser = User.builder()
                .email("bostoycontact@gmail.com")
                .name("Borislav Stoyanov")
                .password("123123")
                .role(UserRole.ADMIN)
                .build();


        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getRole(), actualUser.getRole());

    }
}