package app.controller;

import app.models.*;
import app.services.ToyManager;
import java.lang.Integer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
//import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.Cookie;


@Controller
public class ToyController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ToyManager toyManager;

	/*@ModelAttribute ("toy")
	public Toy addMyBeanToSessionScope(){

        	Toy toy= new Toy();
        	return toy;
	 }*/
	

	//create  --> Create a new toy and save it in the database.
	@RequestMapping("/createt")
	public String create(@Valid @ModelAttribute("toy")Toy toy ,@RequestParam("file") MultipartFile file,Model model,RedirectAttributes redirectAttributes,BindingResult bindingResult){
			if (bindingResult.hasErrors()){
				return "ajouterj";
			}else{
				if(!file.isEmpty()){
					logger.debug("toy",toy);
					try {
                		byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                                 	/*String rootPath = System.getProperty("user.home"+"/Documents/development/code/site/src/main/resources/static/");
                                        File dir = new File(rootPath + File.separator + "images");
                         		if (!dir.exists())
                                    		dir.mkdirs();
                                         */                                             
                                       // Create the file on server
                    	File serverFile = new File(/*dir.getAbsolutePath()*/"/home/mohamed/Documents/development/code/site/src/main/resources/static/images/" + File.separator + toy.getName()+".jpg");
                    	BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream(serverFile));
                    	stream.write(bytes);
                    	stream.close();
						toy.setImage("/images/"+toy.getName()+".jpg");
						toyManager.insert(toy);
						logger.info("Server File Location=" + serverFile.getAbsolutePath());		
				//model.addAttribute("toy",toy);
				//redirectAttributes.addFlashAttribute("toy",toy);
			       }	
			     	catch (Exception e) {
						logger.warn("error "+e.toString());
			     	}
			    }
         		return "admin";
			}
	}

	/*@RequestMapping("/upload")
	public String upload(/*@ModelAttribute("toy") Toy toy,@RequestParam("image")MultipartFile image,/*ModelMap model){
		try{
			Toy toy=(Toy)model.get("toy");
			toy.setImage(image.getOriginalFilename());
			toyManager.insert(toy);
		}
		catch (Exception ex) {
			logger.warn( "Error creating the toy: " + ex.toString());
		}
		logger.warn("image   :",image.getOriginalFilename());
		return "admin";
	}*/
			
	
	//delete --> Delete the toy having the passed id(name).	
	@RequestMapping("/deletet")
	@ResponseBody
	public String delete(@ModelAttribute("toy") Toy toy) {
		Toy toy1= toyManager.getByName(toy.getName());
		try {
			toyManager.delete(toy1);
		}
		catch (Exception ex) {
			return "Error deleting the toy:" + ex.toString();
		}
			return "Toy succesfully deleted!";
	}	

        /*@RequestMapping("/affichfille")
        public ModelAndView affichef(@RequestParam("name")String name,HttpServletResponse response,HttpServletRequest request){
		List<Toy> toys= new ArrayList<Toy>();
		ModelAndView model= new ModelAndView("afficherj");
		Cookie[] cookies=request.getCookies();
		String panier="";
		Cookie cookie;
		try{
                        for(Cookie ck : cookies){
                                if ((ck.getName().equalsIgnoreCase(name)))
                                       panier=ck.getValue();
					
                        }
	                if ( panier.equals("")){
		 			
			cookie=new Cookie(name,"product");
			cookie.setMaxAge(999999);
			response.addCookie(cookie);
			}
	   		toys=toyManager.getByCategory("fille");
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		model.addObject("toys",toys);
		model.addObject("cookies",getCookiesNumber(request));
		return model;
	}
        @RequestMapping("/affichbebe")
        public ModelAndView afficheb(@RequestParam("name")String name,HttpServletResponse response,HttpServletRequest request){
		List<Toy> toys= new ArrayList<Toy>();
		ModelAndView model= new ModelAndView("afficherj");
		Cookie[] cookies=request.getCookies();
		String panier="";
		Cookie cookie;
		try{
                        for(Cookie ck : cookies){
                                if ((ck.getName().equalsIgnoreCase(name)))
                                       panier=ck.getValue();
					
                                }
	                if ( panier.equals("")){
		 			
			cookie=new Cookie(name,"product");
			cookie.setMaxAge(999999);
			response.addCookie(cookie);
			}
	   		toys=toyManager.getByCategory("bebe");
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		model.addObject("toys",toys);
		model.addObject("cookies",getCookiesNumber(request));
		return model;
	}*/
	/*@RequestMapping(value="/afficherj",method=RequestMethod.POST)
	public ModelAndView manage(@RequestParam("name")String name,HttpServletResponse response,HttpServletRequest request){
		List<Toy> toys= new ArrayList<Toy>();
		ModelAndView model= new ModelAndView("afficherj");
		Cookie[] cookies=request.getCookies();
		String panier="";
		Cookie cookie;
		try{
                        for(Cookie ck : cookies){
                                if ((ck.getName().equalsIgnoreCase(name)))
                                       panier=ck.getValue();
					
                                }
	                if ( panier.equals("")){
		 		
			cookie=new Cookie(name,"product");
			cookie.setMaxAge(999999);
			response.addCookie(cookie);
			}
			for (Toy toy : toyManager.getAll()) {
				
				toys.add(toy);
				logger.info(toy.toString());
			}
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		model.addObject("toys",toys);
		model.addObject("cookies",getCookiesNumber(request));
		return model;
	}
	@RequestMapping(value="/afficherj",method=RequestMethod.GET)
	public ModelAndView manageG(@RequestParam("name")String name,HttpServletResponse response,HttpServletRequest request){
		List<Toy> toys= new ArrayList<Toy>();
		ModelAndView model= new ModelAndView("afficherj");
		
		try{
                       
			for (Toy toy : toyManager.getAll()) {
				toys.add(toy);
				logger.info(toy.toString());
			}
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		model.addObject("toys",toys);
		model.addObject("cookies",0);
		return model;
	}*/

	@RequestMapping("/affichergar")
        public ModelAndView afficheg(){
		List<Toy> toys= new ArrayList<Toy>();
		try{
	   		toys=toyManager.getByCategory("boy");
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		return new ModelAndView("afficherj","toys",toys);
	}
	

	@RequestMapping(value="/afficherfille",method=RequestMethod.GET)
    public ModelAndView affichef(HttpServletResponse response,HttpServletRequest request){
		List<Toy> toys= new ArrayList<Toy>();
		ModelAndView model= new ModelAndView("afficherj");
		try{            
	   		toys=toyManager.getByCategory("fille");
	   		
			//model.addObject("cookies",0);
			/*model.addObject("boy",0);
			model.addObject("baby",0);
			model.addObject("girl",1);*/
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		model.addObject("toys",toys);
		return model;
	}
	@RequestMapping(value="/afficherbebe",method=RequestMethod.GET)
    public ModelAndView afficheb(HttpServletResponse response,HttpServletRequest request){
		List<Toy> toys= new ArrayList<Toy>();
		ModelAndView model= new ModelAndView("afficherj");
		try{            
	   		toys=toyManager.getByCategory("bebe");
	   		
			//model.addObject("cookies",0);
			/*model.addObject("boy",0);
			model.addObject("baby",1);
			model.addObject("girl",0);*/
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		model.addObject("toys",toys);
		return model;
	}
	@RequestMapping(value="/afficher",method=RequestMethod.POST)
	public ModelAndView manage(@RequestParam("name")String name,HttpServletResponse response,HttpServletRequest request){
		List<Toy> toys= new ArrayList<Toy>();
		ModelAndView model= new ModelAndView("afficherj");
		Cookie[] cookies=request.getCookies();
		String panier="";
		Cookie cookie;
		try{
                        for(Cookie ck : cookies){
                                if ((ck.getName().equalsIgnoreCase(name)))
                                       panier=ck.getValue();
					
                                }
	                if ( panier.equals("")){
		 		
			cookie=new Cookie(name,"product");
			cookie.setMaxAge(999999);
			response.addCookie(cookie);
			}
			for (Toy toy : toyManager.getAll()) {
				if (toy.getCategory().equals("boy"))
					toys.add(toy);
				logger.info(toy.toString());
			}
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		model.addObject("toys",toys);
		//model.addObject("cookies",getCookiesNumber(request));
		return model;
	}
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public ModelAndView search(@RequestParam("category") String category,@RequestParam("price") String price,@RequestParam("name")String name){
		List<Toy> toys= new ArrayList<Toy>();
		ModelAndView model=new ModelAndView("afficherj");
		try{
			logger.debug("toys",category);
			logger.debug("toys",price);
			logger.debug("toys",name);
			if(name.equals(""))
			toys=toyManager.getByCategoryAndPrice(category,price);
			else
			
			toys=toyManager.getByCategoryAndPriceAndName(category,price,name);
		}
		catch(Exception ex){
			logger.debug("Error getting Toys' List" + ex.toString(),toys);
                }
                return model.addObject("toys",toys);
        }


	


	//get-by-email  --> Return the (username,first name , second name) for the user having the passed email.
	@RequestMapping("/get-by-name")
	@ResponseBody
	public String getByName(@ModelAttribute("toy") Toy toy) {
		String toyId;
		try {
			toyId = " "+toy.getName()+" "+toy.getCategory();
		}
		catch (Exception ex) {
			return "Toy not found";
		}
		return "The Toy id is: " + toyId;
	}

	//update  --> Update the username and the name for the toy in the database
	@RequestMapping("/updatet")
	public String updateToy(@Valid @ModelAttribute("toy") Toy toy,BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			return "ajouterj";
		}else{
			Toy toy1=toyManager.getByName(toy.getName());
			toy1.setCategory(toy.getCategory());
			toy1.setQuantity(toy.getQuantity());
			toy1.setPrice(toy.getPrice());
			if(toy.getImage()!="")
				toy1.setImage("/images/"+toy.getImage());
			toyManager.insert(toy1);
			return "admin";
		}
	}
/*
	@RequestMapping(value="/index")
	public ModelAndView index(@RequestParam("name")String name,HttpServletResponse response,HttpServletRequest request){
	Cookie[] cookies=request.getCookies();
	String panier="";
                        for(Cookie ck : cookies){
                                if ((ck.getName().equalsIgnoreCase(name)))
                                       panier=ck.getValue();
					
                                }
	                if ( panier.equals(""))
		 			
			{Cookie cookie=new Cookie(name,"product");
			cookie.setMaxAge(999999);
			response.addCookie(cookie);
			return new ModelAndView ("index","cookies",getCookiesNumber(request));
		}
			else 	
				return new ModelAndView("index");
	}
*/

	//afficherj  --> Returns the list of all toys 
	@RequestMapping(value="/afficherj",method=RequestMethod.POST)//@RequestParam("boy")int boy,@RequestParam("girl")int girl,@RequestParam("baby")int baby,*/
	public ModelAndView manageA(@RequestParam("name")String name,HttpServletResponse response,HttpServletRequest request){
		List<Toy> toys= new ArrayList<Toy>();
		ModelAndView model= new ModelAndView("afficherj");
		Cookie[] cookies=request.getCookies();
		String panier="";
		Cookie cookie;
		try{
                        for(Cookie ck : cookies){
                                if ((ck.getName().equalsIgnoreCase(name)))
                                       panier=ck.getValue();
					
                                }
	                if ( panier.equals("")){
		 		
			cookie=new Cookie(name,"product");
			cookie.setMaxAge(999999);
			response.addCookie(cookie);
			}
			for (Toy toy : toyManager.getAll()) {
				/*if(boy==1)
					if(toy.getCategory().equals("garçon"))
						toys.add(toy);
				else if(girl==1)
					if(toy.getCategory().equals("fille"))
						toys.add(toy);
				else if(baby==1)
					if(toy.getCategory().equals("bebe"))
						toys.add(toy);
				else*/
				toys.add(toy);
				logger.info(toy.toString());
				
			}
			model.addObject("toys",toys);
			//model.addObject("cookies",getCookiesNumber(request));
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		
		return model;
	}
	@RequestMapping(value="/afficherj",method=RequestMethod.GET)
	public ModelAndView manageG(HttpServletResponse response,HttpServletRequest request){
		List<Toy> toys= new ArrayList<Toy>();
		ModelAndView model= new ModelAndView("afficherj");
		/*Cookie[] cookies=request.getCookies();
		String panier="";
		Cookie cookie;*/
		try{
                        /*for(Cookie ck : cookies){
                                if ((ck.getName().equalsIgnoreCase(name)))
                                       panier=ck.getValue();
					
                                }
	                if ( panier.equals("")){
		 		
			cookie=new Cookie(name,"product");
			cookie.setMaxAge(999999);
			response.addCookie(cookie);
			}*/
			for (Toy toy : toyManager.getAll()) {
				toys.add(toy);
				logger.info(toy.toString());
			}
			model.addObject("toys",toys);
			/*model.addObject("boy",0);
			model.addObject("baby",0);
			model.addObject("girl",0);
			model.addObject("cookies",0);*/
		}
		catch (Exception ex) {
			logger.warn( "Error getting Toys' List" + ex.toString(),toys);
		}
		
		return model;
	}

	@RequestMapping(value="/affichert",method=RequestMethod.GET)
	public ModelAndView manageT(){
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
		return new ModelAndView("affichert","toys",toys);

	}
	public int getCookiesNumber(HttpServletRequest request){
                int nbr= 0;
                Cookie[] cookies=request.getCookies();
                for(Cookie ck : cookies){
                        if (ck.getValue().equalsIgnoreCase("product")){
                                nbr++;
                        }
                }

                return  nbr+1;
        }

}
