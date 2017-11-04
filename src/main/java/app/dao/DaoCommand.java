package app.dao;


import app.models.Command;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

//@Repository
@Transactional
public interface DaoCommand extends CrudRepository<Command ,Short>{
	
	public Command findById(short id);
}
	
