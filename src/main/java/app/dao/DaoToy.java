package app.dao;


import app.models.Toy;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

//@Repository
@Transactional
public interface DaoToy extends CrudRepository<Toy ,String>{
	
	public Toy findByName(String name);
	public List<Toy> findByCategory(String category);
	public List<Toy> findByPrice(float price);
	public List<Toy> findByQuantity(int quantity);
	public List<Toy> findByCategoryAndPrice(String category ,String price);	
	public List<Toy> findByCategoryAndPriceAndNameStartingWith(String category,String price,String name);
}
	
