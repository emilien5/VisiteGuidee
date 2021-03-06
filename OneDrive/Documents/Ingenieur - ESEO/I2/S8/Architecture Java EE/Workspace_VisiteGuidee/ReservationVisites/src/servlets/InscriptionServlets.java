package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InscriptionServlets
 */
@WebServlet("/InscriptionServlets")
public class InscriptionServlets extends HttpServlet {
		private static final long serialVersionUID = 1L;
		public static final String VUE          = "/WEB-INF/inscriptionErreurs.jsp";
	    public static final String CHAMP_EMAIL  = "email";
	    public static final String CHAMP_PASS   = "motdepasse";
	    public static final String CHAMP_CONF   = "confirmation";
	    public static final String CHAMP_NOM    = "nom";
	    public static final String CHAMP_ADRESSE    = "adresse";
	    public static final String CHAMP_TEL    = "tel";
	    public static final String ATT_ERREURS  = "erreurs";
	    public static final String ATT_RESULTAT = "resultat";
	    
	    private static final String urlBddclientWindows = "jdbc:mysql://localhost:3306/bdd_client?user=user&password=user";
	    private static final String urlBddclientMac = "jdbc:mysql://localhost:8889/bdd_client?user=root&password=root";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlets() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String resultat;
	        Map<String, String> erreurs = new HashMap<String, String>();

	        /* R�cup�ration des champs du formulaire. */
	        String email = request.getParameter( CHAMP_EMAIL );
	        String motDePasse = request.getParameter( CHAMP_PASS );
	        String confirmation = request.getParameter( CHAMP_CONF );
	        String nom = request.getParameter( CHAMP_NOM );
	        String adresse = request.getParameter( CHAMP_ADRESSE );
	        String tel = request.getParameter( CHAMP_TEL );
	 
	        
	        /* Validation du champ email. */
	        try {
	            validationEmail( email );
	        } catch ( Exception e ) {
	            erreurs.put( CHAMP_EMAIL, e.getMessage() );
	        }

	        /* Validation des champs mot de passe et confirmation. */
	        try {
	            validationMotsDePasse( motDePasse, confirmation );
	        } catch ( Exception e ) {
	            erreurs.put( CHAMP_PASS, e.getMessage() );
	        }

	        /* Validation du champ nom. */
	        try {
	            validationNom( nom );
	        } catch ( Exception e ) {
	            erreurs.put( CHAMP_NOM, e.getMessage() );
	        }

	        /* Initialisation du r�sultat global de la validation. */
	        if ( erreurs.isEmpty() ) {
	            resultat = "Succès de l'inscription.";
	        } else {
	            resultat = "échec de l'inscription.";
	        }
	        
	        if(!resultat.equals("échec de l'inscription.")) {
		        try {
					enregistrementUtilisateur(nom, motDePasse, email, adresse, tel);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        /* Stockage du r�sultat et des messages d'erreur dans l'objet request */
	        request.setAttribute( ATT_ERREURS, erreurs );
	        request.setAttribute( ATT_RESULTAT, resultat );
	        request.setAttribute( ATT_RESULTAT, resultat );

	        /* Transmission de la paire d'objets request/response � notre JSP */
	        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );        
	    }

	    /**
	     * Valide l'adresse mail saisie.
	     */
	    private void validationEmail( String email ) throws Exception {
	        if ( email != null && email.trim().length() != 0 ) {
	            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	                throw new Exception( "Merci de saisir une adresse mail valide." );
	            }
	        } else {
	            throw new Exception( "Merci de saisir une adresse mail." );
	        }
	    }
	    /**
	     * Valide les mots de passe saisis.
	     */
	    private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception{
	        if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
	            if (!motDePasse.equals(confirmation)) {
	                throw new Exception("Les mots de passe entr�s sont différents, merci de les saisir à nouveau.");
	            } else if (motDePasse.trim().length() < 3) {
	                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
	            }
	        } else {
	            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
	        }
	    }

	    /**
	     * Valide le nom d'utilisateur saisi.
	     */
	    private void validationNom( String nom ) throws Exception {
	        if ( nom != null && nom.trim().length() < 3 ) {
	            throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractéres." );
	        }
	    }
	    
	    /* Valide le nom d'utilisateur saisi */
	    private boolean enregistrementUtilisateur(String nom, String moDePasse, String email, String adresse, String tel) throws ClassNotFoundException{
	    		Class.forName("com.mysql.jdbc.Driver");
	        	try {
	        		Connection connection = DriverManager.getConnection(urlBddclientWindows);
	        		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Utilisateur(nomUtilisateur,motDePasse, email, adresse, tel) VALUES('"+nom+"','"+moDePasse+"','"+email+"','"+adresse+"','"+ tel +"')");
	        		preparedStatement.executeUpdate();
		       	if(preparedStatement.isClosed() == true) {
		                    return true;
		        }
		    	} catch ( SQLException e ) {
		    	    /* G�rer les �ventuelles erreurs ici */
		    		e.printStackTrace();
		    	} 	
	        return false;
	    }
	}