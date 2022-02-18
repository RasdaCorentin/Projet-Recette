/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
Méthode Post Formulaire Connexion
 */

var form = document.getElementById("myForm");
var http = new XMLHttpRequest();
var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez/connect';
var method = 'PUT';

// … et prenez en charge l'événement submit.
form.addEventListener("submit", function (event) {

    event.preventDefault();
    var nom = document.getElementById("nom").value;
    var password = document.getElementById("password").value;

    var data = {
        "utilisateur": {
            "nom": nom,
            "password": password
        }
    };

    data = JSON.stringify(data);
    requestTest(data);

    //§ Création du cookie "utilisateur", pour permettre la connexion de l'utilisateur sur toutes les pages.
    document.cookie = "utilisateur=" + nom + ":" + password + "; path=/; max-age=31536000 ; samesite=lax"; //= Ce cookie à une durée de vie d'un an.

    //§ Renvoie l'utilisateur vers la page d'accueil.
    location.href = "index.html"

});

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

};