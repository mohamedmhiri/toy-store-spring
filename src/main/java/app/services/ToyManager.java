package app.services;

import app.dao.DaoToy;
import app.models.Toy;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.lang.Iterable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.lang.Exception;

//@Transactional(noRollbackFor = Exception.class)
@Service
public class ToyManager{
	
	@Autowired
	private DaoToy daoToy;

	public void insert(Toy toy){
		daoToy.save(toy);
	}
	
	public void delete(Toy toy){
		daoToy.delete(toy);
	}
	public Toy getByName(String name){
		return daoToy.findByName(name);
	}
	public List<Toy> getByCategory(String category){
		return (List<Toy>)daoToy.findByCategory(category);
	}
	public List<Toy> getByQuantity(int quantity){
		return (List<Toy>)daoToy.findByQuantity(quantity);
	}
	public List<Toy> getByPrice(float price){	
		return (List<Toy>)daoToy.findByPrice(price);
	}
	public List<Toy> getByCategoryAndPrice(String category ,String price){
		return (List<Toy>)daoToy.findByCategoryAndPrice(category,price);
        }

	public List<Toy> getByCategoryAndPriceAndName(String category,String price,String name){
		return (List<Toy>)daoToy.findByCategoryAndPriceAndNameStartingWith(category,price,name);
        }
	public List<Toy> getAll(){
		return (List<Toy>)daoToy.findAll();
	}
	

}
