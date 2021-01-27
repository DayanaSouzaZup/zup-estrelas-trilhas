package br.com.zup.estrelas.trilhas.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.estrelas.trilhas.dto.MessageDTO;
import br.com.zup.estrelas.trilhas.dto.UpdateCustomerDTO;
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

	public MessageDTO createCustomer(@RequestBody Customer customer) {

		return customerService.createCustomer(customer);
	}

	@GetMapping(path = "{/cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })

	public Customer readCustomer(@PathVariable String cpf) {

		return customerService.readCustomer(cpf);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Customer> customerList() {

		return customerService.customerList();
	}

	@DeleteMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MessageDTO removeCustomer(@PathVariable String cpf) {

		return customerService.removeCustomer(cpf);

	}

	@PutMapping(path = "/{cpf}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public MessageDTO updateCliente(@PathVariable String cpf, @RequestBody UpdateCustomerDTO customer) {

		return customerService.updateCustomer(cpf, customer);
	}

}
