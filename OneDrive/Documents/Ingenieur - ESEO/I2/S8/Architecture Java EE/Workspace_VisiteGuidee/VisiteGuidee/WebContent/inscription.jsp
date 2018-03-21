<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
    </head>
    <body>
    	<div id=inscrit>
        <form method="post" action="inscription">
	    <fieldset>
                <legend>Inscription</legend>
                <p>Vous pouvez vous inscrire via ce formulaire.</p>
				
				<label for="Nom">Nom utilisateur<span class="requis">*</span></label>
                <input type="text" id="nom" name="nom" value="" size="20" maxlength="60" />
                <span class="erreur">${erreurs['Nom']}</span>
                <br />


                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${erreurs['motdepasse']}</span>
                <br />

                <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                <span class="erreur">${erreurs['confirmation']}</span>
                <br />

				<label for="email">Adresse email <span class="requis">*</span></label>
                <input type="email" id="email" name="email" value="" size="20" maxlength="60" />
                <span class="erreur">${erreurs['email']}</span>
                <br />
                
                <label for="nom">Tél</label>
                <input type="text" id="tel" name="tel" value="" size="20" maxlength="20" />
                <br />

                <input type="submit" value="Inscription" class="sansLabel"/>
            </fieldset>
            </form>
            </div>
        
    </body>
</html>