package com.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.model.Client;
import com.mongodb.model.Reservation;

@Repository
public interface ClientReservationRespository extends MongoRepository<Client, String> {
	List<Client> findByEmail(String email);
	
	@Query("{'reservation.id': ?0}")
	List <Reservation> findClientByReservationId(String reservationId);
	
	@Query("{'firstName': ?0}")
	List <Client> findByFirstName(String firstName);

}
