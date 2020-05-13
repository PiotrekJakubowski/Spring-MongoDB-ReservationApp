package com.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.model.Reservation;

@Repository
public interface ReservationRespsitory extends MongoRepository<Reservation, String>{

	@Query("{'reservation.id': ?0}")
	List <Reservation> findReservationId(String reservationId);
	
}
