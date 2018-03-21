<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
</head>
    <body>
    	<div id=inscrit>
        <form method="post" action="InscriptionServlets">
	    	<fieldset>
                <legend>Inscription</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>
				
				<label for="Nom">Nom utilisateur<span class="requis">*</span></label>
                <input type="text" id="nom" name="nom" value="" size="20" maxlength="60" />
                <br>
                
                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <br>

                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <br>

				<label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="" size="20" maxlength="60" />
                <br>
                
                <label for="nom">Tél</label>
                <input type="text" id="tel" name="tel" value="" size="20" maxlength="20" />
                <br>

                <input type="submit" value="Inscription" class="sansLabel"/>
                	<a href="index.jsp"> Déjà un compte ?</a>
                	
            </fieldset>
		</form>
        </div>        
    </body>
</html>