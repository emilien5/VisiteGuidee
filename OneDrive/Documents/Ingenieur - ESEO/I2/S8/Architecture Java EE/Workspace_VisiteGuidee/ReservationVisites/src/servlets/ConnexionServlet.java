package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String nomUtilisateur;
	private String motDePasse;
    
	public static final String VUE = "/WEB-INF/reservation.jsp";
	public static final String VUE_ERREUR = "/WEB-INF/connexionErreurs.jsp";
	public static final String VUE_INSCRIPTION = "/WEB-INF/inscription.jsp";
    public static final String CHAMP_NOM_UTILISATEUR = "nom";
    public static final String CHAMP_PASS = "motdepasse";
    
    public String urlBddclientWindows = "jdbc:mysql://localhost:3306/bdd_client?user=user&password=user";
    public String urlBddGestionVisitesWindows = "jdbc:mysql://localhost:3306/gestionvisites?user=user&password=user";
    
    public List<String> listeTypeVisite = new ArrayList<>();
    public List<String> listeVille = new ArrayList<>();
    public List<String> listeDateVisite = new ArrayList<>();
    public List<String> listePrixVisite = new ArrayList<>();
    public String nbVisites = "0";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Map<String, String> erreurs = new HashMap<>();
		 	
	        /* R�cup�ration des champs du formulaire. */
	        nomUtilisateur = request.getParameter( CHAMP_NOM_UTILISATEUR );
	        motDePasse = request.getParameter( CHAMP_PASS );

	        try {
				boolean valide = informationValide(nomUtilisateur, motDePasse);
				boolean valide2 = connexionBddVisite();
				
				if(valide == true && valide2 == true) {
					/* Stockage du r�sultat et des messages d'erreur dans l'objet request */
			        request.setAttribute( CHAMP_NOM_UTILISATEUR, nomUtilisateur );
			        request.setAttribute( CHAMP_PASS, motDePasse );
			        
			        request.setAttribute("TypeVisite", this.listeTypeVisite);
			        request.setAttribute("Ville", this.listeVille);
				    	request.setAttribute("DateVisite", this.listeDateVisite);
				    	request.setAttribute("PrixVisite", this.listePrixVisite);
				    	request.setAttribute("NbVisites", nbVisites);
				    	System.out.println(this.listeTypeVisite);
			        /* Transmission de la paire d'objets request/response � notre JSP */
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
	        try(Connection connexion = DriverManager.getConnection(urlBddclientWindows);
	            Statement statement = connexion.createStatement();
	            ResultSet resultat = statement.executeQuery( "SELECT idUtilisateur FROM Utilisateur WHERE nomUtilisateur = '"+ id +"' and motDePasse = '"+ mp +"'" )) {
		    	    if(resultat.next() != false) {
	                    return true;
	            }
		    	} catch ( SQLException e ) {
		    	    /* G�rer les �ventuelles erreurs ici */
		    		e.printStackTrace();
		    	} 	
	        return false;
	    }
	    
	    private boolean connexionBddVisite() throws ClassNotFoundException{
			Class.forName("com.mysql.jdbc.Driver");
		    try(Connection connexionVisite = DriverManager.getConnection(urlBddGestionVisitesWindows);
		    		Statement statement = connexionVisite.createStatement();
		    		ResultSet resultat = statement.executeQuery( "SELECT typeVisite, ville, dateVisite, prixVisite FROM Visite" )){

		    	
		    	if(resultat.next() != false) {
			    	/* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
		    		this.listeTypeVisite.clear();
		    		this.listePrixVisite.clear();
		    		this.listeDateVisite.clear();
		    		this.listeVille.clear();
		    		this.listeTypeVisite.add(resultat.getString("typeVisite"));
		    		this.listeVille.add(resultat.getString("ville"));
		    		this.listeDateVisite.add(resultat.getString("dateVisite"));
		    		this.listePrixVisite.add(resultat.getString("prixVisite"));
			    	while ( resultat.next() ) {
			    		
			    		this.listeTypeVisite.add(resultat.getString("typeVisite"));
			    		this.listeVille.add(resultat.getString("ville"));
			    		this.listeDateVisite.add(resultat.getString("dateVisite"));
			    		this.listePrixVisite.add(resultat.getString("prixVisite"));
			    		this.nbVisites = String.valueOf(this.listeTypeVisite.size());
			    	}
	            return true;
	        }
		    	
		    	} catch ( SQLException e ) {
		    	    /* G�rer les �ventuelles erreurs ici */
		    		e.printStackTrace();
		    	}    	
		    return false;
	    }
	}