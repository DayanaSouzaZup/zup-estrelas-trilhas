package br.com.zup.estrelas.trilhas.service;

import br.com.zup.estrelas.trilhas.dto.MessageDTO;
import br.com.zup.estrelas.trilhas.entity.Customer;

public interface ICustomerService {
	
	public MessageDTO createCustomer(Customer customer);

}
