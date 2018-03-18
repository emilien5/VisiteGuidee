<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reservation</title>

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
                <th>Ville</th>
                <th>Date</th>
                <th>Prix</th>
            </tr>
            <tbody  >
                <tr >
              		<td>
              		<% String TypeVisite = (String) request.getAttribute("TypeVisite");out.println( TypeVisite );%>
              		 </td>
              		 <td>
              		<% String Ville = (String) request.getAttribute("Ville");out.println( Ville );%>
              		 </td>
              		 <td>
              		 <% String DateVisite = (String) request.getAttribute("DateVisite");out.println( DateVisite );%>
              		 </td>
              		 <td>
              		 <% String PrixVisite = (String) request.getAttribute("PrixVisite");out.println( PrixVisite );%>
              		 </td>
               <%--  <% for(int i = 0; i < (int)request.getAttribute("nombreVisites"); i++) { %>
                    <td>
                        <% out.println((String)request.getAttribute("TypeVisite")); %>
                    </td>
                    <td>
                        <% out.println((String)request.getAttribute("Ville")); %>
                    </td>
                    <td>
                        <% out.println((String)request.getAttribute("Datevisite")); %>
                    </td>
                    <td>
                        <% out.println((String)request.getAttribute("PrixVisite")); %>
                    </td>
                    <% } %> --%>
                </tr>
            </tbody>
        </table>
<br>
<br>
<a href="CreerVisite.jsp?nom=mamalem&motDePasse=network">Proposer une visite</a>
<br>
<a href="connexion.jsp">Déconnexion</a>
</body>
</html>
