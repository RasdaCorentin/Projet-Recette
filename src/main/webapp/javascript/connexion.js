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
    var nom = document.getElementById("nom").value;
    var password = document.getElementById("password").value;

    var data = {
        "utilisateur": {
            "nom": nom,
            "password": password
        }
    };
    //Je change data en JSON & lance fonction requestTest
    //(je passe le mot de passe ici car j'en ai besoin pour construire le cookie)
    data = JSON.stringify(data);
    requestTest(data, password);

    //§ Renvoie l'utilisateur vers la page d'accueil.
    location.href = "index.html"
});
//Envois + Réponse de l'API en JSON
function requestTest(data, password) {
    http.open(method, url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 202) {
            var res = JSON.parse(http.responseText);
            //Ici je récupére la réponse en JSON que je met dans var nom & id 
            res = JSON.parse(http.responseText);
            // et je crée le cookie qui ressemble à "utilisateur=nom:paswword:id" réutilisé uniquement dans cookie.js
            var nom = res.nom;
            var id = res.id;
            //§ Création du cookie "utilisateur", pour permettre la connexion de l'utilisateur sur toutes les pages.
    document.cookie = "utilisateur=" + nom + ":" + password + ":" + id +"; path=/; max-age=31536000 ; samesite=lax"; //= Ce cookie à une durée de vie d'un an.
        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 202) {
            console.log("Error");
        }
    };
    http.send(data);
};
