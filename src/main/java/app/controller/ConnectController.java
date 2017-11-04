package app.controller;

import app.models.*;
import app.services.*;
import java.lang.Integer;
import org.slf4j.Logger;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus; 
import javax.servlet.http.Cookie;

@SessionAttributes("customer")
@Controller
public class ConnectController{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerManager customerManager;
	@Autowired
	private ToyManager toyManager;

	/*@ModelAttribute("myBean1")
    	public Customer addMyBeanToSessionScope() {
        	.info("Adding ");
        	return new MyBean("My Bean 1");
    	}*/

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	/*@RequestMapping("/create")
	public String create(@Valid @ModelAttribute("customer") Customer customer,BindingResult bindingResult){
			if (bindingResult.hasErrors()){
				return "form";
			}
			else {
				logger.debug("customer " +customer);
				customerManager.insert(customer);
				return "admin";
			}
	}*/
	@ModelAttribute(value="customer")
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("email")String email,@RequestParam("flag")int flag,@RequestParam("password")String password,HttpSession session,HttpServletResponse response,HttpServletRequest request){

	
		Customer customer= customerManager.getByEmail(email);//si l'email existe on retourne le client
	   	if(customer == null){//email invalid alors pas de client retourn
			return new ModelAndView("login","error","Account is invalid");
	   	}
	   	else{//le client existe et l'email est vrai  
	         	 if(customer.getPassword().equals(password)){//on verifie alors le password
		 		logger.warn("session :"+session); 
		 		logger.warn("session's customer :"+session.getAttribute("customer")); 
				Cookie cookie=new Cookie("email",email);
				response.addCookie(cookie);
				if(flag!=1){
		 			ModelAndView model= new ModelAndView("afficherj","customer",customer);
					model.addObject("cookies",getCookiesNumber(request));		
					List<Toy> toys= new ArrayList<Toy>();
					for (Toy toy : toyManager.getAll()) {
						toys.add(toy);
						logger.info(toy.toString());
					}
		 			return model.addObject("toys",toys);		
	          		}else{
					return new ModelAndView("index"/*,"email",customer.getEmail()*/);
				}
			}
	         	else {
				return new ModelAndView("login","error","Account is invalid");
		 	}
		}	
	}
	
	//}	
	
	/*public Customer checkCookie(HttpServletRequest request){
		Cookie[] cookies=request.getCookies();
	
		String email="";
		String password="";
	  	for(Cookie ck : cookies){
	  		if (ck.getName().equalsIgnoreCase("email"))
	  			email=ck.getValue();
	  		if (ck.getName().equalsIgnoreCase("password"))
	  			password=ck.getValue();
	  	}
		if(!email.isEmpty() && !password.isEmpty())
		
		return new Customer();
	}*/
 
	@RequestMapping(value="/logout",method=RequestMethod.GET)
        public String logout(){
                return "logout";         
        }  		
	@RequestMapping(value="/manage",method=RequestMethod.GET)
        public String verif(){
                return "manage";         
        }
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public String logout(SessionStatus status,HttpSession session,HttpServletResponse response,HttpServletRequest request){
		status.setComplete();
		logger.warn("session :"+session);
		Cookie[] cookies=request.getCookies();
                try{
                        for(Cookie ck : cookies){
                                if (ck.getName().equalsIgnoreCase("email")){
                                       	ck.setMaxAge(0); 
					ck.setValue("");
            				ck.setPath("/");
            				response.addCookie(ck);
                                }
                        }
		}
                catch (Exception ex) {
			logger.warn("Error deleting panier");
                }
		return "index";
	}
	public int getCookiesNumber(HttpServletRequest request){
                int nbr= 0;
                Cookie[] cookies=request.getCookies();
                for(Cookie ck : cookies){
                        if (ck.getValue().equalsIgnoreCase("product")){
                                nbr++;
                        }
                }

                return  nbr;
        }

}
