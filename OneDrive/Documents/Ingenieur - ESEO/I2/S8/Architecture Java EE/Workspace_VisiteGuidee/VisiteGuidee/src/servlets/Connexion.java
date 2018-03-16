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
	private Boolean valide;
	
	public static final String VUE = "/WEB-INF/reservation.jsp";
	public static final String VUE_ERREUR = "/WEB-INF/connexionErreur.jsp";
    public static final String CHAMP_NOM_UTILISATEUR = "nom";
    public static final String CHAMP_PASS = "motdepasse";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        Map<String, String> erreurs = new HashMap<String, String>();

        /* Récupération des champs du formulaire. */
        nomUtilisateur = request.getParameter( CHAMP_NOM_UTILISATEUR );
        motDePasse = request.getParameter( CHAMP_PASS );

        try {
			informationValide(nomUtilisateur, motDePasse);
			
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
    private void informationValide( String id, String mp ) throws Exception {
	    	String url = "jdbc:mysql://localhost:8888/bdd_client";
	    	String utilisateurBDD = "admin";
	    	String motDePasseBDD = "admin";
	    	Connection connexion = null;
	    	System.out.println("SDFGHJKDFGHJKDFGHJK");
	    	try {
	    	    connexion = DriverManager.getConnection( url, utilisateurBDD, motDePasseBDD );
	
	    	    /* Création de l'objet gérant les requêtes */
	    	    Statement statement = connexion.createStatement();
		    	ResultSet resultat = statement.executeQuery( "SELECT idUtilisateur FROM Utilisateur WHERE nomUtilisateur = "+ id +" and motDePasse = "+ mp +";" );
	    	    if(resultat == null) {
	    	    		this.valide = false;
	            throw new Exception( "Le nom d'utilisateur est incorect." );
	        } else {
	        		this.valide = true;
	        }
	
	    	} catch ( SQLException e ) {
	    	    /* Gérer les éventuelles erreurs ici */
	    	} finally {
	    	    if (connexion != null)
	    	        try {
	    	            /* Fermeture de la connexion */
	    	            connexion.close();
	    	        } catch (SQLException ignore) {
	    	            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
	    	        }
	    	}
    }
   
}