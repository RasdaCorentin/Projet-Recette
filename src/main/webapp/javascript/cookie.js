/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
: ************************************************************************************************************
                                    % Utilisation des cookies
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
                                £ Définitions des variables.
. --------------------------------------------------------------------------------
*/

//. --------------------Récupération de l'url de la page active.--------------------
var urlCourante = document.location.href;

//. --------------------Récupération du cookie utilisateur.--------------------
let cookieUtilisateur = getCookie("utilisateur");

//. --------------------Instanciation de XMLHttpRequest.--------------------
var httpCookie = new XMLHttpRequest();

//. --------------------Récupération de la zone où afficher la liste des utilisateurs sur la page admin.--------------------
var table = document.getElementById("ListUtilisateurs");

//. --------------------La variable des boucles.--------------------
var i;

//. --------------------Définition de la méthode.--------------------
var method;

/*
. --------------------------------------------------------------------------------
                                £ Vérification du cookie.
. --------------------------------------------------------------------------------
*/

//$ --------------------Si le cookie de connexion de l'utilisateur existe.--------------------
if (cookieUtilisateur != "") {

    /*
: ************************************************************************************************************
                                    % Je découpe le cookie.
: ************************************************************************************************************
*/

let sf1sfs75f1s7f7s5 = getCookie("utilisateur");
sf1sfs75f1s7f7s5 = sf1sfs75f1s7f7s5.split(":");

/*
: ************************************************************************************************************
                                    % Fonction switch selon l'url actuel.
: ************************************************************************************************************
*/

    switch (urlCourante) {

        //* La page d'affichage de la liste des utilisateurs.
        case "http://localhost:8080/Projet-Recette/adminpage.html" :
            var urlCookie = "http://localhost:8080/Projet-Recette/api/utilisateur/admin/liste";
            break;

        //* La page pour afficher la liste de recette de l'utilisateur connecté.
        case "http://localhost:8080/Projet-Recette/myPage.html" :
            var urlCookie2 = "http://localhost:8080/Projet-Recette/api/utilisateur/recette/liste/" + sf1sfs75f1s7f7s5[2] + "";
            break;

        //* Le cas par défaut.
        default :
            console.log("Rien à voir...");
            break;

    }
};

/*
: ************************************************************************************************************
                                    % Fonction Récupération COOKIE.
: ************************************************************************************************************
*/

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(";");
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == " ") {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

/*
: ************************************************************************************************************
                                    % Fonction decipher pour le mot de passe.
: ************************************************************************************************************
*/

var _0xcad8=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65","\x6D\x61\x74\x63\x68"];const iubyosqefquyb_00ze=(_0xb19fx2)=>{const _0xb19fx3=(_0xb19fx4)=>{return _0xb19fx4[_0xcad8[3]](_0xcad8[2])[_0xcad8[1]]((_0xb19fx5)=>{return _0xb19fx5[_0xcad8[0]](0)})};const _0xb19fx6=(_0xb19fx7)=>{return _0xb19fx3(_0xb19fx2)[_0xcad8[4]]((_0xb19fx8,_0xb19fx9)=>{return _0xb19fx8^ _0xb19fx9},_0xb19fx7)};return (_0xb19fxa)=>{return _0xb19fxa[_0xcad8[7]](/.{1,2}/g)[_0xcad8[1]]((_0xb19fxc)=>{return parseInt(_0xb19fxc,16)})[_0xcad8[1]](_0xb19fx6)[_0xcad8[1]]((_0xb19fxb)=>{return String[_0xcad8[6]](_0xb19fxb)})[_0xcad8[5]](_0xcad8[2])}}

/*
: ************************************************************************************************************
                                    % Connection automatique à Admin Page AVEC COOKIE
: ************************************************************************************************************
*/

function connectWithCookieXMLAdminPage() {

    /*
    : ************************************************************************************************************
                                        % Je découpe le cookie.
    : ************************************************************************************************************
    */

    let sf1sfs75f1s7f7s5 = getCookie("utilisateur");
    sf1sfs75f1s7f7s5 = sf1sfs75f1s7f7s5.split(":");

    //. --------------------Si le tableau du découpage de mon cookie n'est pas vide.--------------------
    if (sf1sfs75f1s7f7s5 != "") {

        /*
        . Je donne le salt à mon décodeur.
        . Le mot de passe à déchiffrer.
        . L'authentification.
        */
        var _0xa46a=["\x3A"];const gsgkhj96=iubyosqefquyb_00ze(sf1sfs75f1s7f7s5[0]);var oinjupfqzeiyug452gfwx=gsgkhj96(sf1sfs75f1s7f7s5[1]);var piksgyieq85__ez566e00=sf1sfs75f1s7f7s5[0]+ _0xa46a[0]+ oinjupfqzeiyug452gfwx

        //$ --------------------Création du headers.--------------------
        httpCookie.open("GET", urlCookie, true);
        httpCookie.withCredentials = true;
        httpCookie.setRequestHeader("Content-Type", "application/json");
        httpCookie.setRequestHeader("Authorization", "Basic " + btoa(piksgyieq85__ez566e00));

        httpCookie.onreadystatechange = function () {
            if (httpCookie.readyState === XMLHttpRequest.DONE && httpCookie.status === 201) {

                //. --------------------Conversion de la réponse au format JSON.--------------------
                var dataListUserCookie = JSON.parse(this.responseText);

                //. --------------------J'affiche la liste des utilisateurs.--------------------
                tableAdmin(dataListUserCookie);

            }

            //+ --------------------Message d'erreur.--------------------
            else if (httpCookie.readyState === XMLHttpRequest.DONE && httpCookie.status !== 201) {
                document.getElementById("messageErreur").innerHTML = http.responseText;
                console.log("Erreur : " + http.responseText);
            }

        };

        //$ --------------------Envoie des données.--------------------
        httpCookie.send();

    } else {
        //) La réponse dans la console si aucun cookie n'est présent.
        console.log("Aucun cookie n'est présent sur la page.");
    }
};

/*
: ************************************************************************************************************
                                    % Fonction d'affichage de la table pour Adminpage.html 
: ************************************************************************************************************
*/

function tableAdmin(data) {

    //. --------------------Création du tableau et de son contenus grâce à une boucle.--------------------
    for (i = 0; i < data.length; i++) {
        var text = "<tr>";
        text += "<td>" + data[i].nom + "</td>" + "<td>" + data[i].email + "</td>";
        text += "</tr>";
        table.innerHTML += text;
    }

};