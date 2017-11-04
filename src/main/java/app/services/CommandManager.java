package app.services;

import app.dao.DaoCommand;
import app.models.Command;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.lang.Iterable;
import org.springframework.stereotype.Service;

@Service
public class CommandManager{
	
	@Autowired
	private DaoCommand daoCommand;

	public void insert(Command command){
		daoCommand.save(command);
	}
	
	public void delete(Command command){
		daoCommand.delete(command);
	}
	public Command getById(short id){
		return daoCommand.findById(id);
	}
	public List<Command> getAll(){
		return (List<Command>)daoCommand.findAll();
	}
	

}
