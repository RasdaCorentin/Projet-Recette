/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

/*
: ************************************************************************************************************
                                    % Méthode Post Formulaire Inscription.
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
                                £ Définitions des variables.
. --------------------------------------------------------------------------------
*/

//. --------------------Le formulaire.--------------------
var formInscriptionUtilisateur = document.getElementById("formInscriptionUtilisateur");

//$ --------------------Mise en place des variables pour la connection à la BDD.--------------------

//. --------------------Instanciation de XMLHttpRequest.--------------------
var http = new XMLHttpRequest();

//. --------------------Définition de l'url de l'api.--------------------
var urlInscriptionUtilisateur = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez';

//. --------------------Définition de la méthode.--------------------
var method = 'POST';

/*
. --------------------------------------------------------------------------------
                                £ Méthode d'écoute du formulaire.
. --------------------------------------------------------------------------------
*/

//? Écoute du bouton submit.
formInscriptionUtilisateur.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    //$ --------------------Initialisation des variables à envoyer à l'api.--------------------

    var dataInscriptionUtilisateur = {
        "utilisateur": {
            "nom": document.getElementById("nom").value,
            "password": document.getElementById("password").value,
            "email": document.getElementById("email").value
        }
    };

    //$ --------------------Lancement de la fonction requestTestUpdateUtilisateur.--------------------

    //= Je change dataInscriptionUtilisateur en JSON & lance fonction requestTest.
    //= (je passe le mot de passe ici car j'en ai besoin pour construire le cookie)
    dataInscriptionUtilisateur = JSON.stringify(dataInscriptionUtilisateur);
    requestTest(dataInscriptionUtilisateur);
});

/*
. --------------------------------------------------------------------------------
                                £ Envoi + Réponse de l'API en JSON.
. --------------------------------------------------------------------------------
*/

function requestTest(dataInscriptionUtilisateur) {

    //$ --------------------Création du headers.--------------------

    http.open(method, urlInscriptionUtilisateur);
    http.setRequestHeader('Content-Type', 'application/json');

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 201) {

            //§ Renvoie l'utilisateur vers la page connect afin qu'il puisse activer son compte.
            //! /!\ À supprimer après l'ajout des envoies d'email.
            location.href = "connect.html";

        } 

        //+ --------------------Message d'erreur.--------------------
        else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
            document.getElementById("messageErreur").innerHTML = http.responseText;
            console.log("Erreur : " + http.responseText);
        }

    };

    //$ --------------------Envoie des données.--------------------
    http.send(dataInscriptionUtilisateur);
};