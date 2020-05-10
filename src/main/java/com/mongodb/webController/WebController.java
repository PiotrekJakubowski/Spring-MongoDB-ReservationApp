package com.mongodb.webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.model.Client;
import com.mongodb.repository.ClientReservationRespository;


@Controller
@RequestMapping("/client")
public class WebController {

	@Autowired
	ClientReservationRespository clientReservationRepository;
	
	@GetMapping("/list")
	public String clientList(Model model) {

		List<Client> clientList = this.clientReservationRepository.findAll();
		
		//System.out.println("clientList 1: " + clientList.get(0).getId() );
		
		model.addAttribute("clients", clientList);

		return "clients-table";

	}
	
}
