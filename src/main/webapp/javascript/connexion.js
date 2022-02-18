/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/**********************************
 
 Methode PUT Formulaire Connexion
 
 ***********************************/

//Methode d'écoute sur Formulaire
var form = document.getElementById("myForm");
//Mise en place des variable pour la connection à la BDD
var http = new XMLHttpRequest();
//Url api
var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez/connect';
var method = 'PUT';




//Ecoute bouton submit
form.addEventListener("submit", function (event) {
    event.preventDefault();
    //J'initialise les variable à envoyer à l'API
    var data = {
        "utilisateur": {
            "nom": document.getElementById("nom").value,
            "password": document.getElementById("password").value
        }
    };
    //Je change data en JSON & lance fonction requestTest
    data = JSON.stringify(data);
    requestTest(data);
});
//Envois + Réponse de l'API en JSON
function requestTest(data) {
    http.open(method, url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 202) {
            var res = JSON.parse(http.responseText);
            console.log(res);
        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 202) {
            console.log("Error");
        }
    };
    http.send(data);
}
;
