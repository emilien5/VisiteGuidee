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


	<br>
	<input type="submit" value="Paiement" id="Paiement">
	<br>
	<br>
		<a href="index.jsp">Déconnexion</a>
	

</body>
</html>