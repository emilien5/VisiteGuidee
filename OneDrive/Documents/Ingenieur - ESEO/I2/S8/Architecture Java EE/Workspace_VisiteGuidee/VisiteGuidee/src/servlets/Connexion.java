package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connexion extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String nomUtilisateur;
	private String motDePasse;
    
	public static final String VUE = "/WEB-INF/reservation.jsp";
	public static final String VUE_ERREUR = "/WEB-INF/connexionErreur.jsp";
    public static final String CHAMP_NOM_UTILISATEUR = "nom";
    public static final String CHAMP_PASS = "motdepasse";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        Map<String, String> erreurs = new HashMap<>();

        /* Récupération des champs du formulaire. */
        nomUtilisateur = request.getParameter( CHAMP_NOM_UTILISATEUR );
        motDePasse = request.getParameter( CHAMP_PASS );

        try {
			boolean valide = informationValide(nomUtilisateur, motDePasse);
			
			if(valide == true) {
				/* Stockage du résultat et des messages d'erreur dans l'objet request */
		        request.setAttribute( CHAMP_NOM_UTILISATEUR, nomUtilisateur );
		        request.setAttribute( CHAMP_PASS, motDePasse );
		        
		        /* Transmission de la paire d'objets request/response à notre JSP */
		        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
			} else {
				this.getServletContext().getRequestDispatcher( VUE_ERREUR ).forward( request, response );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}            
    }
    
    /* Valide le nom d'utilisateur saisi */
    private boolean informationValide( String id, String mp ) throws ClassNotFoundException{
    		Class.forName("com.mysql.jdbc.Driver");
        try(Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:8889/bdd_client?user=root&password=root");
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery( "SELECT idUtilisateur FROM Utilisateur WHERE nomUtilisateur = '"+ id +"' and motDePasse = '"+ mp +"'" )) {
	    	    if(resultat.next() != false) {
                    return true;
                }
	    	} catch ( SQLException e ) {
	    	    /* Gérer les éventuelles erreurs ici */
	    		e.printStackTrace();
	    	}
	    	
        return false;
    }
}
