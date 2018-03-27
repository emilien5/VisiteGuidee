package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.transport.http.HTTPSession;

/**
 * Servlet implementation class ValiderReservationServlet
 */
@WebServlet("/ValiderReservationServlet")
public class ValiderReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String VUE = "/paiement.jsp";
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
		
		request.setAttribute("nomUtilisateur", user);

		  this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		// faire requete pour recuperer les reservations de l'utilisateur connecte //
		// ajouter les valeur a des listes type ville date prix //
		// envoyer les donnees dans un jsp et rediriger vers la page de paiement //
		
	}

}
