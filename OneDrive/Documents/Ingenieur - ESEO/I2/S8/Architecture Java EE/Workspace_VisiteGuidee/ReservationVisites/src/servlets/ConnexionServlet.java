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

import org.apache.velocity.runtime.directive.Parse;

import jee.GestionVisiteSEI;
import jee.GestionVisiteService;
import jee.Visite;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String nomUtilisateur;
	private String motDePasse;
	
	private String typeSelectionne;
	private String villeSelectionnee;
	private int prixSelectionne;
    
	public static final String VUE = "/WEB-INF/reservation.jsp";
	public static final String VUE_ERREUR = "/WEB-INF/connexionErreurs.jsp";
	public static final String VUE_INSCRIPTION = "/WEB-INF/inscription.jsp";
    public static final String CHAMP_NOM_UTILISATEUR = "nom";
    public static final String CHAMP_PASS = "motdepasse";
    
    public String urlBddclientWindows = "jdbc:mysql://localhost:3306/bdd_client?user=user&password=user";
    public String urlBddclientMac = "jdbc:mysql://localhost:8889/bdd_client?user=root&password=root";

    
    public List<String> listeTypeVisite = new ArrayList<>();
    public List<String> listeVille = new ArrayList<>();
    public List<String> listeDateVisite = new ArrayList<>();
    public List<String> listePrixVisite = new ArrayList<>();
    
    Set<String> setListeTypeVisite = new HashSet<String>();
    Set<String> setListeVille = new HashSet<String>();
    public List<String> nomTypes = new ArrayList<>();
    public List<String> nomVilles = new ArrayList<>();
    
    ArrayList<Visite> listeVisite = new ArrayList<Visite>();
    
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
	        nomUtilisateur = request.getParameter(CHAMP_NOM_UTILISATEUR);
	        motDePasse = request.getParameter(CHAMP_PASS);

	        if(request.getParameter(CHAMP_NOM_UTILISATEUR) == null || request.getParameter(CHAMP_PASS) == null) {
		        nomUtilisateur = request.getParameter("user");
		        motDePasse = request.getParameter("password");
	        }

	        if(request.getParameter("Type") != null) typeSelectionne = request.getParameter("Type");
	        if(request.getParameter("Ville") != null) villeSelectionnee = request.getParameter("Ville");
	        if(request.getParameter("Prix") != null) prixSelectionne = Integer.parseInt(request.getParameter("Prix"));
	        
	        try {
				boolean valide = informationValide(nomUtilisateur, motDePasse);				
				remplissageListes();

				if(valide == true) {
					/* Stockage du résultat et des messages d'erreur dans l'objet request */
			        request.setAttribute(CHAMP_NOM_UTILISATEUR, nomUtilisateur);
			        request.setAttribute(CHAMP_PASS, motDePasse);		
			        
			        request.setAttribute("TypeVisite", this.listeTypeVisite);
			        request.setAttribute("Ville", this.listeVille);
				    request.setAttribute("DateVisite", this.listeDateVisite);
				    request.setAttribute("PrixVisite", this.listePrixVisite);
				    
				    request.setAttribute("nomTypes", nomTypes);
				    request.setAttribute("nomVilles", nomVilles);
				    
				    request.setAttribute("NbVisites", nbVisites);
				    request.setAttribute("tailleListeTypes", tailleListeTypes);
				    request.setAttribute("tailleListeVille", tailleListeVille);
				    
				    request.setAttribute("listeVisite", listeVisite);

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
	        try(Connection connexion = DriverManager.getConnection(urlBddclientWindows);
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
	    
	    private void remplissageListes() throws ClassNotFoundException{
	    	this.listeDateVisite.clear();
	    	this.listePrixVisite.clear();
	    	this.listeTypeVisite.clear();
	    	this.listeVille.clear();
	    	
	    	Visite uneVisite = new Visite();
	    	uneVisite.setDateVisite("none");
	    	uneVisite.setPrixVisite(0);
	    	uneVisite.setTypeVisite("none");
	    	uneVisite.setVille("none");
	    	
	    	if(this.typeSelectionne != null) uneVisite.setTypeVisite(this.typeSelectionne);
	    	if(this.villeSelectionnee != null) uneVisite.setVille(this.villeSelectionnee);
	    	if(this.prixSelectionne != 0) uneVisite.setPrixVisite(this.prixSelectionne);

	    	GestionVisiteService service = new GestionVisiteService();
	    	GestionVisiteSEI port = service.getGestionVisitePort();
	    	listeVisite = (ArrayList<Visite>) port.trouverVisite(uneVisite);
	    	nbVisites = listeVisite.size();

			for(int i=0; i<nbVisites; i++) {
				this.listeTypeVisite.add(listeVisite.get(i).getTypeVisite());
				this.listeVille.add(listeVisite.get(i).getVille());
				this.listeDateVisite.add(listeVisite.get(i).getDateVisite());
				this.listePrixVisite.add(String.valueOf(listeVisite.get(i).getPrixVisite()));
			}
			setListeTypeVisite.addAll(this.listeTypeVisite);
			setListeVille.addAll(this.listeVille);
			nomTypes = new ArrayList<String>(setListeTypeVisite);
			nomVilles = new ArrayList<String>(setListeVille);
			tailleListeTypes = setListeTypeVisite.size();
			tailleListeVille = setListeVille.size();
			
	    }
	}