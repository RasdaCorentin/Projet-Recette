/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
: ************************************************************************************************************
                                    % Méthode Get List Utilisateur
: ************************************************************************************************************
*/

function includeHTML() {

  /*
  . --------------------------------------------------------------------------------
                              £ Définitions des variables.
  . --------------------------------------------------------------------------------
  */

  var z, i, elmnt, file, xhttp;

  //. --------------------Récupération de tout les éléments HTML.--------------------
  z = document.getElementsByTagName("*");

  for (i = 0; i < z.length; i++) {
    elmnt = z[i];

    //. --------------------Recherche des éléments avec l'attribut "w3-include".--------------------
    file = elmnt.getAttribute("w3-include");

    //. --------------------Si file est définit.--------------------
    if (file) {

      //. --------------------Créée une requête HTTP en utilisant comme attribut le nom du fichier.--------------------
      xhttp = new XMLHttpRequest();

      xhttp.onreadystatechange = function() {

        if (this.readyState == 4) {

          if (this.status == 200) {elmnt.innerHTML = this.responseText;}
          if (this.status == 404) {elmnt.innerHTML = "Page not found.";}

          //. --------------------Enlève l'attribut de l'élément traité.--------------------
          elmnt.removeAttribute("w3-include");

          //. --------------------Inclus le fichier html.--------------------
          includeHTML();
        }

      }

      xhttp.open("GET", file, true);
      xhttp.send();

      //. --------------------Quitte la fonction.--------------------
      return;
    }

  }

}