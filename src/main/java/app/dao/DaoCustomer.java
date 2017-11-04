package app.dao;


import app.models.Customer;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

//@Repository
@Transactional
public interface DaoCustomer extends CrudRepository<Customer ,String>{
	
	public Customer findByEmail(String email);
}
	
