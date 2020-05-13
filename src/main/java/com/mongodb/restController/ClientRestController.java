package com.mongodb.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.model.Client;
import com.mongodb.model.Reservation;
import com.mongodb.repository.ClientReservationRespository;

@RestController
@RequestMapping("/api/client")
public class ClientRestController {

	@Autowired
	ClientReservationRespository clientReservationRepository;
	
	@PostMapping("/")
	public ResponseEntity<Client> createClient(@RequestBody Client reqClient) {
		System.out.println("ReqClient: " + reqClient);

		try {
			List <Reservation> reservationList = new ArrayList<>();
			reservationList = reqClient.getReservation();
			
			Client client = new Client(reqClient.getFirstName(), reqClient.getLastName(), reqClient.getPhoneNumber(),
					reqClient.getEmail(), reservationList);

			Client saveClient = clientReservationRepository.save(client);

			return new ResponseEntity<>(saveClient, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}

	}
	
}
