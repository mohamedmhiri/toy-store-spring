package app.controller;

import app.models.*;
import app.services.*;
import java.lang.Integer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CookieValue;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.ZoneId;

@Controller
public class AccueilController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerManager customerManager;
	
	@Autowired 
	private ToyManager toyManager;
	
	@Autowired
	private CommandManager commandManager;
	
	@RequestMapping(value={"/accueil","/","/index"})
	public String accueil(HttpServletResponse response){
		Cookie cookie=new Cookie("panier1","product1");
		response.addCookie(cookie);
		return "index";	
	}
	@RequestMapping(value="/chart")
	public ModelAndView chart(){
		List<Toy> toys= toyManager.getAll();
		int size =0;
		String toys_name="";
		int toys_s=0;
		String toys_sold="";
		if (toys.size()==1){
			toys_name=toys.get(0).getName();
			toys_sold="100";
			size=toys.get(0).getTSoldNbr();

		}
		else {
			size=toys.get(0).getTSoldNbr();
			if(toys.get(0).getSoldNbr()>0){
					toys_name=toys.get(0).getName();
					toys_s=(toys.get(0).getSoldNbr()*100)/size;
					toys_sold=""+toys_s;
				}
			
			if(size==0){
				toys_sold="100";
				toys_name="No Sold Toys";
			}else{
				for (int i=1; i<toys.size();i++) {
					logger.info(""+toys.get(i).getSoldNbr());
					if(toys.get(i).getSoldNbr() > 0){
						toys_s=(toys.get(i).getSoldNbr()*100)/size;
						if(toys_sold.equals(""))
							toys_sold=""+toys_s;
						else
							toys_sold=toys_sold+","+toys_s;
						if(toys_name.equals(""))
							toys_name=toys.get(i).getName();
						else	
							toys_name=toys_name+"|"+toys.get(i).getName();
					}
				
					logger.info(toys_name);
					logger.info(""+size);
					logger.info(""+toys_sold);
				}
			}
		}

		/*https://chart.googleapis.com/chart?cht=p3&chd=t:60,40&chs=300x150&chl=Hello|World*/
		String url="https://chart.googleapis.com/chart?cht=p3&chd=t:"  +toys_sold  +"&chs=280x110&chl="+toys_name;
		return new ModelAndView("chart","url",url);	
	}

	/*@RequestMapping(value="/index")
	public ModelAndView index(@RequestParam("name")String name,HttpServletResponse response,HttpServletRequest request){
	Cookie[] cookies=request.getCookies();
	String panier="";
                        for(Cookie ck : cookies){
                                if ((ck.getName().equalsIgnoreCase(name)))
                                       panier=ck.getValue();
					
                                }
	                if ( panier.equals("")){
		 			
			Cookie cookie=new Cookie(name,"product");
			cookie.setMaxAge(999999);
			response.addCookie(cookie);
			return new ModelAndView ("index","cookies",0);
		}
			else 
				return new ModelAndView("index");
	}
*//*@RequestMapping(value="/panier")
	public ModelAndView panier(HttpServletRequest request){
		Cookie cookie=new Cookie(name,"product");
//		cookie.setMaxAge(999999);
 //               response.addCookie(cookie);
		List<Toy> toys =checkCookie(request);
		float total=0;
		for(Toy toy :toys)
			total+=Float.parseFloat(toy.getPrice());
		ModelAndView model=new ModelAndView("panier");
		model.addObject("toys",checkCookie(request));
		if (getUser(request)!=null)
			model.addObject("user",getUser(request));
		model.addObject("total",total);
		return model;
	}*/
	@RequestMapping(value="/panier")
	public ModelAndView panier(@RequestParam("name")String name ,HttpServletRequest request,HttpServletResponse response){
		Cookie cookie=new Cookie(name,"product");
		cookie.setMaxAge(999999);
        response.addCookie(cookie);
		List<Toy> toys =checkCookie(request);
		float total=0;
		for(Toy toy :toys)
			total+=Float.parseFloat(toy.getPrice());
		ModelAndView model=new ModelAndView("panier");
		model.addObject("toys",checkCookie(request));
		if (getUser(request)!=null)
			model.addObject("user",getUser(request));
		model.addObject("total",total);
		return model;
	}
	/*int sum=0;
	for(Toy toy:toyManager.getAll()){
		int sum=sum+toy.getSoldNbr();
	}
	for(Toy toy:toyManager.getAll()){
		toy.setTSoldNbr(sum);
		toyManager.insert(toy);
	}*/
	@RequestMapping(value="/cmd")
	public ModelAndView commander(@RequestParam("quantity")int quantity,@RequestParam("email")String email,HttpServletRequest request,HttpSession session){
		ModelAndView model=new ModelAndView("bill");
		List<Toy> toys=new ArrayList<Toy>();
		try {
			if(getUser(request)!=null){
				toys=checkCookie(request);
				Command command=new Command();
				command.setEmail(email);
				LocalDateTime ldt = LocalDateTime.now();
				String d=ldt.getDayOfMonth()+" "+ldt.getMonth()+" "+ldt.getYear()+" "+ldt.getHour()+" "+ldt.getMinute()+" "+ldt.getSecond();
				command.setCreatedOn(d);
				commandManager.insert(command);
				model.addObject("toys",toys);
				for(Toy toy :toys){
					toy.setId(command.getId());
					logger.info(""+toy.getQuantity());
					if(toy.getQuantity()>0)
						toy.setSoldNbr(toy.getQuantity());
					toy.setQuantity(toy.getQuantity()-quantity);
					//if(this.quantity>0)
					toyManager.insert(toy);
					
					logger.info(""+toy.getQuantity());
				}
			int sum=0;
			for(Toy toy:toyManager.getAll()){
				sum=sum+toy.getSoldNbr();
			}
			for(Toy toy:toyManager.getAll()){
				toy.setTSoldNbr(sum);
				toyManager.insert(toy);
			}	
			}else{
				model= new ModelAndView("login","flag",1);
			}
		}
		catch (Exception ex) {
			logger.warn("errorrrr "+ex.toString());
		}
		return model;
	}
	@RequestMapping(value="/delpanier")
	public ModelAndView delPanier(@RequestParam("name")String name,HttpServletResponse response,HttpServletRequest request){
		Cookie[] cookies=request.getCookies();
		ModelAndView model=new ModelAndView("panier");
                try{
                        for(Cookie ck : cookies){
                                if (ck.getName().equalsIgnoreCase(name)){
                                       	ck.setMaxAge(0); 
					ck.setValue("");
            			ck.setPath("/");
            				response.addCookie(ck);
					
                                }
                        }
			List<Toy> toys =checkCookie(request);
			float total=0;
			for(Toy toy :toys)
				total+=Float.parseFloat(toy.getPrice());
		
			model.addObject("toys",checkCookie(request));
			model.addObject("total",total);
					
                }
                catch (Exception ex) {
			logger.warn("Error deleting panier");
                }
		return model;
        }
	
	public List<Toy> checkCookie(HttpServletRequest request){
                Cookie[] cookies=request.getCookies();
                List<Toy> toys=new ArrayList<Toy>();
                String panier ="";
                try{
                        for(Cookie ck : cookies){
                                if (ck.getValue().equalsIgnoreCase("product")){
                                        panier=ck.getName();
                                        logger.warn("cookie panier"+panier);

                                        Toy toy=toyManager.getByName(panier);
                                        logger.warn("nom du toy"+toy.getName());
                                        toys.add(toy);
                                }
                        }
                }
                catch (Exception ex) {
                        logger.warn( "Error getting Toys' List" + ex.toString(),toys);
                }
                return  toys;
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
        public String getUser(HttpServletRequest request){
                Cookie[] cookies=request.getCookies();
                Customer customer=null;
                String email=null;
                try{
                        for(Cookie ck : cookies){
                                if (ck.getName().equalsIgnoreCase("email")){
                                        customer=customerManager.getByEmail(ck.getValue());
                                        email=customer.getEmail();
                                }
                        }
                }
                catch (Exception ex) {
                        logger.warn( "Error getting Customer" + ex.toString());
                }
                return email;
        }
        @RequestMapping("/popup")
        public String popUp(){
        	return "popup";
        }
        @RequestMapping("/try")
        public String tryJs(){
        	return "try";
        }
        @RequestMapping("/valid")
        public String validJs(){
        	return "valid";
        }

}
	

	
