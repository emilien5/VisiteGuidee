package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jee.GestionVisiteSEI;
import jee.GestionVisiteService;
import jee.ReservationVisite;
import jee.Visite;

/**
 * Servlet implementation class ValiderReservationServlet
 */
@WebServlet("/ValiderReservationServlet")
public class ValiderReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String VUE = "/paiement.jsp";
	
	/* Création des ArrayList pour les valeurs des checkBox (cocher ou non, type,ville,date,prix,id,visite) */
	public List<Boolean> etatCb = new ArrayList<>();
	ArrayList<Visite> listeVisiteCb = new ArrayList<Visite>();
    public List<String> listeTypeVisiteCb = new ArrayList<>();
    public List<String> listeVilleCb = new ArrayList<>();
    public List<String> listeDateVisiteCb = new ArrayList<>();
    public List<String> listePrixVisiteCb = new ArrayList<>();
    public List<String> listeIdVisiteCb = new ArrayList<>();
    public List<String> codeReservation = new ArrayList<>();
    
    public int nombreVisitesCb;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* On recupere les attributs de la session (identifiants et nombreVisites) */
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("nomUtilisateur");
		//String psswd = (String) session.getAttribute("motDePasse");	
		int nbVisites = (int)  session.getAttribute("NbVisites");
		//int idUtilisateur = (int) session.getAttribute("idUtilisateur");
		
		/* On recupere les listes de la sessions */
		@SuppressWarnings("unchecked")
		List<String> listeTypeVisite = (List<String>) session.getAttribute("listeTypeVisite");
		@SuppressWarnings("unchecked")
		List<String> listeVille = (List<String>) session.getAttribute("listeVille");
		@SuppressWarnings("unchecked")
		List<String> listeDateVisite = (List<String>) session.getAttribute("listeDateVisite");
		@SuppressWarnings("unchecked")
		List<String> listePrixVisite = (List<String>) session.getAttribute("listePrixVisite");
		@SuppressWarnings("unchecked")
		List<String> listeIdVisite = (List<String>) session.getAttribute("listeIdVisite");
		
		/* On effectue la reservation suivant les CheckBox cochees */
		reservation(request,nbVisites,listeTypeVisite,listeVille,listeDateVisite,listePrixVisite,listeIdVisite);
		
		session.setAttribute("codeReservation", codeReservation);	
		System.out.println(codeReservation);
		
		request.setAttribute("nomUtilisateur", user);
		request.setAttribute("nombreVisitesCb", nombreVisitesCb);
		request.setAttribute("listeTypeVisiteCb", listeTypeVisiteCb);
		request.setAttribute("listeVilleCb", listeVilleCb);
		request.setAttribute("listeDateVisiteCb", listeDateVisiteCb);
		request.setAttribute("listePrixVisiteCb", listePrixVisiteCb);
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );		
	}
	
	
	
	/* Methodes pour remplir les listes selons check box
	 * puis reserver les visites selon le nombres de place et l'utilisateur qui reserve
	 * @param : les listes, la requetes et le nombre de Visites
	 */ 
	public void reservation(HttpServletRequest request, int nbVisites, List<String> listeTypeVisite, List<String> listeVille, List<String> listeDateVisite, List<String> listePrixVisite, List<String> listeIdVisite ) {
		/* On vide les listes pour avoir des listes propres a cahque utilisations */
		etatCb.clear();	
		listeDateVisiteCb.clear();
		listePrixVisiteCb.clear();
		listeTypeVisiteCb.clear();
		listeVilleCb.clear();
		listeIdVisiteCb.clear();
		codeReservation.clear();
		
		/* On recupere les nbPlaces que l'utilisateur reserve */
		int nbPlaces = Integer.parseInt(request.getParameter("nbPlaces"));
		
		/* On utilise le WSDL pour pouvoir reserver les visites selectionnees */
		ReservationVisite maReservation = new ReservationVisite();		
		GestionVisiteService service = new GestionVisiteService();
    	GestionVisiteSEI port = service.getGestionVisitePort();
		
    	/* On rempli les listes */
		for(int i=0; i<nbVisites; i++ ) {
			etatCb.add(request.getParameter("checkBox"+i)!= null);
			/* Dans le cas ou la visite est cochee */
			if(etatCb.get(i)==true) {
				listeTypeVisiteCb.add(listeTypeVisite.get(i));
				listeVilleCb.add(listeVille.get(i));
				listeDateVisiteCb.add(listeDateVisite.get(i));
				listePrixVisiteCb.add(listePrixVisite.get(i));
				listeIdVisiteCb.add(listeIdVisite.get(i));
			}
			nombreVisitesCb = listeTypeVisiteCb.size();
		}
		/* On reserve les visites correspondantes */
		for(int k=0; k<nombreVisitesCb; k++) {
			maReservation.setIdVisite(Integer.parseInt(listeIdVisiteCb.get(k)));
			maReservation.setNombrePlace(nbPlaces);
			//maReservation.setIdClient(idUtilisateur);
			port.reserverVisite(maReservation);
			codeReservation.add(String.valueOf(maReservation.getIdReservation()));
		}		
	}

}
