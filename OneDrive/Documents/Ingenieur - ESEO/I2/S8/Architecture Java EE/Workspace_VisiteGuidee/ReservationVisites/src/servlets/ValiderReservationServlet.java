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

import org.apache.cxf.transport.http.HTTPSession;

import jee.Visite;

/**
 * Servlet implementation class ValiderReservationServlet
 */
@WebServlet("/ValiderReservationServlet")
public class ValiderReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String VUE = "/paiement.jsp";
	
	public List<Boolean> etatCb = new ArrayList<>();
	ArrayList<Visite> listeVisiteCb = new ArrayList<Visite>();
    public List<String> listeTypeVisiteCb = new ArrayList<>();
    public List<String> listeVilleCb = new ArrayList<>();
    public List<String> listeDateVisiteCb = new ArrayList<>();
    public List<String> listePrixVisiteCb = new ArrayList<>();
    
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("nomUtilisateur");
		String psswd = (String) session.getAttribute("motDePasse");	
		int nbVisites = (int)  session.getAttribute("NbVisites");
		List<String> listeTypeVisite = (List<String>) session.getAttribute("listeTypeVisite");
		List<String> listeVille = (List<String>) session.getAttribute("listeVille");
		List<String> listeDateVisite = (List<String>) session.getAttribute("listeDateVisite");
		List<String> listePrixVisite = (List<String>) session.getAttribute("listePrixVisite");
		
		remplissageListes(request,nbVisites,listeTypeVisite,listeVille,listeDateVisite,listePrixVisite);
		 
		request.setAttribute("nomUtilisateur", user);
		request.setAttribute("nombreVisitesCb", nombreVisitesCb);
		request.setAttribute("listeTypeVisiteCb", listeTypeVisiteCb);
		request.setAttribute("listeVilleCb", listeVilleCb);
		request.setAttribute("listeDateVisiteCb", listeDateVisiteCb);
		request.setAttribute("listePrixVisiteCb", listePrixVisiteCb);
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		// faire requete pour recuperer les reservations de l'utilisateur connecte //
		// ajouter les valeur a des listes type ville date prix //
		// envoyer les donnees dans un jsp et rediriger vers la page de paiement //
		
	}
	
	public void remplissageListes(HttpServletRequest request, int nbVisites, List<String> listeTypeVisite, List<String> listeVille, List<String> listeDateVisite, List<String> listePrixVisite ) {
		etatCb.clear();	
		listeDateVisiteCb.clear();
		listePrixVisiteCb.clear();
		listeTypeVisiteCb.clear();
		listeVilleCb.clear();
		System.out.println(nbVisites);
		for(int i=0; i<nbVisites; i++ ) {
			etatCb.add(request.getParameter("checkBox"+i)!= null);
			if(etatCb.get(i)==true) {
				listeTypeVisiteCb.add(listeTypeVisite.get(i));
				listeVilleCb.add(listeVille.get(i));
				listeDateVisiteCb.add(listeDateVisite.get(i));
				listePrixVisiteCb.add(listePrixVisite.get(i));
			}
			nombreVisitesCb = listeTypeVisiteCb.size();
			System.out.println(nombreVisitesCb);
		}
		System.out.println(etatCb);
	}

}
