package br.com.zup.estrelas.trilhas.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.trilhas.dto.MessageDTO;
import br.com.zup.estrelas.trilhas.entity.Customer;
import br.com.zup.estrelas.trilhas.service.CustomerService;


@RestController
@RequestMapping("/customer")
public class CustomerController {

	CustomerService customerService;

	public CustomerController(CustomerService customerParam) {
		this.customerService = customerParam;
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	
	public MessageDTO createCustomer (@RequestBody Customer customer) {

		return customerService.createCustomer(customer);
	}

}


