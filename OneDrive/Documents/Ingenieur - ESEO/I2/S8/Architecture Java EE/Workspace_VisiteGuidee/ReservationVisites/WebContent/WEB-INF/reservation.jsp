<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
title>Reservation</title>

</head>
<body>

Bienvenue <% String attribut = (String) request.getAttribute("nom");out.println( attribut );%>
<br>
Il y a  <% String NbVisites = (String) request.getAttribute("NbVisites");out.println( NbVisites );%> visites.
<br>
<br>
 <table border="1" id="visite">
            <tr>
            	<th>Type</th>
                <th>Type</th>
                <th>Ville</th>
                <th>Date</th>
                <th>Prix</th>
            </tr>
            <tbody  >
                <tr >
	               	<%@ page import ="java.util.ArrayList"%>
					<%@ page import ="java.util.List"%>
                
               		 <% 
               			int nombreVistite = Integer.parseInt((String)request.getAttribute("NbVisites"));
               		 for(int i = 0; i < nombreVistite; i++) { %>
               		 <td>
              		<input id="checkBox" type="checkbox">
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
                </tr>
            </tbody>
        </table>
<br>
<br>

<br>
<a href="connexion.jsp">Déconnexion</a>
</body>
</html>