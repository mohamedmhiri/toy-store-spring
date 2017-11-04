package app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.persistence.Lob;
import java.sql.Blob;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Pattern;
import java.util.List;
import javax.persistence.Column;

@Entity
@Table(name="toy")
public class Toy implements java.io.Serializable{
	
	@Pattern(regexp="^[a-z]{4,6}[0-9]+$",message="{Pattern.Toy.name}")
	@Id
	private String name;
	

	@Pattern(regexp="^[a-zçé]{4,6}$",message="{Pattern.Toy.category}")
	private String category;

	
	@Min(value=0,message="{Pattern.Toy.quantity}")
	private int quantity;

	private int sold_nbr ;

	private int tsold_nbr ;

	
	@Pattern(regexp="^[0-9]+$|^[0-9]\\.[0-9]+",message="{Pattern.Toy.price}")
	private String price ;
	

	private String image;
	
	private short id;

/*	@ManyToOne
        @JoinColumn(name="commanded")
	private Command command;*/

	public Toy(){}
	
	public Toy(String name){
		setName(name);
	}

	public Toy(String name,String category,int quantity,String price,String image,short id,String sold_nbr){
		setName(name);
		setCategory(category);
		setQuantity(quantity);
		setPrice(price);
		setImage(image);
		setId(id);
	}
	
	public int getQuantity(){
		return quantity;
	}
	public int getSoldNbr(){
		return sold_nbr;
	}
	public int getTSoldNbr(){
		return tsold_nbr;
	}
	public void setTSoldNbr(int tsold_nbr){
		this.tsold_nbr=tsold_nbr;
	}
	public void setSoldNbr(int sold_nbr){
		this.sold_nbr=this.sold_nbr+sold_nbr;
	}
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	public String getPrice(){
		return price;
	}
	public void setPrice(String price){
		this.price=price;
	}
	public String getImage(){
		return image;
	}
	public void setImage(String image){
		this.image=image;
	}
	public String getCategory() {

		return category;

	}

	public void setCategory(String category) {

		this.category = category;

	}
	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}


	public short getId(){
		return this.id;
	}
	public void setId(short id){
	//	this.id.setId(id.getId());
	//	this.id.setEmail(id.getEmail());
		//this.id.setCustomer(id.getCustomer());
	//	this.id.setCreatedOn(id.getCreatedOn());
		this.id=id;	
	//this.id.setToys(id.getToys());
	}
	
	@Override
   	 public String toString() {
        	return String.format(
                	"Toy[category='%s',name='%s']",category,name);
	}
}
