package app.controller;

import app.models.*;
import app.dao.*;
import app.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.Integer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.ZoneId;

@Controller
public class CommandController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommandController.class);
	@Autowired
	private CommandManager commandManager;
	@Autowired
	private CustomerManager customerManager;
	@Autowired
	private ToyManager toyManager;
	

/*	@ModelAttribute ("command")
	public Command addMyBeanToSessionScope(){

        	Command command= new Command();
        	return command;
	 }
*/	

	//create  --> Create a new command and insert it in the database.
	@RequestMapping("/createc")
	@ResponseBody
	public String create(@ModelAttribute("command") Command command,@RequestParam("email")String email,@RequestParam("name")String name){
		try {
			logger.warn("email "+email);
			logger.warn("name "+name);
			logger.warn(customerManager.getByEmail(email).toString());
			command.setEmail(email);
			LocalDateTime ldt = LocalDateTime.now();
			logger.warn(""+ldt);
			//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			//Date date = df.parse(dateString);
			String d=ldt.getDayOfMonth()+" "+ldt.getMonth()+" "+ldt.getYear()+" "+ldt.getHour()+" "+ldt.getMinute()+" "+ldt.getSecond();
			command.setCreatedOn(d);
			logger.warn("command 's customer :"+command.getEmail());
			logger.warn("toy's command :"+toyManager.getByName(name).toString());
			/*List<Toy> toys=new ArrayList<Toy>();
			toys.add(toyManager.getByName(name));
			command.setToys(toys);*/
			commandManager.insert(command);
			Toy toy =toyManager.getByName(name);
			toy.setId(command.getId());
			if(toy.getQuantity()>0)
				toy.setSoldNbr(toy.getQuantity());
			toyManager.insert(toy);
		}
		catch (Exception ex) {
			return "Error creating the command: " + ex.toString();
		}
		return "Command succesfully created! (id = " + command.getId() + ")";
	}

	
	//delete --> Delete the user having the passed id(email).	
	@RequestMapping("/deletec")
	@ResponseBody
	public String delete(@ModelAttribute("command") Command command) {
		try {
			Command command1= commandManager.getById(command.getId());
			commandManager.delete(command1);
		}
		catch (Exception ex) {
			return "Error deleting the command:" + ex.toString();
		}
			return "Command succesfully deleted!";
	}	

	//get-by-email  --> Return the (username,first name , second name) for the user having the passed email.
	/*@RequestMapping("/get-by-id")
	@ResponseBody
	public String getById(@ModelAttribute("command") Command command) {
		String commandId;
		try {
			commandId = " "+command.getId();
		}
		catch (Exception ex) {
			return "Command not found";
		}
		return "The Command id is: " + commandId;
	}*/

/*	//update  --> Update the username and the name for the command in the database
	@RequestMapping("/updatec")
	@ResponseBody
	public String updateCommand(@ModelAttribute("command") Command command,@RequestMapping("name")String name){
		try {
			Command command1=commandManager.getById(command.getId());
			command1.setEmail(command.getEmail());
			command1.setCreatedOn(command.getCreatedOn());
			Toy toy=toyManager.getByName(name);
			//toy.setCommand(command1);
			//command1.setToys(command.getToys());
			commandManager.insert(command1);
			toyManager.insert(toy);
		}
		catch (Exception ex) {
			return "Error updating the command: " + ex.toString();
		}
		return "Command succesfully updated!";
	}
*/
	//manage  --> Returns the list of all commands 
	@RequestMapping("/affichcmd")
	public ModelAndView manage(){
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
		return new ModelAndView("affichcmd","commands",commands);
	}

}
