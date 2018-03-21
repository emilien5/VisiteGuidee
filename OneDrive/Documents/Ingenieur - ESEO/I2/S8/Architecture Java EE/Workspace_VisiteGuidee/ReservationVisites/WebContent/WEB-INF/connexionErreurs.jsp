<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
    </head>
    <body>
         <form method="post" action="ConnexionServlet">
         <div>
	    <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via ce formulaire.</p>
				<label for="nom">Nom d'utilisateur</label>
                <input type="text" id="nom" name="nom" value="" size="20" maxlength="20" />
                <span class="erreur">${erreurs['nom']}</span>
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${erreurs['motdepasse']}</span>
                <br />                
             	
             	<p><font color="red">Le nom d'utilisateur ou mot de passe est incorrect.</font></p>
                <input type="submit" value="Connection" class="sansLabel" />
               	<a href="inscription.jsp">Pas de compte ?</a>
                
                <br />                     
                <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
            </fieldset>
            </div>
        </form>
    </body>
</html>