package br.com.zup.estrelas.trilhas.nivelUm;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.zup.estrelas.trilhas.dto.MessageDTO;
import br.com.zup.estrelas.trilhas.dto.UpdateCustomerDTO;
import br.com.zup.estrelas.trilhas.entity.Customer;
import br.com.zup.estrelas.trilhas.repository.CustomerRepository;
import br.com.zup.estrelas.trilhas.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
public class ShouldCreateCustomerTest {

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerService customerService;

	@Test
	public void shouldCreateNullCustomerTest() {

		Customer customer = new Customer();

		MessageDTO messageDTOReturn = customerService.createCustomer(customer);

		assertEquals("CPF cannot be null!", messageDTOReturn.getMessage());

	}

	@Test
	public void shouldCreateCustomerCPfUsedTest() {

		Customer customer = new Customer();

		customer.setCpf("8765472622");

		Mockito.when(customerRepository.existsById("8765472622")).thenReturn(true);

		MessageDTO messageDTOReturn = customerService.createCustomer(customer);

		assertEquals("CPF is already used", messageDTOReturn.getMessage());

	}

	@Test
	public void shouldCreateCustomer() {

		Customer customer = new Customer();

		customer.setCpf("2357669977");

		Mockito.when(customerRepository.existsById("2357669977")).thenReturn(false);

		MessageDTO messageDTOReturn = customerService.createCustomer(customer);

		assertEquals("Customer successfully registred!", messageDTOReturn.getMessage());
	}

	@Test
	public void shouldShowCustomerByCpf() {

		Customer customer = new Customer();

		customer.setCpf("8765472622");

		Optional<Customer> expectedCustomer = Optional.of(customer);

		Mockito.when(customerRepository.findById(customer.getCpf())).thenReturn(expectedCustomer);

		Customer foundCustomer = customerService.readCustomer("8765472622");

		assertEquals(foundCustomer.getCpf(), customer.getCpf());
	}

	@Test
	public void shouldDeleteCustomerByCpf() {

		Customer customer = new Customer();

		customer.setCpf("258122234776");

		Mockito.when(customerRepository.existsById(customer.getCpf())).thenReturn(true);

		MessageDTO messageDTOReturn = customerService.removeCustomer("258122234776");

		assertEquals("Customer successfully removed", messageDTOReturn.getMessage());

	}

	@Test
	public void shouldNotDeleteCustomerByCpfNotExist() {

		Customer customer = new Customer();

		customer.setCpf("6744578976");

		Mockito.when(customerRepository.existsById(customer.getCpf())).thenReturn(false);

		MessageDTO messageDTOReturn = customerService.removeCustomer("6744578976");

		assertEquals("Missing customer", messageDTOReturn.getMessage());

	}

	@Test
	public void shouldUpdateCustomerByCpf() {

		Customer customer = new Customer();

		customer.setCpf("8765472622");

		Optional<Customer> expectedCustomer = Optional.of(customer);

		Mockito.when(customerRepository.findById(customer.getCpf())).thenReturn(expectedCustomer);

		UpdateCustomerDTO customerDTO = new UpdateCustomerDTO();

		customerDTO.setAddress("Rua elvira");
		customerDTO.setAge(23);
		customerDTO.setEmail("fbfb@com");
		customerDTO.setName("Fabiana");
		customerDTO.setPhoneNumber("9864643643");
		
		MessageDTO messageUpdatedCustomer = customerService.updateCustomer("8765472622", customerDTO);
		
		assertEquals("Customer sucessfully updated ", messageUpdatedCustomer.getMessage());
		

	}

}
