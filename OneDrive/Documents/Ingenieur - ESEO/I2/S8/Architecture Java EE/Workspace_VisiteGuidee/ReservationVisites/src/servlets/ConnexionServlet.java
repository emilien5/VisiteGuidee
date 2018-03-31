package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jee.GestionVisiteSEI;
import jee.GestionVisiteService;
import jee.Visite;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* Les informations de l'utilisateurs */
	private String nomUtilisateur;
	private String motDePasse;
	private int idUtilisateur;
	
	/* Les listes que l'utilisateur a trier apres une recherche */
	private String typeSelectionne;
	private String villeSelectionnee;
	private int prixSelectionne;
	private String dateSelectionnee;
    
	/* Les attributs passe-partouts de redirections et identifiants */
	public static final String VUE = "/WEB-INF/reservation.jsp";
	public static final String VUE_ERREUR = "/WEB-INF/connexionErreurs.jsp";
	public static final String VUE_INSCRIPTION = "/WEB-INF/inscription.jsp";
    public static final String CHAMP_NOM_UTILISATEUR = "nom";
    public static final String CHAMP_PASS = "motdepasse";
    
    /* Connections a la BDD pour Windows  et pour Mac pour ne pas utiliser de ficihier .properties */
    public String urlBddclientWindows = "jdbc:mysql://localhost:3306/bdd_client?user=user&password=user";
    public String urlBddclientMac = "jdbc:mysql://localhost:8889/bdd_client?user=root&password=root";

    /* Les listes de chaque attributs de l'entite Visite */
    public List<String> listeTypeVisite = new ArrayList<>();
    public List<String> listeVille = new ArrayList<>();
    public List<String> listeDateVisite = new ArrayList<>();
    public List<String> listePrixVisite = new ArrayList<>();
    public List<String> listeIdVisite = new ArrayList<>();
    
    /* Creation de HashSet pour ne pas avoir de doublon dans les listes deroulantes */
    Set<String> setListeTypeVisite = new HashSet<String>();
    Set<String> setListeVille = new HashSet<String>();
    
    /* Liste deroulantes Types et Villes */
    public List<String> nomTypes = new ArrayList<>();
    public List<String> nomVilles = new ArrayList<>();
    
    /* Regroupe toutes les visites de la BDD */
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
		 /* Recuperation des champs du formulaire. */
	        nomUtilisateur = request.getParameter(CHAMP_NOM_UTILISATEUR);
	        motDePasse = request.getParameter(CHAMP_PASS);
	        HttpSession session = request.getSession();	        
	        if(request.getParameter(CHAMP_NOM_UTILISATEUR) == null || request.getParameter(CHAMP_PASS) == null) {
		        nomUtilisateur = request.getParameter("user");
		        motDePasse = request.getParameter("password");
	        }
	        if(request.getParameter("Type") != null) typeSelectionne = request.getParameter("Type");
	        if(request.getParameter("Ville") != null) villeSelectionnee = request.getParameter("Ville");
	        if(request.getParameter("Date") != null) dateSelectionnee = request.getParameter("Date");
	        if(request.getParameter("Prix") != null) prixSelectionne = Integer.parseInt(request.getParameter("Prix"));
	        
	        /* Se connecte a la BDD, verifie les informations et genere les listes necessaires */
	        try {
				boolean valide = informationValide(nomUtilisateur, motDePasse);				
				remplissageListes();
				getIdentifiant(nomUtilisateur, motDePasse);
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
				    
				    
			        session.setAttribute("nomUtilisateur", nomUtilisateur);
			        session.setAttribute("motDePasse", motDePasse);
			        session.setAttribute("NbVisites", nbVisites);
			        session.setAttribute("listeTypeVisite",listeTypeVisite);
			        session.setAttribute("listeVille",listeVille);
			        session.setAttribute("listeDateVisite",listeDateVisite);
			        session.setAttribute("listePrixVisite",listePrixVisite);
			        session.setAttribute("listeIdVisite", listeIdVisite);
			        session.setAttribute("idUtilisateur", idUtilisateur);
			        
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
	    
	    /* Recupere l'identifiant de l'utilisateur qui s'est connecter */
	    private int getIdentifiant(String id, String mp) throws SQLException, ClassNotFoundException {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	try(Connection connexion = DriverManager.getConnection(urlBddclientWindows);
		            Statement statement = connexion.createStatement();
		            ResultSet resultat = statement.executeQuery( "SELECT idUtilisateur FROM Utilisateur WHERE nomUtilisateur = '"+ id +"' and motDePasse = '"+ mp +"'" )) {
	    			if(resultat.next() != false) {
	    				idUtilisateur = resultat.getInt("idUtilisateur");
	    			}
	    	} catch ( SQLException e ) {
    	    /* Gérer les éventuelles erreurs ici */
    		e.printStackTrace();
	    } 
	    	return idUtilisateur;
	    }
	    
	    /* Rempli toutes les listes pour l'affichage sur le JSP, le tri et la selection */
	    private void remplissageListes() throws ClassNotFoundException{
	    	/* Nettoie toutes les listes */
	    	this.listeDateVisite.clear();
	    	this.listePrixVisite.clear();
	    	this.listeTypeVisite.clear();
	    	this.listeVille.clear();
	    	this.listeIdVisite.clear();
	    	listeVisite.clear();
	    	this.nomTypes.clear();
	    	this.nomVilles.clear();
	    	
	    	/* Creation de la visite  none pour recuperer toutes les visites existantes */
	    	Visite uneVisite = new Visite();
	    	uneVisite.setDateVisite("none");
	    	uneVisite.setPrixVisite(0);
	    	uneVisite.setTypeVisite("none");
	    	uneVisite.setVille("none");
	    	
	    	
	    	if(this.typeSelectionne != null) uneVisite.setTypeVisite(this.typeSelectionne);
	    	if(this.villeSelectionnee != null) uneVisite.setVille(this.villeSelectionnee);
	    	if(this.prixSelectionne != 0) uneVisite.setPrixVisite(this.prixSelectionne);
	    	if(this.dateSelectionnee != null) uneVisite.setDateVisite(this.dateSelectionnee);
	    	
	    	GestionVisiteService service = new GestionVisiteService();
	    	GestionVisiteSEI port = service.getGestionVisitePort();
	    	listeVisite = (ArrayList<Visite>) port.trouverVisite(uneVisite);
	    	nbVisites = listeVisite.size();

			for(int i=0; i<nbVisites; i++) {
				this.listeTypeVisite.add(listeVisite.get(i).getTypeVisite());
				this.listeVille.add(listeVisite.get(i).getVille());
				this.listeDateVisite.add(listeVisite.get(i).getDateVisite());
				this.listePrixVisite.add(String.valueOf(listeVisite.get(i).getPrixVisite()));
				this.listeIdVisite.add(String.valueOf(listeVisite.get(i).getIdVisite()));
			}
			
			setListeTypeVisite.addAll(this.listeTypeVisite);
			setListeVille.addAll(this.listeVille);
			nomTypes = new ArrayList<String>(setListeTypeVisite);
			nomVilles = new ArrayList<String>(setListeVille);
			tailleListeTypes = setListeTypeVisite.size();
			tailleListeVille = setListeVille.size();
			
			this.typeSelectionne = null;
			this.villeSelectionnee = null;
			this.prixSelectionne = 0;
			this.dateSelectionnee = null;
	    }
	}