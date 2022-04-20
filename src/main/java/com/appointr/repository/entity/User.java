package com.appointr.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Length(min = 6, max = 20)
    @Column(name = "password")
    private String password;

    @Builder.Default
    @Column(length = 20, name = "role", columnDefinition = "VARCHAR(20) default 'CUSTOMER' ")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.CUSTOMER;
}
