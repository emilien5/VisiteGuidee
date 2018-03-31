package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jee.GestionVisiteSEI;
import jee.GestionVisiteService;

/**
 * Servlet implementation class ValiderPaiementServlet
 */
@WebServlet("/ValiderPaiementServlet")
public class ValiderPaiementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String VUE = "/WEB-INF/paiement.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderPaiementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		List<String> codeReservation = (List<String>) session.getAttribute("codeReservation");
		String user = (String) session.getAttribute("nomUtilisateur");
		String psswd = (String) session.getAttribute("motDePasse");	
		
		System.out.println(codeReservation);
		payerrReservation(codeReservation);
		
		request.setAttribute("user", user);
		request.setAttribute("psswd", psswd);
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		
	}
	
	public void payerrReservation(List<String> code) {		
		GestionVisiteService service = new GestionVisiteService();
    	GestionVisiteSEI port = service.getGestionVisitePort();
    	
    	for(int i=0; i<code.size(); i++) {
    		port.payerVisite(Integer.parseInt(code.get(i)));
    	}
    	
	}

}
