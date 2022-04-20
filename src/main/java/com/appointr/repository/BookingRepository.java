package com.appointr.repository;

import com.appointr.repository.entity.Booking;
import com.sun.istack.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
 List<Booking> findBookingsByCreatorId(@NotNull  Long id);
}
