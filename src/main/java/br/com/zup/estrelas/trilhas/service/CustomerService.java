package br.com.zup.estrelas.trilhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.trilhas.dto.MessageDTO;
import br.com.zup.estrelas.trilhas.entity.Customer;
import br.com.zup.trilhas.repository.CustomerRepository;

@Service
public class CustomerService implements ICustomerService{
	
	private static final String CPF_IS_ALREADY_USED = "CPF is already used";
	private static final String CUSTOMER_REGISTRED_SUCCESSFULLY = "Customer registred successfully!";
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public MessageDTO createCustomer(Customer customer) {
		
		if (customer.getCpf() != null) {

			if (customerRepository.existsById(customer.getCpf())) {
				return new MessageDTO(CPF_IS_ALREADY_USED);
			}
		}

		customerRepository.save(customer);
		return new MessageDTO(CUSTOMER_REGISTRED_SUCCESSFULLY);
	}

}
