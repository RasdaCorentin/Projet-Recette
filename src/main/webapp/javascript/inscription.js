/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

/*
Méthode Post Formulaire Inscription
*/

var form = document.getElementById("myForm");
var http = new XMLHttpRequest();
var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez';
var method = 'POST';

// … et prenez en charge l'événement submit.
form.addEventListener("submit", function (event) {

    var nom = document.getElementById("nom").value;
    var password = document.getElementById("password").value;
    event.preventDefault();

    var data = {
        "utilisateur": {
            "nom": nom,
            "password": password,
            "email": document.getElementById("email").value
        }
    };

    data = JSON.stringify(data);
    requestTest(data);

    //§ Renvoie l'utilisateur vers la page d'accueil.
    location.href = "index.html"

});

function requestTest(data) {

    http.open(method, url);
    http.setRequestHeader('Content-Type', 'application/json');

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 201) {
            console.log(http.responseText);
        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
            console.log("Error");
        }
    };

    http.send(data);
};