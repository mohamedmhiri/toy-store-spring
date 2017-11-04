package app.controller;

import org.slf4j.Logger;
import app.services.*;
import app.models.*;
import org.slf4j.LoggerFactory;
import app.dao.DaoCustomer;
import app.models.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;

@Controller
public class GreetingController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerManager customerManager;
	@Autowired
	private ToyManager toyManager;
	@Autowired
	private CommandManager commandManager;


    @RequestMapping(value="/form", method=RequestMethod.GET)
    public String greetingForm(Model model,Customer customer) {
        model.addAttribute("form", customer);
        return "form";
    }

    @RequestMapping(value="/form", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Customer customer, Model model) {
        model.addAttribute("form", customer);
        return "create";
    }
    /*@RequestMapping(value="/errorr")
    public String errorG() {
        
        return "error";
    }*/
  /* 
		List<Toy> toys= new ArrayList<Toy>();
		try{
			for (Toy toy : toyManager.getAll()) {
				toys.add(toy);
				logger.info(toy.toString());
			}
		}*/
    @RequestMapping(value="/u_form")
    public ModelAndView greetingupdate(Customer customer){
		ModelAndView model= new ModelAndView("u_form","customer",customer);
		List<Customer> customers= new ArrayList<Customer>();
		try{
			for (Customer customer1 : customerManager.getAll()) {
				customers.add(customer1);
				logger.info(customer1.toString());
			}
			model.addObject("customers",customers);
		}
		catch (Exception ex) {
			logger.warn( "Error getting Customers' List" + ex.toString(),customers);
		}
		return model; 
	}
/*    @RequestMapping(value="/u_form",method=RequestMethod.POST)
    public String greetinguupdate(){
	return "update";
    }*/
    @RequestMapping(value="/suppclient",method=RequestMethod.GET)
    public ModelAndView greetingdel(){
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
		return new ModelAndView("suppclient","customers",customers);
    }
    @RequestMapping(value="/suppclient",method=RequestMethod.POST)
    public String greetingdele(){
	return "delete";
    }
    @RequestMapping("/admin")
    public ModelAndView  admin(HttpServletRequest request){
	return new ModelAndView("admin","cookies",0);
    }
    /*@RequestMapping("/affichclient")
    public String display(){
	return "afficheclient";
    }*/
    @RequestMapping(value="/ajouterj", method=RequestMethod.GET)
    public String greetingAddJ(Model model,Toy toy) {
        model.addAttribute("ajouterj", toy);
        return "ajouterj";
    }

    @RequestMapping(value="/ajouterj", method=RequestMethod.POST)
    public String greetingSubmitJ(@ModelAttribute("toy") Toy toy, Model model) {
	logger.info("toy",toy);
        model.addAttribute("ajouterj", toy);
        return "createt";
    }
    @RequestMapping(value="/modifierj"/*,method=RequestMethod.GET*/)
    public ModelAndView greetingUpdateT(Toy toy){
		ModelAndView model =new ModelAndView("modifierj","toy",toy);
		List<Toy> toys= new ArrayList<Toy>();
		try{
			for (Toy toy1 : toyManager.getAll()) {
				toys.add(toy1);
				logger.info(toy1.toString());
			}
			model.addObject("toys",toys);
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		return model;
	}
    @RequestMapping(value="/supprimerj",method=RequestMethod.GET)
    public ModelAndView greetingdelT(){
		List<Toy> toys= new ArrayList<Toy>();
		try{
			for (Toy toy : toyManager.getAll()) {
				
				toys.add(toy);
				logger.info(toy.toString());
			}
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
	
	return new ModelAndView("supprimerj","toys",toys);
    }
    @RequestMapping(value="/supprimerj",method=RequestMethod.POST)
    public String greetingdeleT(){
	return "deletet";
    }
    @RequestMapping(value="/ajoutercmd", method=RequestMethod.GET)
    public ModelAndView greetingAddC() {
		ModelAndView m=new ModelAndView("ajoutercmd");	
		List<Toy> toys= new ArrayList<Toy>();
		List<Customer> customers= new ArrayList<Customer>();
		try{
			for (Toy toy : toyManager.getAll()) {
				
				toys.add(toy);
				logger.info(toy.toString());
			}
			for (Customer customer : customerManager.getAll()) {
				
				customers.add(customer);
				logger.info(customer.toString());
			}
			m.addObject("toys",toys);
			m.addObject("customers",customers);
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		return m;       
       
    }

    @RequestMapping(value="/ajoutercmd", method=RequestMethod.POST)
    public String greetingSubmitC(@ModelAttribute("command") Command command, Model model) {
        model.addAttribute("ajouterj", command);
	logger.info("command",command);
        return "createc";
	}	
    @RequestMapping(value="/supprimercmd",method=RequestMethod.GET)
    public ModelAndView greetingdelC(){
		List<Command> commands= new ArrayList<Command>();
		try{
			for (Command command : commandManager.getAll()) {
				
				commands.add(command);
				logger.info(command.toString());
			}
		}
		catch (Exception ex) {
			logger.warn( "Error getting Commands' List" + ex.toString(),commands);
		}
	
	return new ModelAndView("supprimercmd","commands",commands);
    }
    @RequestMapping(value="/supprimercmd",method=RequestMethod.POST)
    public String greetingdeleC(){
	return "deletec";
    }
	
}

