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
	Choisissez vos visites parmis les  <% String NbVisites = (String) request.getAttribute("NbVisites");out.println( NbVisites );%> visites disponibles :
	<br>
	<br>
	<b>TYPE :</b>
	<div class="custom-select" style="width:200px;">
	<select name="Type">	
  		<option value="Familliale">Familliale</option>
 		<option value="Culturel">Culturel</option>
  		<option value="Gastronomique">Gastronomique</option>
  		<option value="MAIS NON ?!">MAIS NON ?!</option>
  		<option value="chocolatine">Chocolatine</option>
	</select>
	</div>
	
	<br>
	
	<b>VILLE :</b>
	<div class="custom-select" style="width:200px;">	
	<select name="Ville">
  		<option value="Angers">Angers</option>
 		<option value="Brive-la-gaillarde">Brive-la-gaillarde</option>
  		<option value="Cosnac">Cosnac</option>
  		<option value="MAIS NON ?!">MAIS NON ?!</option>
  		<option value="chocolatine">Chocolatine</option>
	</select>
	</div>
	<br>
	
	<b>PRIX MAXIMUM :</b>
	<div class="custom-select" style="width:200px;">
	<select name="Prix">
  		<option value="25">25 euros</option>
 		<option value="50">50 euros</option>
  		<option value="75">75 euros</option>
  		<option value="100">100 euros</option>
  		<option value="Chocolatine">Chocolatine</option>
	</select>
	</div>
	
	<br>
	<b>DATE :</b>
	<br> <input type="text" value="2018-12-31" id="Date" size="30" style="width:200px;">
	<br>
	<br>
	<input type="submit" value="Valider" id="Valider">
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
                
               		 <% int nombreVistite = Integer.parseInt((String)request.getAttribute("NbVisites"));
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

			</tbody>
	</table>
	<br>
	<input type="submit" value="Réserver" id="Réserver">
	<br>
	<br>
		<a href="index.jsp">Déconnexion</a>
<script>
var x, i, j, selElmnt, a, b, c;
/*look for any elements with the class "custom-select":*/
x = document.getElementsByClassName("custom-select");
for (i = 0; i < x.length; i++) {
  selElmnt = x[i].getElementsByTagName("select")[0];
  /*for each element, create a new DIV that will act as the selected item:*/
  a = document.createElement("DIV");
  a.setAttribute("class", "select-selected");
  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
  x[i].appendChild(a);
  /*for each element, create a new DIV that will contain the option list:*/
  b = document.createElement("DIV");
  b.setAttribute("class", "select-items select-hide");
  for (j = 1; j < selElmnt.length; j++) {
    /*for each option in the original select element,
    create a new DIV that will act as an option item:*/
    c = document.createElement("DIV");
    c.innerHTML = selElmnt.options[j].innerHTML;
    c.addEventListener("click", function(e) {
        /*when an item is clicked, update the original select box,
        and the selected item:*/
        var i, s, h;
        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
        h = this.parentNode.previousSibling;
        for (i = 0; i < s.length; i++) {
          if (s.options[i].innerHTML == this.innerHTML) {
            s.selectedIndex = i;
            h.innerHTML = this.innerHTML;
            break;
          }
        }
        h.click();
    });
    b.appendChild(c);
  }
  x[i].appendChild(b);
  a.addEventListener("click", function(e) {
      /*when the select box is clicked, close any other select boxes,
      and open/close the current select box:*/
      e.stopPropagation();
      closeAllSelect(this);
      this.nextSibling.classList.toggle("select-hide");
      this.classList.toggle("select-arrow-active");
  });
}
function closeAllSelect(elmnt) {
  /*a function that will close all select boxes in the document,
  except the current select box:*/
  var x, y, i, arrNo = [];
  x = document.getElementsByClassName("select-items");
  y = document.getElementsByClassName("select-selected");
  for (i = 0; i < y.length; i++) {
    if (elmnt == y[i]) {
      arrNo.push(i)
    } else {
      y[i].classList.remove("select-arrow-active");
    }
  }
  for (i = 0; i < x.length; i++) {
    if (arrNo.indexOf(i)) {
      x[i].classList.add("select-hide");
    }
  }
}
/*if the user clicks anywhere outside the select box,
then close all select boxes:*/
document.addEventListener("click", closeAllSelect);

</script>
</body>
</html>