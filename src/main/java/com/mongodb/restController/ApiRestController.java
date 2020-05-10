package com.mongodb.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.model.Client;
import com.mongodb.model.Reservation;
import com.mongodb.repository.ClientReservationRespository;

@RestController
@RequestMapping("/api")
public class ApiRestController {

	@Autowired
	ClientReservationRespository clientReservationRepository;

	@PostMapping("/clientReservation")
	public ResponseEntity<Client> createClientReservation(@RequestBody Client reqClient) {
		System.out.println("createClientReservation function -> body parameters: " + reqClient.getFirstName() + " | "
				+ reqClient.getLastName() + " | " + reqClient.getEmail() + " | " + reqClient.getPhoneNumber() + " | "
				+ reqClient.getReservation().getCourt() + " | " + reqClient.getReservation().getDate());
		try {
			Reservation reservation = new Reservation(reqClient.getReservation().getCourt(),
					reqClient.getReservation().getDate());
			Client client = new Client(reqClient.getFirstName(), reqClient.getLastName(), reqClient.getPhoneNumber(),
					reqClient.getEmail(), reservation);

			Client saveClient = clientReservationRepository.save(client);

			return new ResponseEntity<>(saveClient, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/clientReservation")
	public List<Client> getAllClientReservations() {

		List<Client> clients = this.clientReservationRepository.findAll();

		System.out.println("getAllClientReservations function");

		return clients;
	}

	@GetMapping("/clientReservation/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") String id) {
		Optional<Client> clientData = clientReservationRepository.findById(id);

		if (clientData.isPresent()) {
			return new ResponseEntity<>(clientData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/clientReservation/{id}")
	public ResponseEntity<Client> updateClientReservation(@PathVariable("id") String id, @RequestBody Client client) {
		Optional<Client> clientData = clientReservationRepository.findById(id);

		if (clientData.isPresent()) {
			Client _client = clientData.get();
			Reservation _reservation = clientData.get().getReservation();
			if (client.getFirstName() != null)
				_client.setFirstName(client.getFirstName());
			if (client.getLastName() != null)
				_client.setLastName(client.getLastName());
			if (client.getEmail() != null)
				_client.setEmail(client.getEmail());
			if (client.getPhoneNumber() != null)
				_client.setPhoneNumber(client.getPhoneNumber());
			if (client.getReservation().getCourt() != null)
				_reservation.setCourt(client.getReservation().getCourt());
			if (client.getReservation().getDate() != null)
				_reservation.setDate(client.getReservation().getDate());
			_client.setReservation(_reservation);

			return new ResponseEntity<>(clientReservationRepository.save(_client), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/clientReservation/{id}")
	public ResponseEntity<HttpStatus> deleteClientReservation(@PathVariable("id") String id) {
		try {
			clientReservationRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/clientReservation")
	public ResponseEntity<HttpStatus> deleteAllClientReservations() {
		try {
			clientReservationRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
