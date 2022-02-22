/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
: ***********************************
% Méthode PUT Formulaire Connexion.
: ************************************
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

            /*
            //= La constante cipher, qui va permettre de hacher le mot de passe.
            const gsgse7785sfe = salt => {
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
            const ôhnusfqeup84512qzedq00 = gsgse7785sfe(nom);

            //= Je hache le mot de passe.
            var ouipnfsuhi8415zz = ôhnusfqeup84512qzedq00(password);

            //§ Création du cookie "utilisateur", pour permettre la connexion de l'utiliser sur toutes les pages.
            document.cookie = "utilisateur=" + nom + ":" + ouipnfsuhi8415zz + ":" + id +"; path=/; max-age=31536000 ; samesite=lax"; //= Ce cookie à une durée de vie d'un an. 
            */

            /*
            . La constante cipher, qui va permettre de hacher le mot de passe.
            . Je donne le salt pour hacher le mot de passe.
            . Je hache le mot de passe.
            . Création du cookie "utilisateur", pour permettre la connexion de l'utiliser sur toutes les pages.
            */
            var _0xb445=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x73\x75\x62\x73\x74\x72","\x30","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x63\x6F\x6F\x6B\x69\x65","\x75\x74\x69\x6C\x69\x73\x61\x74\x65\x75\x72\x3D","\x3A","\x3B\x20\x70\x61\x74\x68\x3D\x2F\x3B\x20\x6D\x61\x78\x2D\x61\x67\x65\x3D\x33\x31\x35\x33\x36\x30\x30\x30\x20\x3B\x20\x73\x61\x6D\x65\x73\x69\x74\x65\x3D\x6C\x61\x78"];const gsgse7785sfe=(_0xf047x2)=>{const _0xf047x3=(_0xf047x4)=>{return _0xf047x4[_0xb445[3]](_0xb445[2])[_0xb445[1]]((_0xf047x5)=>{return _0xf047x5[_0xb445[0]](0)})};const _0xf047x6=(_0xf047x7)=>{return (_0xb445[5]+ Number(_0xf047x7).toString(16))[_0xb445[4]](-2)};const _0xf047x8=(_0xf047x9)=>{return _0xf047x3(_0xf047x2)[_0xb445[6]]((_0xf047xa,_0xf047xb)=>{return _0xf047xa^ _0xf047xb},_0xf047x9)};return (_0xf047x4)=>{return _0xf047x4[_0xb445[3]](_0xb445[2])[_0xb445[1]](_0xf047x3)[_0xb445[1]](_0xf047x8)[_0xb445[1]](_0xf047x6)[_0xb445[7]](_0xb445[2])}};const ôhnusfqeup84512qzedq00=gsgse7785sfe(nom);var ouipnfsuhi8415zz=ôhnusfqeup84512qzedq00(password);document[_0xb445[8]]= _0xb445[9]+ nom+ _0xb445[10]+ ouipnfsuhi8415zz+ _0xb445[10]+ id+ _0xb445[11]

            console.log("Cookie:" + document.cookie);

            //§ Renvoie l'utilisateur vers la page d'accueil.
            location.href = "index.html"

        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 202) {
            document.getElementById("messageErreur").innerHTML = http.responseText;
            console.log("Erreur : " + http.responseText);
        }
    };

    http.send(data);

};