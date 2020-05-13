package com.mongodb.webController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.model.Client;
import com.mongodb.model.Reservation;
import com.mongodb.repository.ClientReservationRespository;
import com.mongodb.repository.ReservationRespsitory;

@Controller
@RequestMapping("/web")
public class WebController {

	@Autowired
	ClientReservationRespository clientReservationRepository;
	
	@Autowired
	ReservationRespsitory reservationRepository;
	

	@GetMapping("/client")
	public String clientList(Model model) {

		List<Client> clientList = this.clientReservationRepository.findAll();

		model.addAttribute("clients", clientList);

		return "clients-table";

	}

	@GetMapping("/addClient")
	public String addClient(Model model) {

		Client client = new Client();

		model.addAttribute("client", client);

		return "client-form";

	}

	@PostMapping("/clientSave")
	public String saveClient(@ModelAttribute("client") Client theClient) {

		System.out.println("Client save method start");

		clientReservationRepository.save(theClient);

		return "redirect:/web/client";
	}

	@GetMapping("/update/{clientId}")
	public String updateClientForm(@PathVariable String clientId, Model model) {
		Optional<Client> client = clientReservationRepository.findById(clientId);

		model.addAttribute("client", client);

		return "client-form";

	}

	@GetMapping("/delete/{clientId}")
	public String deleteClient(@PathVariable String clientId) {

		clientReservationRepository.deleteById(clientId);

		System.out.println("Client id deleted: " + clientId);

		return "redirect:/web/client";
	}

	@GetMapping("/clientReservations/{clientId}")
	public String reservationList(@PathVariable String clientId, Model model) {

		System.out.println("reservationList function start");

		Optional<Client> client = clientReservationRepository.findById(clientId);

		System.out.println(client.get().getReservation());

		model.addAttribute("reservations", client.get().getReservation());
		model.addAttribute("clientId", clientId);

		return "reservations-table";
	}

	@GetMapping("/addNewReservation/{clientId}")
	public String addReservation(@PathVariable String clientId, Model model) {

		Reservation reservation = new Reservation();

		model.addAttribute("reservation", reservation);
		model.addAttribute("clientId", clientId);

		return "reservation-form";

	}

	@PostMapping("/save/{clientId}")
	public String saveReservation(@PathVariable String clientId,
			@ModelAttribute("reservation") Reservation reservation) {

		System.out.println(reservation);

		Client theClient = clientReservationRepository.findById(clientId).orElse(new Client());

		System.out.println(theClient);

		ObjectId id = new ObjectId();
		reservation.setId(id.toHexString());

		List<Reservation> tempReservation = new ArrayList<>();
		
		if (theClient.getReservation().isEmpty()) {
			tempReservation.add(reservation);

			theClient.setReservation(tempReservation);
		} else {
			tempReservation = theClient.getReservation();
			tempReservation.add(reservation);
			theClient.setReservation(tempReservation);
		}

		clientReservationRepository.save(theClient);

		return "redirect:/web/clientReservations/" + clientId;

	}
	
	@GetMapping("/updateReservation/{reservationId}/{clientId}")
	public String updateReservationForm(@PathVariable String reservationId, @PathVariable String clientId, Model model) {
		
		System.out.println("ReservationId: " +  reservationId);
		
		System.out.println(clientReservationRepository.findByFirstName("Ron"));
		
		List <Reservation> client = reservationRepository.findReservationId(reservationId);
		
		System.out.println("Client: " + client);
		
		
		return "redirect:/web/client";
	}


}
