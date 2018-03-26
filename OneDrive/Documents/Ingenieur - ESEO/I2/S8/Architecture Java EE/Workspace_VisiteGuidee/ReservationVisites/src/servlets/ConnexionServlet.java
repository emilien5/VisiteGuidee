package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jee.TrouverVisite;
import jee.TrouverVisiteResponse;
import jee.Visite;

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
    public String urlBddclientMac = "jdbc:mysql://localhost:8889/bdd_client?user=root&password=root";
    public String urlBddGestionVisitesMac = "jdbc:mysql://localhost:8889/bdd_reservation?user=root&password=root";
    
    public List<String> listeTypeVisite = new ArrayList<>();
    public List<String> listeVille = new ArrayList<>();
    public List<String> listeDateVisite = new ArrayList<>();
    public List<String> listePrixVisite = new ArrayList<>();
    
    Set<String> setListeTypeVisite = new HashSet<String>();
    Set<String> setListeVille = new HashSet<String>();
//    Set<String> setListeDateVisite = new HashSet<String>();
//    Set<Integer> setListePrixVisite = new HashSet<Integer>();
    
    public int tailleListeTypes = 0;
    public int tailleListeVille = 0; 
    public int nbVisites = 0;
    
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
				remplissageListes();
				
				if(valide == true ) {
					/* Stockage du résultat et des messages d'erreur dans l'objet request */
			        request.setAttribute( CHAMP_NOM_UTILISATEUR, nomUtilisateur );
			        request.setAttribute( CHAMP_PASS, motDePasse );			        
			        request.setAttribute("TypeVisite", this.listeTypeVisite);
			        request.setAttribute("Ville", this.listeVille);
				    request.setAttribute("DateVisite", this.listeDateVisite);
				    request.setAttribute("PrixVisite", this.listePrixVisite);
				    request.setAttribute("NbVisites", nbVisites);
				    request.setAttribute("tailleListeTypes", tailleListeTypes);
				    request.setAttribute("tailleListeVille", tailleListeVille);
				    
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
	    
	    private void remplissageListes() throws ClassNotFoundException{
	    	Visite uneVisite = new Visite();
	    	uneVisite.setDateVisite("none");
	    	uneVisite.setPrixVisite(0);
	    	uneVisite.setTypeVisite("none");
	    	uneVisite.setVille("none");
	    	
	    	TrouverVisite serviceTrouver = new TrouverVisite();
	    	serviceTrouver.setArg0(uneVisite);
	    	TrouverVisiteResponse reponseServiceTrouver = new TrouverVisiteResponse();
	    	nbVisites = reponseServiceTrouver.getReturn().size();	    	
			for(int i=0; i<nbVisites; i++) {
				setListeTypeVisite.add(reponseServiceTrouver.getReturn().get(i).getTypeVisite());
				setListeVille.add(reponseServiceTrouver.getReturn().get(i).getVille());
//				setListeDateVisite.add(reponseServiceTrouver.getReturn().get(i).getDateVisite());
//				setListePrixVisite.add(reponseServiceTrouver.getReturn().get(i).getPrixVisite());
			}
			listeTypeVisite.addAll(listeTypeVisite);
			listeVille.addAll(listeVille);
//			listeDateVisite.addAll(listeDateVisite);
//			listePrixVisite.addAll(listePrixVisite);
			tailleListeTypes = listeTypeVisite.size();
			tailleListeVille = listeVille.size();
	    }
	}