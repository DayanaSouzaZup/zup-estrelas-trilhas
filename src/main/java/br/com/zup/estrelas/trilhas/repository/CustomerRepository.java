package br.com.zup.estrelas.trilhas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.estrelas.trilhas.entity.Customer;

@Repository
	public interface CustomerRepository extends CrudRepository<Customer, String> {


}
