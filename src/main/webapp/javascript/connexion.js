/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
: ************************************************************************************************************
                                    % Méthode PUT Formulaire Connexion.
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
                                £ Définitions des variables.
. --------------------------------------------------------------------------------
*/

//. --------------------Le formulaire.--------------------
var formConnexionUtilisateur = document.getElementById("formConnexionUtilisateur");

//. --------------------Les variables globales.--------------------
var nom = {};
var fqzf71q7zf1q75zf1q75 = {};

//$ --------------------Mise en place des variables pour la connection à la BDD.--------------------

//. --------------------Instanciation de XMLHttpRequest.--------------------
var http = new XMLHttpRequest();

//. --------------------Définition de l'url de l'api.--------------------
var urlConnexion = 'http://localhost:8080/Projet-Recette/api/utilisateur/connexion';

//. --------------------Définition de la méthode.--------------------
var method = 'PUT';

/*
. --------------------------------------------------------------------------------
                                £ Méthode d'écoute du formulaire.
. --------------------------------------------------------------------------------
*/

//? Écoute du bouton submit.
formConnexionUtilisateur.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    //$ --------------------Récupération des variables.--------------------

    nom = document.getElementById("nom").value;
    fqzf71q7zf1q75zf1q75 = document.getElementById("password").value;

    //$ --------------------Initialisation des variables à envoyer à l'api.--------------------

    var dataConnexion = {
        "utilisateur": {
            "nom": nom,
            "password": fqzf71q7zf1q75zf1q75
        }
    };

    //$ --------------------Lancement de la fonction requestTestUpdateUtilisateur.--------------------

    //= Je change dataConnexion en JSON & lance fonction requestTest.
    //= (je passe le mot de passe ici car j'en ai besoin pour construire le cookie)
    dataConnexion = JSON.stringify(dataConnexion);
    requestTest(dataConnexion, fqzf71q7zf1q75zf1q75);

    //+ --------------------Message de lancement.--------------------
    document.getElementById("messageSimple").innerHTML = "Connexion en cours..."
});

/*
. --------------------------------------------------------------------------------
                                £ Envoi + Réponse de l'API en JSON.
. --------------------------------------------------------------------------------
*/

function requestTest(dataConnexion, fqzf71q7zf1q75zf1q75) {

    //$ --------------------L'authentification.--------------------

    var authBasic = nom + ":" + fqzf71q7zf1q75zf1q75;

    //$ --------------------Création du headers.--------------------
    http.open(method, urlConnexion);
    http.setRequestHeader('Content-Type', 'application/json');
    http.setRequestHeader("Authorization", "Basic " + btoa(authBasic));

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 202) {

            //$ --------------------Récupération de la réponse.--------------------

            //. --------------------Conversion de la réponse au format JSON.--------------------
            var res = JSON.parse(http.responseText);

            //. --------------------Récupération du nom et de l'id.--------------------
            var nom = res.nom;
            var id = res.id;

            /*
            . La constante cipher, qui va permettre de hacher le mot de passe.
            . Je donne le salt pour hacher le mot de passe.
            . Je hache le mot de passe.
            . Création du cookie "utilisateur", pour permettre la connexion de l'utiliser sur toutes les pages.
            */
            var _0xb445=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x73\x75\x62\x73\x74\x72","\x30","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x63\x6F\x6F\x6B\x69\x65","\x75\x74\x69\x6C\x69\x73\x61\x74\x65\x75\x72\x3D","\x3A","\x3B\x20\x70\x61\x74\x68\x3D\x2F\x3B\x20\x6D\x61\x78\x2D\x61\x67\x65\x3D\x33\x31\x35\x33\x36\x30\x30\x30\x20\x3B\x20\x73\x61\x6D\x65\x73\x69\x74\x65\x3D\x6C\x61\x78"];const gsgse7785sfe=(_0xf047x2)=>{const _0xf047x3=(_0xf047x4)=>{return _0xf047x4[_0xb445[3]](_0xb445[2])[_0xb445[1]]((_0xf047x5)=>{return _0xf047x5[_0xb445[0]](0)})};const _0xf047x6=(_0xf047x7)=>{return (_0xb445[5]+ Number(_0xf047x7).toString(16))[_0xb445[4]](-2)};const _0xf047x8=(_0xf047x9)=>{return _0xf047x3(_0xf047x2)[_0xb445[6]]((_0xf047xa,_0xf047xb)=>{return _0xf047xa^ _0xf047xb},_0xf047x9)};return (_0xf047x4)=>{return _0xf047x4[_0xb445[3]](_0xb445[2])[_0xb445[1]](_0xf047x3)[_0xb445[1]](_0xf047x8)[_0xb445[1]](_0xf047x6)[_0xb445[7]](_0xb445[2])}};const ôhnusfqeup84512qzedq00=gsgse7785sfe(nom);var ouipnfsuhi8415zz=ôhnusfqeup84512qzedq00(fqzf71q7zf1q75zf1q75);document[_0xb445[8]]= _0xb445[9]+ nom+ _0xb445[10]+ ouipnfsuhi8415zz+ _0xb445[10]+ id+ _0xb445[11]

            //+ --------------------Message de réussite.--------------------
            document.getElementById("messageReussite").innerHTML = "La connexion est une réussite."

            //. --------------------Renvoie l'utilisateur vers la page d'accueil.--------------------
            location.href = "index.html"

        } 

        //+ --------------------Message d'erreur.--------------------
        else if (http.readyState === XMLHttpRequest.DONE && http.status !== 202) {
            document.getElementById("messageErreur").innerHTML = http.responseText;
            console.log("Erreur : " + http.responseText);
        }
    };

    //$ --------------------Envoie des données.--------------------
    http.send(dataConnexion);
};