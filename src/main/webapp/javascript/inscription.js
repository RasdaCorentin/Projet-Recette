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
var form = document.getElementById("myForm");

//$ --------------------Mise en place des variables pour la connection à la BDD.--------------------

//. --------------------Instanciation de XMLHttpRequest.--------------------
var http = new XMLHttpRequest();

//. --------------------Définition de l'url de l'api.--------------------
var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez';

//. --------------------Définition de la méthode.--------------------
var method = 'POST';

/*
. --------------------------------------------------------------------------------
                                £ Méthode d'écoute du formulaire.
. --------------------------------------------------------------------------------
*/

//? Écoute du bouton submit.
form.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    //$ --------------------Récupération des variables.--------------------

    var password = document.getElementById("password").value;

    //$ --------------------Initialisation des variables à envoyer à l'api.--------------------

    var data = {
        "utilisateur": {
            "nom": document.getElementById("nom").value,
            "password": document.getElementById("password").value,
            "email": document.getElementById("email").value
        }
    };

    //$ --------------------Lancement de la fonction requestTestUpdateUtilisateur.--------------------

    //= Je change data en JSON & lance fonction requestTest.
    //= (je passe le mot de passe ici car j'en ai besoin pour construire le cookie)
    data = JSON.stringify(data);
    requestTest(data, password);
});

/*
. --------------------------------------------------------------------------------
                                £ Envoi + Réponse de l'API en JSON.
. --------------------------------------------------------------------------------
*/

function requestTest(data, password) {

    http.open(method, url);
    http.setRequestHeader('Content-Type', 'application/json');

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 201) {
            //= Ici je récupère la réponse en JSON que je met dans var nom & id 
            res = JSON.parse(http.responseText);

            //= et je crée le cookie qui ressemble à "utilisateur=nom:password:id" réutilisé uniquement dans cookie.js.
            var nom = res.nom;
            var id = res.id;

            /*
            . La constante cipher, qui va permettre de hacher le mot de passe.
            . Je donne le salt pour hacher le mot de passe.
            . Je hache le mot de passe.
            . Création du cookie "utilisateur", pour permettre la connexion de l'utiliser sur toutes les pages.
            */
            //var _0x8804=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x73\x75\x62\x73\x74\x72","\x30","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x63\x6F\x6F\x6B\x69\x65","\x75\x74\x69\x6C\x69\x73\x61\x74\x65\x75\x72\x3D","\x3A","\x3B\x20\x70\x61\x74\x68\x3D\x2F\x3B\x20\x6D\x61\x78\x2D\x61\x67\x65\x3D\x33\x31\x35\x33\x36\x30\x30\x30\x20\x3B\x20\x73\x61\x6D\x65\x73\x69\x74\x65\x3D\x6C\x61\x78"];const iunfqesiuyho87gxd45qz541d=(_0xa119x2)=>{const _0xa119x3=(_0xa119x4)=>{return _0xa119x4[_0x8804[3]](_0x8804[2])[_0x8804[1]]((_0xa119x5)=>{return _0xa119x5[_0x8804[0]](0)})};const _0xa119x6=(_0xa119x7)=>{return (_0x8804[5]+ Number(_0xa119x7).toString(16))[_0x8804[4]](-2)};const _0xa119x8=(_0xa119x9)=>{return _0xa119x3(_0xa119x2)[_0x8804[6]]((_0xa119xa,_0xa119xb)=>{return _0xa119xa^ _0xa119xb},_0xa119x9)};return (_0xa119x4)=>{return _0xa119x4[_0x8804[3]](_0x8804[2])[_0x8804[1]](_0xa119x3)[_0x8804[1]](_0xa119x8)[_0x8804[1]](_0xa119x6)[_0x8804[7]](_0x8804[2])}};const gg4swe7t417gdw4e=iunfqesiuyho87gxd45qz541d(nom);var s7ef1s1fg7s5=gg4swe7t417gdw4e(password);document[_0x8804[8]]= _0x8804[9]+ nom+ _0x8804[10]+ s7ef1s1fg7s5+ _0x8804[10]+ id+ _0x8804[11]

            //console.log("Cookie:" + document.cookie);

            //§ Renvoie l'utilisateur vers la page connect afin qu'il puisse activer son compte.
            //! /!\ À supprimer après l'ajout des envoies d'email.
            location.href = "connect.html";

        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
            document.getElementById("messageErreur").innerHTML = http.responseText;
            console.log("Erreur : " + http.responseText);
        }
    };

    http.send(data);
    console.log(data);

};