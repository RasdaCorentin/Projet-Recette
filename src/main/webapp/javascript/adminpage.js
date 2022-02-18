/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/******************************
 
 Methode Get List Utilisateur
 
 *****************************/

//Methode d'écoute sur Formulaire
var form = document.getElementById("myForm");
//Ecoute bouton submit
form.addEventListener("submit", function (event) {
    //J'enléve les paramétres par défaut du formulaire
    event.preventDefault();
    //Mise en place des variable pour la connection à la BDD
    var http = new XMLHttpRequest();
    //Url api
    var url = "http://localhost:8080/Projet-Recette/api/utilisateur/admin/liste";
    //On submit je prend les infos de l'utilisateur
    var username = document.getElementById("nom").value;
    var password = document.getElementById("password").value;
    var authBasic = username + ":" + password;

    //Authentification + Réponse de l'API en JSON
    http.open("GET", url, true);
    http.withCredentials = true;
    http.setRequestHeader('Content-Type', 'application/json');
    http.setRequestHeader("Authorization", "Basic " + btoa(authBasic));
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 201) {
            var dataListUser = JSON.parse(this.responseText);
            myFunction(dataListUser);
        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
            console.log("Error");
        }
    };
    http.send();

    // Fonction d'affichage JSON => HTML
    function myFunction(data) {
        var table = document.getElementById("ListUtilisateurs");
        var i;
        /* i est égal à 0; tant que i plus petit que myArr, relancer la boucle */
        for (i = 0; i < data.length; i++) {
            var text = "<tr>"
            text += "<td>" + data[i].nom + "</td>"
                    + "<td>" + data[i].email + "</td>"
            text += "</tr>";
            table.innerHTML += text
        }

    }
});
 