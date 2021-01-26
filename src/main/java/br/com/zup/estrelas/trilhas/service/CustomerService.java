package br.com.zup.estrelas.trilhas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.estrelas.trilhas.dto.MessageDTO;
import br.com.zup.estrelas.trilhas.dto.UpdateCustomerDTO;
import br.com.zup.estrelas.trilhas.entity.Customer;
import br.com.zup.estrelas.trilhas.repository.CustomerRepository;

@Service
public class CustomerService implements ICustomerService {

	private static final String CPF_IS_ALREADY_USED = "CPF is already used";
	private static final String CUSTOMER_SUCCESSFULLY_REGISTRED = "Customer successfully registred!";
	private static final String CPF_CANNOT_BE_NULL = "CPF cannot be null!";
	private static final String CUSTOMER_SUCCESSFULLY_REMOVED = "Customer successfully removed";
	private static final String MISSING_CUSTOMER = "Missing customer";
	private static final String CUSTOMER_SUCESSFULLY_UPDATED = "Customer sucessfully updated ";

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public MessageDTO createCustomer(Customer customer) {

		if (customer.getCpf() == null) {
			return new MessageDTO(CPF_CANNOT_BE_NULL);
		}

		if (customer.getCpf() != null) {

			if (customerRepository.existsById(customer.getCpf())) {
				return new MessageDTO(CPF_IS_ALREADY_USED);
			}
		}

		customerRepository.save(customer);
		return new MessageDTO(CUSTOMER_SUCCESSFULLY_REGISTRED);
	}

	public Customer readCustomer(String cpf) {

		return customerRepository.findById(cpf).orElseThrow();
	}

	public List<Customer> customerList() {

		return (List<Customer>) customerRepository.findAll();
	}

	public MessageDTO removeCustomer(String cpf) {

		if (cpf != null && customerRepository.existsById(cpf)) {

			customerRepository.deleteById(cpf);

			return new MessageDTO(CUSTOMER_SUCCESSFULLY_REMOVED);
		}
		return new MessageDTO(MISSING_CUSTOMER);
	}

	public MessageDTO updateCliente(String cpf, UpdateCustomerDTO updateCustomerDTO) {

		Optional<Customer> consultedCustomer = customerRepository.findById(cpf);

		if (consultedCustomer.isPresent()) {

			Customer customerUpdated = consultedCustomer.get();

			customerUpdated.setName(updateCustomerDTO.getName());
			customerUpdated.setAge(updateCustomerDTO.getAge());
			customerUpdated.setPhoneNumber(updateCustomerDTO.getPhoneNumber());
			customerUpdated.setAddress(updateCustomerDTO.getAddress());
			customerUpdated.setEmail(updateCustomerDTO.getEmail());

			customerRepository.save(customerUpdated);

			return new MessageDTO(CUSTOMER_SUCESSFULLY_UPDATED);
		}
		return new MessageDTO(MISSING_CUSTOMER);
	}

}
