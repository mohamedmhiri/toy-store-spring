package app.controller;

import app.models.Customer;
import app.dao.DaoCustomer;
import app.services.CustomerManager;
import java.lang.Integer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerManager customerManager;
	//private DaoCustomer daoCustomer;

/*	@ModelAttribute ("customer")
	public Customer addMyBeanToSessionScope(){

        	Customer customer= new Customer();
        	return customer;
	 }
*/	

	//create  --> Create a new user and save it in the database.
	@RequestMapping("/create")
	public String create(@Valid @ModelAttribute("customer") Customer customer,BindingResult bindingResult){
			if (bindingResult.hasErrors()){
				return "form";
			}
			else {
				logger.debug("customer " +customer);
				customerManager.insert(customer);
				return "admin";
			}
	}
	
	//delete --> Delete the user having the passed id(email).	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@ModelAttribute("customer") Customer customer) {
		try {
			Customer customer1= customerManager.getByEmail(customer.getEmail());
			customerManager.delete(customer1);
		}
		catch (Exception ex) {
			return "Error deleting the client:" + ex.toString();
		}
			return "Client succesfully deleted!";
	}	

	//get-by-email  --> Return the (email ,first name , second name) for the user having the passed email.
	@RequestMapping("/get-by-email")
	@ResponseBody
	public String getByEmail(@ModelAttribute("customer") Customer customer) {
		String userId;
		try {
			userId = " "+customer.getEmail()+" "+customer.getNom()+" "+customer.getPrenom();
		}
		catch (Exception ex) {
			return "Customer not found";
		}
		return "The Customer id is: " + userId;
	}

	//update  --> Update the customer in the database
	@RequestMapping("/update")
	public String updateUser(@Valid @ModelAttribute("customer") Customer customer,BindingResult bindingResult,Model model){
		List<Customer> customers= new ArrayList<Customer>();
		if (bindingResult.hasErrors()){
			for (Customer customer1 : customerManager.getAll()) 
				customers.add(customer1);
			model.addAttribute("customers",customers);
			return "u_form";
		}
		else {
			logger.debug("customer " +customer);
			Customer customer1=customerManager.getByEmail(customer.getEmail());
			customer1.setNom(customer.getNom());
			customer1.setPrenom(customer.getPrenom());
			if(customer.getTel()!="")
				customer1.setTel(customer.getTel());
			if(customer.getNom()!="")
				customer1.setAdresse(customer.getAdresse());
			customerManager.insert(customer1);
			return "admin";
		}
	}

	//manage  --> Returns the list of all customers 
	@RequestMapping("/affichclient")
	public ModelAndView manage(){
		List<Customer> customers= new ArrayList<Customer>();
		try{
			for (Customer customer : customerManager.getAll()) {
				
				customers.add(customer);
				logger.info(customer.toString());
			}
		}
		catch (Exception ex) {
			logger.warn( "Error getting Customers' List" + ex.toString(),customers);
		}
		return new ModelAndView("affichclient","customers",customers);
	}

}
