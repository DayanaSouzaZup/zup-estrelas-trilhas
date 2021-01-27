package br.com.zup.estrelas.trilhas.service;

import java.util.List;

import br.com.zup.estrelas.trilhas.dto.MessageDTO;
import br.com.zup.estrelas.trilhas.dto.UpdateCustomerDTO;
import br.com.zup.estrelas.trilhas.entity.Customer;

public interface ICustomerService {
	
	public MessageDTO createCustomer(Customer customer);
	
	public Customer readCustomer(String cpf);
	
	public List<Customer> customerList();
	
	public MessageDTO removeCustomer(String cpf);
	
	public MessageDTO updateCliente(String cpf, UpdateCustomerDTO updateCustomerDTO);

}
