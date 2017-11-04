/*package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import bean.Etudiant;
import bean.BeanException;
import com.mysql.jdbc.Connection;
import java.sql.*;

public class DaoEtudiantImp implements DaoEtudiant {

	

	private DaoFactory daoFactory;



    public DaoEtudiantImp(DaoFactory daoFactory) {

        this.daoFactory = daoFactory;

    }



    @Override

    public void ajouter(Etudiant user) throws DaoException {

        Connection connexion = null;

        PreparedStatement preparedStatement = null;



        try {

            connexion = daoFactory.getConnection();

           // preparedStatement = (PreparedStatement) connexion.prepareStatement("INSERT INTO inscriptions(login,nom, prenom,email,tel,adresse,passwd,type) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement = (PreparedStatement) connexion.prepareStatement("INSERT INTO inscriptions(login,nom, prenom,email,tel,adresse,passwd,type) VALUES('a','b','c','d',1,'e','f','g');");

		 /*preparedStatement.setString(1, user.getLogin());
                 preparedStatement.setString(2, user.getNom());
                 preparedStatement.setString(3, user.getPrenom());
                 preparedStatement.setString(4, user.getEmail());
                 String tele = Integer.toString(user.getTel());
                 preparedStatement.setString(5,tele );
                 preparedStatement.setString(6, user.getAdresse());
                 preparedStatement.setString(7, user.getPasswd());
                 preparedStatement.setString(8, user.getType());*/
		
/*		preparedStatement.executeUpdate();

            connexion.commit();

        } catch (SQLException e) {

            try{

            	if(connexion!=null){

            		connexion.rollback();

            	}

            }catch(SQLException e2){

            		

            	}

            throw new DaoException("Database connexion failed1");

        }

        

        finally {

            try {

                if (connexion != null) {

                    connexion.close();  

                }

            } catch (SQLException e) {

                throw new DaoException("Database connexion failed2");

            }

        }



    }



    @Override

    public List<Etudiant> lister() throws DaoException {

        List<Etudiant> etudiants = new ArrayList<Etudiant>();

        Connection connexion = null;

        PreparedStatement preparedStatement = null;

        ResultSet resultat = null;



        try {

            connexion = daoFactory.getConnection();

            preparedStatement =  connexion.prepareStatement("SELECT * FROM inscriptions");

            resultat = preparedStatement.executeQuery();



            while (resultat.next()) {

            	String nom = resultat.getString("nom");

                System.out.println(nom);

                String prenom = resultat.getString("prenom");

                String mail =resultat.getString("email");

                int tel =Integer.parseInt(resultat.getString("tel"));

                String adresse =resultat.getString("adresse");

                String login =resultat.getString("login");

                String pwd =resultat.getString("passwd");

                String type =resultat.getString("type");

                //System.out.println(prenom);
                Etudiant student = new Etudiant();
                student.setNom(nom);
                student.setPrenom(prenom);
		student.setEmail(mail);
                student.setTel(tel);
                student.setType(type);
                student.setAdresse(adresse);
                student.setLogin(login);
                student.setPasswd(pwd);

                etudiants.add(student);
            }
        } catch (SQLException e) {

                                throw new DaoException("Impossible de communiquer avec la base de données1");

        }
        catch (BeanException e) {

                                throw new DaoException("Les données de la base sont invalides");

                }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données2");
            }
        }
	 return etudiants;
    }

}
*/	
