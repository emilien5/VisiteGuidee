<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Reservation</title>
	<link type="text/css" rel="stylesheet" href="select.css" />

</head>

<body>
	Bienvenue <b><% String attribut = (String) request.getAttribute("nom");out.println( attribut );%></b>
	<br>
	<br>
	Choisissez vos visites parmis les  <% int NbVisites = (int) request.getAttribute("NbVisites");out.println( NbVisites );%> visites disponibles :
	<br>
	<br>
	
	<form  action="ConnexionServlet">
	<% String user = (String) request.getAttribute("nom");%>
	<% String password = (String) request.getAttribute("motdepasse");%>
	<input id="user" name="user" type="hidden" value="<%=user%>">
	<input id="password" name="password" type="hidden" value="<%=password%>">
	
	<b>TYPE :</b>
	
	<select name="Type" id="Type">	
  		<option value="none">None</option>
  		<% int nombreTypes = (int) request.getAttribute("tailleListeTypes");
               		 for(int i = 0; i < nombreTypes; i++) { %>
 		<option ><% List nomTypes = (List) request.getAttribute("nomTypes");
              			out.println( nomTypes.get(i).toString() );%></option>
	<% } %> 
	</select>

	<b>VILLE :</b>	
	<select name="Ville" id="Ville">
  		<option value="none">None</option>
  		<% int nombreVilles = (int) request.getAttribute("tailleListeVille");
               		 for(int i = 0; i < nombreVilles; i++) { %>
 		<option> <% List nomVilles = (List) request.getAttribute("nomVilles");
              			out.println( nomVilles.get(i).toString() );%></option>
	<% } %> 
	</select>

	<b>PRIX MAXIMUM :</b>
	<select name="Prix" id="Prix">
  		<option value="0">None</option>
 		<option value="50">50 euros</option>
  		<option value="100">100 euros</option>
  		<option value="150">150 euros</option>
  		<option value="200">200 euros</option>
	</select>	

	<b>DATE :</b>
	<input type="text" name="Date" value="none" id="Date" size="12">
	
	<br>
	<br>
	<input type="submit" value="Valider" id="Valider">
	</form>
	
	<form method="post"  action="ValiderReservationServlet">
	<br>
	<br>
	<table border="1" id="visite">
    	<tr>
        	<th>Choix</th>
            <th>Type</th>
            <th>Ville</th>
            <th>Date</th>
            <th>Prix (euros)</th>
		</tr>
            <tbody>
                <tr>
	               	<%@ page import ="java.util.ArrayList"%>
					<%@ page import ="java.util.List"%>
                
               		 <% int nombreVistite = (int) request.getAttribute("NbVisites");
               		 for(int i = 0; i < nombreVistite; i++) { %>
               		 <% String valeur = "checkBox"+String.valueOf(i); %>
               		 <td>
              		 	<input id="<%= valeur %>" name="<%= valeur %>" type="checkbox">
              		 </td>
              		 
              		 
              		<td>
              		<% List TypeVisite = (List) request.getAttribute("TypeVisite");
              			out.println( TypeVisite.get(i) );%>
              		 </td>
              		 
              		 <td>
              		<% List Ville = (List) request.getAttribute("Ville");
              			out.println( Ville.get(i) );%>
              		 </td>
              		 
              		 <td>
              		<% List DateVisite = (List) request.getAttribute("DateVisite");
              			out.println( DateVisite.get(i) );%>
              		 </td>
              		 
              		 <td>
              		<% List PrixVisite = (List) request.getAttribute("PrixVisite");
              		 	out.println( PrixVisite.get(i) );%>
              		 </td>
              		 
              	</tr>
              		<% } %> 

			</tbody>
	</table>
	<br>
	Nombre de places *: 
	<input id="nbPlaces" name="nbPlaces" type="text" required>
	<br>
	<br>
	<input type="submit" value="Réserver" id="Reserver">
	<br>
	<br>
		<a href="index.jsp">Déconnexion</a>
	</form>
</body>
</html>