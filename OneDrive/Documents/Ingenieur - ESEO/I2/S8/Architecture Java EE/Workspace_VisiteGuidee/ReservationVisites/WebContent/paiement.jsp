<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Paiement</title>
</head>
<body>

	Bienvenue <b><% String nomUtilisateur = (String) request.getAttribute("nomUtilisateur");out.println( nomUtilisateur );%></b>
	<br>
	<br>

<form method="post"  action="ValiderReservationServlet">
	<br>
	<br>
	<table border="1" id="visite">
    	<tr>
            <th>Type</th>
            <th>Ville</th>
            <th>Date</th>
            <th>Prix (euros)</th>
		</tr>
            <tbody>
                <tr>
	               	<%@ page import ="java.util.ArrayList"%>
					<%@ page import ="java.util.List"%>
                
               		 <% int nombreVistite = (int) request.getAttribute("nombreVisitesCb");
               		 for(int i = 0; i <nombreVistite; i++) { %>
              		 
              		<td>
              		<% List TypeVisite = (List) request.getAttribute("listeTypeVisiteCb");
              			out.println( TypeVisite.get(i) );%>
              		 </td>
              		 
              		 <td>
              		<% List Ville = (List) request.getAttribute("listeVilleCb");
              			out.println( Ville.get(i) );%>
              		 </td>
              		 
              		 <td>
              		<% List DateVisite = (List) request.getAttribute("listeDateVisiteCb");
              			out.println( DateVisite.get(i) );%>
              		 </td>
              		 
              		 <td>
              		<% List PrixVisite = (List) request.getAttribute("listePrixVisiteCb");
              		 	out.println( PrixVisite.get(i) );%>
              		 </td>
              		 
              	</tr>
              		<% } %> 

			</tbody>
	</table>
	<br>
	<br>
	<input type="submit" value="Paiement" id="Paiement">
	<br>
	<br>
		<a href="index.jsp">Déconnexion</a>
	

</body>
</html>