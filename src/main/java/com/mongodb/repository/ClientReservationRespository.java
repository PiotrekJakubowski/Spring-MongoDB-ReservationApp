package com.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.model.Client;

@Repository
public interface ClientReservationRespository extends MongoRepository<Client, String> {
  List<Client> findByEmail(String email);
}
