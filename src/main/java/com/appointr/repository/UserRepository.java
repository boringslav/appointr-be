package com.appointr.repository;

import com.appointr.repository.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(@NotBlank @Email String email);
    Optional<User> findUserById(@NotBlank Long id);
}
