package app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="command")
public class Command implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private short id;
	

/*	@ManyToOne
	@JoinColumn(name="email")
	private Customer customer;
*/
	private String email;

	@CreatedDate
	private String createdOn ;

//	@OneToMany/*(mappedBy="bought")*/
//	private List<Toy> toys;
	


	public Command(){}
	
	public Command(short id){
		setId(id);
	}

	public Command(short id,String createdOn/*,List<Toy> toys,Customer customer*/,String email){
		setId(id);
		setCreatedOn(createdOn);
		//setToys(toys);
		//setCustomer(customer);
		setEmail(email);
	}
	
	public short getId(){
		return this.id;
	}
	public void setId(short id){
		this.id=id;
	}

	public String getCreatedOn(){
		return createdOn;
	}
	public void setCreatedOn(String createdOn){
		this.createdOn=createdOn;
	}
/*	public List<Toy> getToys(){
		return toys;
	}
	public void setToys(List<Toy> toys){
		for(Toy toy:toys){
			this.toys.add(toy);
		}
	}
	public Customer getCustomer(){
		return customer;
	}
	//String email,String nom,String prenom,int tel,String adresse,String username, String password
	public void setCustomer(Customer customer){
		this.customer.setEmail(customer.getEmail());
		this.customer.setNom(customer.getNom());
		this.customer.setPrenom(customer.getPrenom());
		this.customer.setTel(customer.getTel());
		this.customer.setAdresse(customer.getAdresse());
		this.customer.setUsername(customer.getUsername());
		this.customer.setPassword(customer.getPassword());
	}*/
	
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email=email;
	}

	@Override
         public String toString() {
                return String.format(
                        "Command[email='%s']",this.getEmail());
        }
		
}
