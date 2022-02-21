/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
***********************************
% Méthode PUT Formulaire Connexion
************************************
*/

//= Méthode d'écoute sur Formulaire.
var form = document.getElementById("myForm");

//= Mise en place des variable pour la connection à la BDD.
var http = new XMLHttpRequest();

//= Url api.
var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez/connect';
var method = 'PUT';

//? Écoute du bouton submit.
form.addEventListener("submit", function (event) {
    event.preventDefault();

    //= J'initialise les variable à envoyer à l'API.
    var nom = document.getElementById("nom").value;
    var password = document.getElementById("password").value;

    var data = {
        "utilisateur": {
            "nom": nom,
            "password": password
        }
    };

    //= Je change data en JSON & lance fonction requestTest.
    //= (je passe le mot de passe ici car j'en ai besoin pour construire le cookie)
    data = JSON.stringify(data);
    requestTest(data, password);

    //§ Renvoie l'utilisateur vers la page d'accueil.
    location.href = "index.html"
});

//? Envois + Réponse de l'API en JSON.
function requestTest(data, password) {
    http.open(method, url);
    http.setRequestHeader('Content-Type', 'application/json');

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 202) {
            var res = JSON.parse(http.responseText);

            //= Ici je récupère la réponse en JSON que je met dans var nom & id.
            res = JSON.parse(http.responseText);

            //§ et je crée le cookie qui ressemble à "utilisateur=nom:password:id" réutilisé uniquement dans cookie.js
            var nom = res.nom;
            var id = res.id;

            //= La constante cipher, qui va permettre de hacher le mot de passe.
            const cipher = salt => {
                const textToChars = text => text.split('').map(c => c.charCodeAt(0));
                const byteHex = n => ("0" + Number(n).toString(16)).substr(-2);
                const applySaltToChar = code => textToChars(salt).reduce((a,b) => a ^ b, code);
            
                return text => text.split('')
                    .map(textToChars)
                    .map(applySaltToChar)
                    .map(byteHex)
                    .join('');
            }

            //= Je donne le salt pour hacher le mot de passe.
            const myCipher = cipher(nom);

            //= Je hache le mot de passe.
            var passwordCipher = myCipher(password);

            //§ Création du cookie "utilisateur", pour permettre la connexion de l'utilisateur sur toutes les pages.
            document.cookie = "utilisateur=" + nom + ":" + passwordCipher + ":" + id +"; path=/; max-age=31536000 ; samesite=lax"; //= Ce cookie à une durée de vie d'un an.

            alert("Cookie:" + document.cookie);
        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 202) {
            console.log("Error");
        }
    };

    http.send(data);

};