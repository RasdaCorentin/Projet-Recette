/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

/********************************
 
 Methode Post Formulaire Inscription
 
 *******************************/

<<<<<<< Updated upstream
    var form = document.getElementById("myForm");
    var http = new XMLHttpRequest();
    var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez';
    var method = 'POST';
=======
//Methode d'écoute sur Formulaire
var form = document.getElementById("myForm");
//Mise en place des variable pour la connection à la BDD
var http = new XMLHttpRequest();
//Url api
var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez';
var method = 'POST';
>>>>>>> Stashed changes




<<<<<<< Updated upstream
    // … et prenez en charge l'événement submit.
    form.addEventListener("submit", function (event) {
        event.preventDefault();
            var data = {
=======
//Ecoute bouton submit
form.addEventListener("submit", function (event) {
    event.preventDefault();
    //J'initialise les variable à envoyer à l'API
    var data = {
>>>>>>> Stashed changes
        "utilisateur": {
            "nom": document.getElementById("nom").value,
            "password": document.getElementById("password").value,
            "email": document.getElementById("email").value
        }
    };
<<<<<<< Updated upstream
    data = JSON.stringify(data);
        requestTest(data);
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
        alert(data);
=======
    /*******************************************************************
     Je prend une seconde fois le mot de passe pour le mettre dans le cookie
     **********************************************************************/
    var password = document.getElementById("password").value;
    //Je change data en JSON & lance fonction requestTest 
    //(je passe le mot de passe ici car j'en ai besoin pour construire le cookie)
    data = JSON.stringify(data);
    requestTest(data, password);
});
//Envois + Réponse de l'API en JSON
function requestTest(data, password) {
    http.open(method, url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 201) {
            //Ici je récupére la réponse en JSON que je met dans var nom & id 
            res = JSON.parse(http.responseText);
            // et je crée le cookie qui ressemble à "utilisateur=nom:paswword:id" réutilisé uniquement dans cookie.js
            var nom = res.nom;
            var id = res.id;
            document.cookie = "utilisateur=" + nom + ":" + password + ":" + id;
            "path=/"; "max-age=600000000"; "samesite=lax"; //$ max-age=31536000 pour une année entière.   
            alert("Cookie:" + document.cookie);
        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
            console.log("Error");
        }
>>>>>>> Stashed changes
    };
