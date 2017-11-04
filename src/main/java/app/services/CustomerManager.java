package app.services;

import app.dao.DaoCustomer;
import app.models.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.lang.Iterable;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager{
	
	@Autowired
	private DaoCustomer daoCustomer;

	public void insert(Customer customer){
		daoCustomer.save(customer);
	}
	
	public void delete(Customer customer){
		daoCustomer.delete(customer);
	}
	public Customer getByEmail(String email){
		return daoCustomer.findByEmail(email);
	}
	public List<Customer> getAll(){
		return (List<Customer>)daoCustomer.findAll();
	}
	

}
