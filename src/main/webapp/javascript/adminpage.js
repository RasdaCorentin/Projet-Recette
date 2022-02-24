/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
: ************************************************************************************************************
                                    % Méthode Get List Utilisateur
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
                                £ Définitions des variables.
. --------------------------------------------------------------------------------
*/

//. --------------------Le formulaire.--------------------
var formConnexionAdminPage = document.getElementById("formConnexionAdminPage");

//. --------------------L'emplacement de la liste.--------------------
var table = document.getElementById("ListUtilisateurs");

//. --------------------La boucle.--------------------
var i;

//. --------------------Les variables pour les hide and show.--------------------
var elementsACacher = document.getElementById("elementsACacher");
var cacherConnexion = document.getElementById("cacherConnexion");
var cacherInscription = document.getElementById("cacherInscription");
var elementAMontrer = document.getElementById("elementAMontrer");
var elementsIdentificationACacher = document.getElementById("Identification");

//$ --------------------Mise en place des variables pour la connection à la BDD.--------------------

//. --------------------Instanciation de XMLHttpRequest.--------------------
var http = new XMLHttpRequest();

//. --------------------Définition de l'url de l'api.--------------------
var urlListeUtilisateur = 'http://localhost:8080/Projet-Recette/api/utilisateur/admin/liste';

//. --------------------Définition de la méthode.--------------------
var method = 'GET';

/*
. --------------------------------------------------------------------------------
                                £ Méthode d'écoute du formulaire.
. --------------------------------------------------------------------------------
*/

//? Écoute du bouton submit.
formConnexionAdminPage.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    //$ --------------------Récupération des variables.--------------------

    var username = document.getElementById("nom").value;
    var password = document.getElementById("password").value;

    //$ --------------------Création de l'authentification.--------------------
    var authBasic = username + ":" + password;

    //$ --------------------Création du headers.--------------------

    http.open(method, urlListeUtilisateur, true);
    http.withCredentials = true;
    http.setRequestHeader('Content-Type', 'application/json');
    http.setRequestHeader("Authorization", "Basic " + btoa(authBasic));

    http.onreadystatechange = function () {

        if (http.readyState === XMLHttpRequest.DONE && http.status === 201) {

            //$ --------------------Récupération de la réponse.--------------------

            //. --------------------Conversion de la réponse au format JSON.--------------------
            var dataListUser = JSON.parse(this.responseText);

            //. --------------------Récupération de la liste des utilisateurs.--------------------
            listerUtilisateurs(dataListUser);
        } 

        //+ --------------------Message d'erreur.--------------------
        else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
            document.getElementById("messageErreur").innerHTML = http.responseText;
            console.log("Erreur : " + http.responseText);
        }

    };

    //$ --------------------Envoie des données.--------------------
    http.send();

    //$ --------------------Fonction d'affichage JSON => HTML.--------------------
    function listerUtilisateurs(dataUtilisateurs) {

        //. --------------------Création du tableau et de son contenus grâce à une boucle.--------------------
        for (i = 0; i < dataUtilisateurs.length; i++) {
            var text = "<tr>"
            text += "<td>" + dataUtilisateurs[i].nom + "</td>"
                    + "<td>" + dataUtilisateurs[i].email + "</td>"
            text += "</tr>";
            table.innerHTML += text
        }

        //. --------------------Je cache le formulaire de connexion une fois que mon tableau est entièrement généré.--------------------
        elementsACacher.classList.add('hide');
    }
});

/*
: ************************************************************************************************************
                                    % Les hide and show.
: ************************************************************************************************************
*/

//. --------------------Les hide and show.--------------------
if (cookieUtilisateur != "") {
    elementsACacher.classList.add('hide'); //: Permet de cacher le formulaire de connexion si l'utilisateur possède un cookie.
    cacherConnexion.classList.add('hide'); //: Permet de cacher le lien vers la page de connexion si l'utilisateur possède un cookie.
    cacherInscription.classList.add('hide'); //: Permet de cacher le lien vers la page d'inscription si l'utilisateur possède un cookie.
    elementAMontrer.classList.remove('hide'); //: Permet de montré le lien vers la page de déconnexion si l'utilisateur possède un cookie.
    elementsIdentificationACacher.classList.add('hide'); //: Permet de cacher le formulaire si l'utilisateur possède un cookie.
}