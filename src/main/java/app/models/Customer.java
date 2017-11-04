package app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import javax.validation.constraints.Pattern;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="customer")
public class Customer implements java.io.Serializable{
	
	@Pattern(regexp="^[a-zA-Z]{2,}$",message="{Pattern.Customer.nom}")
	private String nom;
	
	@Pattern(regexp="^[a-zA-Z]{2,}$",message="{Pattern.Customer.prenom}")
	private String prenom;

	@Id  
	@Pattern(regexp="^.+@.+\\.[a-z]{2,}$",message="{Pattern.Customer.email}")
	private String email;
	
	@Pattern(regexp="^[0-9]{8}$|^[0-9]{0}$",message="{Pattern.Customer.tel}")
	private String tel;

	private String adresse;

	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\\s).{8,20}$",message="{Pattern.Customer.password}")
	private String password;
	
	//@OneToMany/*(mappedBy="made")*/
	//private List<Command> commands;

	public Customer(){}	

	public Customer(String email,String password){
		setEmail(email);
		setPassword(password);
	}

	public Customer(String email,String nom,String prenom,String tel,String adresse,String password/*,List<Command> commands*/){
		setEmail(email);
		setNom(nom);
		setPrenom(prenom);
		setTel(tel);
		setAdresse(adresse);
		setPassword(password);
		//setCommands(commands);
	}

	public String getNom() {

		return nom;

	}

	public void setNom(String nom) /*throws BeanException*/ {

	/*	if (nom.length() > 40) {

            throw new BeanException("Le nom est trop grand ! (10 caractères maximum)");

        }

        else {

        */	this.nom = nom; 

        /*}*/}

	public String getPrenom() {

		return prenom;

	}

	public void setPrenom(String prenom) {

		this.prenom = prenom;

	}

	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	public String getTel() {

		return tel;

	}

	public void setTel(String tel) {

		this.tel = tel;

	}

	public String getAdresse() {

		return adresse;

	}

	public void setAdresse(String adresse) {

		this.adresse = adresse;

	}


	public String getPassword() {

		return password;

	}

	public void setPassword(String password) {

		this.password = password;

	}

	/*public List<Command> getCommands(){
		return this.commands;
	}
	

	public void setCommands(List<Command> commands){
		for(Command command:commands){
			this.commands.add(command);
		}
	}*/
	
	@Override
   	 public String toString() {
        	return String.format(
                	"Customer[email='%s', name='%s',prenom='%s']",email,nom,prenom);
    	}

	

}
