/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
: ************************************************************************************************************
                                    % Méthode Get List Utilisateur
: ************************************************************************************************************
*/

//? Méthode d'écoute sur Formulaire.
var form = document.getElementById("myForm");
//= Écoute du bouton submit.
form.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();
    //= Mise en place des variable pour la connection à la BDD.
    var http = new XMLHttpRequest();
    //= Url api.
    var url = "http://localhost:8080/Projet-Recette/api/utilisateur/admin/liste";
    //= On envoie le formulaire, je prend les infos de l'utilisateur.
    var username = document.getElementById("nom").value;
    var password = document.getElementById("password").value;
    var authBasic = username + ":" + password;

    //= Authentification + Réponse de l'API en JSON.
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

    //= Fonction d'affichage JSON => HTML.
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

//. --------------------Les variables pour les hide and show.--------------------
var elementsACacher = document.getElementById("elementsACacher");
var cacherConnexion = document.getElementById("cacherConnexion");
var cacherInscription = document.getElementById("cacherInscription");
var elementAMontrer = document.getElementById("elementAMontrer");

//. --------------------Les hide and show.--------------------
if (cookieUtilisateur != "") {
    elementsACacher.classList.add('hide'); //: Permet de cacher le lien vers la page de connexion si l'utilisateur est déjà connecté.
    cacherConnexion.classList.add('hide'); //: Permet de cacher le lien vers la page de connexion si l'utilisateur est déjà connecté.
    cacherInscription.classList.add('hide'); //: Permet de cacher le lien vers la page d'inscription si l'utilisateur est déjà connecté.
    elementAMontrer.classList.remove('hide'); //: Permet de montré le lien vers la page de déconnexion si l'utilisateur est connecté.
}