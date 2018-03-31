<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Annulation</title>
</head>
<body>

<font color="green">Vos annulations ont été effectuées avec succès !</font>

<form method="post" action="ConnexionServlet">
<% String user = (String) request.getAttribute("user");%>
<% String psswd = (String) request.getAttribute("psswd");%>

<input id="nom" name="nom" type="hidden" value="<%=user%>">
<input id="motdepasse" name="motdepasse" type="hidden" value="<%=psswd%>">
<br>
<br>
<input type="submit" value="Retour" class="sansLabel" />
<br>
<br>
<a href="index.jsp">Déconnexion</a>
</form>
</body>
</html>