package com.appointr.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "title")
    private String title;

    @Length(min = 10, max = 50)
    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "creator", referencedColumnName = "id")
    private User creator;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private User customer;

    @Column(name = "booking_date")
    private String bookingDate;

}
