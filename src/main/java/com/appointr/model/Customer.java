package com.appointr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "firstName")
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "lastName")
    private String lastName;

    @NotBlank
    @Email
    @Column(name="email")
    private String email;

    @NotBlank
    @Length(min=6, max=20)
    @Column(name="password")
    private String password;
}
