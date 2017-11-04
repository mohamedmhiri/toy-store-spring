/*package dao;



import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import bean.Etudiant;


public class DaoFactory {

	String url;

	String username;

	String passwd;

	public DaoFactory(String url,String username, String passwd){

		this.url=url;

		this.username=username;

		this.passwd=passwd;

	}

	

	

	public static DaoFactory getInstance(){

		

		try {

            Class.forName("com.mysql.jdbc.Driver");

            

        } catch (ClassNotFoundException e) {

        }

		DaoFactory instance=new DaoFactory("jdbc:mysql://localhost:3306/tp", "root", "root");

		return instance ;

		 

	}

	

	public Connection getConnection() throws SQLException {

         Connection cnx= (Connection)DriverManager.getConnection(url, username, passwd);

         cnx.setAutoCommit(false);

         return cnx;

    }



    

    public IDaoEtudiant getDaoEtudiant() {

        return new DaoEtudiantImp(this);

    }

	

}



*/
