/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
: ************************************************************************************************************
                                    % Récupération du cookie
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

let cookieUser = getCookie("utilisateur");

let qges7f4s71ef5 = getCookie("utilisateur");
qges7f4s71ef5 = qges7f4s71ef5.split(":");

/*
: ************************************************************************************************************
                                    % Définition des variables
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
                                £ Définitions des variables globales.
. --------------------------------------------------------------------------------
*/

//. --------------------L'authentification.--------------------
var authBasicUser = {};

//. --------------------Le nom de l'utilisateur.--------------------
var username = {};

//. --------------------Le mot de passe de l'utilisateur.--------------------
var password = {};

/*
. --------------------------------------------------------------------------------
                                £ Définitions des variables des formulaires.
. --------------------------------------------------------------------------------
*/

//. --------------------Le formulaire d'identification manuelle.--------------------
let formIdUser = document.getElementById("Identification");

/*
. --------------------------------------------------------------------------------
                                £ Définitions des zones pour les messages.
. --------------------------------------------------------------------------------
*/

//. --------------------La zone pour un message d'erreur.--------------------
var messageErreur = document.getElementById("messageErreur");

//. --------------------La zone pour un message de réussite.--------------------
var messageReussite = document.getElementById("messageReussite");

//. --------------------La zone pour un message simple.--------------------
var messageSimple = document.getElementById("messageSimple");

/*
. --------------------------------------------------------------------------------
                                £ Les variables pour les hide and show.
. --------------------------------------------------------------------------------
*/

var elementsACacher = document.getElementById("elementsACacher");
var cacherConnexion = document.getElementById("cacherConnexion");
var cacherInscription = document.getElementById("cacherInscription");
var elementAMontrer = document.getElementById("elementAMontrer");
var elementsIdentificationACacher = document.getElementById("Identification");

//$ --------------------Les boutons de la page.--------------------
var boutonCreationRecette = document.getElementById("boutonModalCreationRecette");
var boutonUpdateUtilisateur = document.getElementById("boutonModalUpdate");
var boutonSupprimerUtilisateur = document.getElementById("boutonModalVanish");

/*
: ************************************************************************************************************
                                    % Méthode Fetch myPage List Recette du User
: ************************************************************************************************************
*/

//. --------------------Si le cookie est présent.--------------------
if (cookieUser != '') {

    /*
        . Je donne le salt à mon décodeur.
        . Le mot de passe à déchiffrer.
        . Je stocke le mot de passe déchiffrer dans une variable.
    */
    var _0xaa45=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65","\x6D\x61\x74\x63\x68","\x3A"];const q7f17q5fq7=(_0x8c00x2)=>{const _0x8c00x3=(_0x8c00x4)=>{return _0x8c00x4[_0xaa45[3]](_0xaa45[2])[_0xaa45[1]]((_0x8c00x5)=>{return _0x8c00x5[_0xaa45[0]](0)})};const _0x8c00x6=(_0x8c00x7)=>{return _0x8c00x3(_0x8c00x2)[_0xaa45[4]]((_0x8c00x8,_0x8c00x9)=>{return _0x8c00x8^ _0x8c00x9},_0x8c00x7)};return (_0x8c00xa)=>{return _0x8c00xa[_0xaa45[7]](/.{1,2}/g)[_0xaa45[1]]((_0x8c00xc)=>{return parseInt(_0x8c00xc,16)})[_0xaa45[1]](_0x8c00x6)[_0xaa45[1]]((_0x8c00xb)=>{return String[_0xaa45[6]](_0x8c00xb)})[_0xaa45[5]](_0xaa45[2])}};const qd1q75d1q7d=q7f17q5fq7(qges7f4s71ef5[0]);var dq1dq7d1q75=qd1q75d1q7d(qges7f4s71ef5[1]);var authBasicUser=qges7f4s71ef5[0]+ _0xaa45[8]+ dq1dq7d1q75;fetchRecetteUser(qges7f4s71ef5[2]);fetchIngredientUser(qges7f4s71ef5[2])
}

/*
. --------------------------------------------------------------------------------
                                £ La connexion manuelle.
. --------------------------------------------------------------------------------
*/

//? Écoute bouton submit du formulaire de connection manuelle.
formIdUser.addEventListener("submit", async function (event) {

    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    //$ --------------------Récupération des variables.--------------------
    username = document.getElementById("nom").value;
    password = document.getElementById("password").value;

    //$ --------------------Création de l'authentification.--------------------
    authBasicUser = username + ":" + password;

    //$ --------------------Initialisation des variables à envoyer à l'api.--------------------

    let _data = {

        "utilisateur": {
            "nom": username
        }

    }

    //$ --------------------Création du headers.--------------------
    var headers = new Headers();
    headers.set("Content-type", "application/json");
    headers.set('Authorization', 'Basic ' + btoa(authBasicUser));

    responseIdUser = await fetch('http://localhost:8080/Projet-Recette/api/utilisateur/read', {
        method: "POST",
        body: JSON.stringify(_data),
        headers: headers
    })

    if (responseIdUser.status === 202) {
        let data = await responseIdUser.json();
        idUser = data.id;
        elementsACacher.classList.add('hide');
        boutonCreationRecette.classList.remove('hide');
        boutonUpdateUtilisateur.classList.remove('hide');
        boutonSupprimerUtilisateur.classList.remove('hide');
    }
    fetchRecetteUser(idUser);
    fetchIngredientUser(idUser);
});

/*
: ************************************************************************************************************
                                    % Méthode Fetch myPage List Ingrédient du User
: ************************************************************************************************************
*/

async function fetchIngredientUser(id) {
    //Url api
    var urlListeIngredient = "http://localhost:8080/Projet-Recette/api/utilisateur/ingredient/liste/" + id + "";
    //Mise en place des variable pour la connection à la BDD
    let headers = new Headers();
    //Authentification + Réponse de l'API en JSON
    headers.set('Authorization', 'Basic ' + btoa(authBasicUser));


    let response = await fetch(urlListeIngredient, {method: 'GET',
        headers: headers
    });

    if (response.status === 201) {
        let dataIngredient = await response.json();
        console.log(dataIngredient);
        var tableIng = document.getElementById("ListIng")
        // Fonction d'affichage JSON => HTML
        for (let iterIng in dataIngredient) {
            var textIng = "<tr>"
            textIng += "<td>" + dataIngredient[iterIng].libelle + "</td>"
                    + "<td data-bs-toggle='modal' data-bs-target='#exampleIng" + iterIng + "'>" + dataIngredient[iterIng].quantite + "</button></td>" +
                    '<div class="modal fade" id="exampleIng' + iterIng + '" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
                    '<div class="modal-dialog">' +
                    '<div class="modal-content">' +
                    '<div class="modal-header">' +
                    '<h5 class="modal-title" id="exampleModalLabel">' + dataIngredient[iterIng].libelle + '</h5>' +
                    '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>' +
                    '</div>' +
                    '<div class="modal-footer">' +
                    '<button type="button" onclick="deleteIng(' + dataIngredient[iterIng].id + ')" class="btn btn-danger">Supprimer Ingrédient</button>' +
                    '<button type="button" onclick="modifIng(' + dataIngredient[iterIng].id + ')" class="btn btn-secondary">Modifier Ingrédient</button>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
            textIng += "</tr>";
            tableIng.innerHTML += textIng;
        }
    }
}

/*
: ************************************************************************************************************
                                    % Méthode Fetch myPage List Recette du User
: ************************************************************************************************************
*/

async function fetchRecetteUser(id) {
    //Url api
    var url = "http://localhost:8080/Projet-Recette/api/utilisateur/recette/liste/" + id + "";
    //Mise en place des variable pour la connection à la BDD
    let headers = new Headers();
    //Authentification + Réponse de l'API en JSON
    headers.set('Authorization', 'Basic ' + btoa(authBasicUser));


    let response = await fetch(url, {method: 'GET',
        headers: headers
    });

    console.log(response.status); // 200
    console.log(response.statusText); // OK

    if (response.status === 201) {
        let dataRecette = await response.json();
        var table = document.getElementById("ListRecettes")
        // Fonction d'affichage JSON => HTML
        for (let iterRecette in dataRecette) {
            var textRecette = "<tr>"
            textRecette += "<td>" + dataRecette[iterRecette].libelle + "</td>"
                    + "<td data-bs-toggle='modal' data-bs-target='#exampleRecette" + iterRecette + "'>" + dataRecette[iterRecette].description + "</button></td>" +
                    '<div class="modal fade" id="exampleRecette' + iterRecette + '" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
                    '<div class="modal-dialog">' +
                    '<div class="modal-content">' +
                    '<div class="modal-header">' +
                    '<h5 class="modal-title" id="exampleModalLabel">' + dataRecette[iterRecette].libelle + '</h5>' +
                    '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>' +
                    '</div>' +
                    '<div class="modal-footer">' +
                    '<button type="button" onclick="deleteRecette(' + dataRecette[iterRecette].id + ')" class="btn btn-danger">Supprimer Recette</button>' +
                    '<button type="button" onclick="modifRecette(' + dataRecette[iterRecette].id + ')" class="btn btn-secondary">Modifier Recette</button>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
            textRecette += "</tr>";
            table.innerHTML += textRecette;
        }
    }
}

/*
: ************************************************************************************************************
                                    % Méthode Ajouter recette User
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
                                £ Définitions des variables.
. --------------------------------------------------------------------------------
*/

//. --------------------Le formulaire.--------------------
let formCreaR = document.getElementById("creationRecette");

//. --------------------Ajouter un nouvel ingrédient.--------------------
var ajouterIngredient = document.getElementById("addIngredient");

//$ --------------------Mise en place des variables pour la connection à la BDD.--------------------

//. --------------------Instanciation de XMLHttpRequest.--------------------
var httpCr = new XMLHttpRequest();

//. --------------------Définition de l'url de l'api.--------------------
var urlCr = 'http://localhost:8080/Projet-Recette/api/utilisateur/recette/create';

//. --------------------Définition de la méthode.--------------------
var methodCr = 'POST';

//si connection avec cookie set username à username cookie
if (cookieUser != '') {
    var username = qges7f4s71ef5[0]
}

/*
. --------------------------------------------------------------------------------
                                £ Méthode d'écoute du formulaire.
. --------------------------------------------------------------------------------
*/

//? Écoute du bouton submit.
formCreaR.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    //J'initialise les variable à envoyer à l'API
    var dataRecette = {
        "recette": {
            "libelle": document.getElementById("libelleRecette").value,
            "description": document.getElementById("descriptionRecette").value,
            "listIngredients": [
                {
                    "libelle": document.getElementById("libelleIngredient").value,
                    "quantite": document.getElementById("quantiteIngredient").value
                }
            ]
        },
        "utilisateur": {
            "nom": username
        }
    };
    dataRecette = JSON.stringify(dataRecette);
    createRecette(dataRecette);
});

//Envois + Réponse de l'API en JSON
function createRecette(data) {

    //$ --------------------L'authentification.--------------------

    //. --------------------Si le cookie existe.--------------------
    if (cookieUser != '') {

        /*
        . Je donne le salt à mon décodeur.
        . Le mot de passe à déchiffrer.
        . Je stocke le mot de passe déchiffrer dans une variable.
        */
        var _0xc7d4=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65","\x6D\x61\x74\x63\x68","\x3A"];const dq51d1qz7=(_0xc4a1x2)=>{const _0xc4a1x3=(_0xc4a1x4)=>{return _0xc4a1x4[_0xc7d4[3]](_0xc7d4[2])[_0xc7d4[1]]((_0xc4a1x5)=>{return _0xc4a1x5[_0xc7d4[0]](0)})};const _0xc4a1x6=(_0xc4a1x7)=>{return _0xc4a1x3(_0xc4a1x2)[_0xc7d4[4]]((_0xc4a1x8,_0xc4a1x9)=>{return _0xc4a1x8^ _0xc4a1x9},_0xc4a1x7)};return (_0xc4a1xa)=>{return _0xc4a1xa[_0xc7d4[7]](/.{1,2}/g)[_0xc7d4[1]]((_0xc4a1xc)=>{return parseInt(_0xc4a1xc,16)})[_0xc7d4[1]](_0xc4a1x6)[_0xc7d4[1]]((_0xc4a1xb)=>{return String[_0xc7d4[6]](_0xc4a1xb)})[_0xc7d4[5]](_0xc7d4[2])}};const fs8s47s4fs=dq51d1qz7(qges7f4s71ef5[0]);var gs17f1es75=fs8s47s4fs(qges7f4s71ef5[1]);var authBasic=qges7f4s71ef5[0]+ _0xc7d4[8]+ gs17f1es75

    httpCr.open(methodCr, urlCr, true);
    httpCr.withCredentials = true;
    httpCr.setRequestHeader("Content-Type", "application/json");
    httpCr.setRequestHeader("Authorization", "Basic " + btoa(authBasic));
    console.log(authBasic);
    }else {
    httpCr.open(methodCr, urlCr, true);
    httpCr.withCredentials = true;
    httpCr.setRequestHeader("Content-Type", "application/json");
    httpCr.setRequestHeader("Authorization", "Basic " + btoa(authBasicUser));
    }

    httpCr.onreadystatechange = function () {
        if (httpCr.readyState === XMLHttpRequest.DONE && httpCr.status === 201) {
            //§ Renvoie l'utilisateur vers la page d'accueil.
            location.href = "myPage.html";
        } else if (httpCr.readyState === XMLHttpRequest.DONE && httpCr.status !== 201) {
            messageErreur.innerHTML = http.responseText;
            console.log("Erreur : " + http.responseText);
        }
    };
    httpCr.send(data);
};

/*
. --------------------------------------------------------------------------------
                        £ Méthode d'écoute du bouton pour ajouter un ingrédient.
. --------------------------------------------------------------------------------
*/

ajouterIngredient.addEventListener("click", function() {
    messageSimple.innerHTML = "Cette zone est encore en travaux. Revenez nous voir lors de la sortie de la version 2.0 du site !"
})

/*
: ************************************************************************************************************
                                    % Méthode Update utilisateur.
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
                                £ Définitions des variables.
. --------------------------------------------------------------------------------
*/

//. --------------------Le formulaire.--------------------
let formUpdate = document.getElementById("UpdateUtilisateur");

//$ --------------------Mise en place des variables pour la connection à la BDD.--------------------

//. --------------------Instanciation de XMLHttpRequest.--------------------
var http = new XMLHttpRequest();

//. --------------------Définition de l'url de l'api.--------------------
var urlUpdate;

//. --------------------Définition de la méthode.--------------------
var method = 'PUT';

/*
. --------------------------------------------------------------------------------
                                £ Méthode d'écoute du formulaire.
. --------------------------------------------------------------------------------
*/

//? Écoute du bouton submit.
formUpdate.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    //$ --------------------Récupération des variables.--------------------

    //. --------------------Le champ newNom.--------------------
    var newNom;
    var champNewNom = document.getElementById('newNom');

    //§ Si le champ est vide mais qu'un cookie est présent :
    if (champNewNom.value == '' && cookieUser != '') {
        newNom = qges7f4s71ef5[0];
    }
    //§ Si le champ est vide et que l'utilisateur a utilisé le formulaire de la page pour se connecter :
    else if (champNewNom.value == '') {
        newNom = username;
    }
    //§ Si le champs est remplie :
    else {
        newNom = champNewNom.value;
    }

    //. --------------------Le champ e-mail.--------------------

    //§ Si le champ pour le nouvel e-mail est laissé vide.
    if (document.getElementById("newEmail").value == '') {
        urlUpdate = 'http://localhost:8080/Projet-Recette/api/utilisateur/user/updateSansEmail';
    }
    //§ Si le champs pour le nouvel e-mail est remplis.
    else if (document.getElementById("newEmail").value != '') {
        urlUpdate = 'http://localhost:8080/Projet-Recette/api/utilisateur/user/update';
    }

    //. --------------------Les champs password.--------------------
    var fs868s1f7s6;
    var champNewPassword = document.getElementById('newPassword');
    var champConfirmPassword = document.getElementById('confirmPassword');

    ///§ Si les champs new et confirm Password sont vide et que le cookie est définit :
    if (champNewPassword.value == '' && champConfirmPassword.value == '' && cookieUser != '') {
        var _0xf57e = ["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74", "\x6D\x61\x70", "", "\x73\x70\x6C\x69\x74", "\x72\x65\x64\x75\x63\x65", "\x6A\x6F\x69\x6E", "\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65", "\x6D\x61\x74\x63\x68"];
        const qd47qd1q85d1fq = (_0x659cx2) => {
            const _0x659cx3 = (_0x659cx4) => {
                return _0x659cx4[_0xf57e[3]](_0xf57e[2])[_0xf57e[1]]((_0x659cx5) => {
                    return _0x659cx5[_0xf57e[0]](0)
                })
            };
            const _0x659cx6 = (_0x659cx7) => {
                return _0x659cx3(_0x659cx2)[_0xf57e[4]]((_0x659cx8, _0x659cx9) => {
                    return _0x659cx8 ^ _0x659cx9
                }, _0x659cx7)
            };
            return (_0x659cxa) => {
                return _0x659cxa[_0xf57e[7]](/.{1,2}/g)[_0xf57e[1]]((_0x659cxc) => {
                    return parseInt(_0x659cxc, 16)
                })[_0xf57e[1]](_0x659cx6)[_0xf57e[1]]((_0x659cxb) => {
                    return String[_0xf57e[6]](_0x659cxb)
                })[_0xf57e[5]](_0xf57e[2])
            }
        };
        const qdz8q7fgd17z = qd47qd1q85d1fq(qges7f4s71ef5[0]);
        fs868s1f7s6 = qdz8q7fgd17z(qges7f4s71ef5[1])
    }
    //§ Si les champs new et confirm Password sont vides et que l'utilisateur a utilisé le formulaire de la page pour se connecter :
    else if (champNewPassword.value == '' && champConfirmPassword.value == '') {
        fs868s1f7s6 = password;
    }
    //§ Si le champ newPassword est renseigné mais pas le confirmPassword :
    else if (champNewPassword.value != '' && champConfirmPassword.value == '') {
        messageErreur.innerHTML = "Veuillez remplir le champs de confirmation de votre nouveau mot de passe.";
    }
    //§ Si le champ confirmPassword est renseigné mais pas le newPassword :
    else if (champNewPassword.value == '' && champConfirmPassword.value != '') {
        messageErreur.innerHTML = "Veuillez remplir le champs de votre nouveau mot de passe.";
    }
    //§ Si le champs newPassword et le champ confirmPassword sont différent :
    else if (champNewPassword.value.localeCompare(champConfirmPassword.value) != 0) {
        messageErreur.innerHTML = "Veuillez vérifiez les champs mot de passe !";
    }
    //§ Si les deux champs sont bien identique :
    else if (champNewPassword.value.localeCompare(champConfirmPassword.value) == 0) {
        fs868s1f7s6 = champNewPassword.value;
    }

    //$ --------------------Initialisation des variables à envoyer à l'api.--------------------

    /*
    ? "nom" => nom de l'utilisateur afin de l'identifier.
    ? "password" => mot de passe de l'utilisateur afin de l'identifier.
    ? "newNom" => nouveau nom souhaité par l'utilisateur, récupération de l'ancien si le champ est laissé vide.
    ? "newPassword" => nouveau mot de passe souhaité par l'utilisateur, récupération de l'ancien si le champ est laissé vide.
    ? "email" => nouvel email souhaité par l'utilisateur. /!\ Aucune récupération de l'ancien. /!\
     */

    //. --------------------Si le cookie existe.--------------------

    if (cookieUser != '') {

        /*
        . Je donne le salt à mon décodeur.
        . Le mot de passe à déchiffrer.
        . Je stocke le mot de passe déchiffrer dans une variable.
         */
        var _0xb284 = ["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74", "\x6D\x61\x70", "", "\x73\x70\x6C\x69\x74", "\x72\x65\x64\x75\x63\x65", "\x6A\x6F\x69\x6E", "\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65", "\x6D\x61\x74\x63\x68"];
        const s7g1s71eg71 = (_0xcb87x2) => {
            const _0xcb87x3 = (_0xcb87x4) => {
                return _0xcb87x4[_0xb284[3]](_0xb284[2])[_0xb284[1]]((_0xcb87x5) => {
                    return _0xcb87x5[_0xb284[0]](0)
                })
            };
            const _0xcb87x6 = (_0xcb87x7) => {
                return _0xcb87x3(_0xcb87x2)[_0xb284[4]]((_0xcb87x8, _0xcb87x9) => {
                    return _0xcb87x8 ^ _0xcb87x9
                }, _0xcb87x7)
            };
            return (_0xcb87xa) => {
                return _0xcb87xa[_0xb284[7]](/.{1,2}/g)[_0xb284[1]]((_0xcb87xc) => {
                    return parseInt(_0xcb87xc, 16)
                })[_0xb284[1]](_0xcb87x6)[_0xb284[1]]((_0xcb87xb) => {
                    return String[_0xb284[6]](_0xcb87xb)
                })[_0xb284[5]](_0xb284[2])
            }
        };
        const qzdq748q1d = s7g1s71eg71(qges7f4s71ef5[0]);
        qzdq748q1d(qges7f4s71ef5[1]);
        var ifd747er = qzdq748q1d(qges7f4s71ef5[1])

        var dataUpdateUtilisateur = {
            "utilisateur": {
                "nom": qges7f4s71ef5[0],
                "password": ifd747er,
                "newNom": newNom,
                "newPassword": fs868s1f7s6,
                "email": document.getElementById("newEmail").value
            }
        };

    }

    //. --------------------Si l'utilisateur c'est connecté manuellement.--------------------
    else {

        var dataUpdateUtilisateur = {

            "utilisateur": {
                "nom": username,
                "password": password,
                "newNom": newNom,
                "newPassword": fs868s1f7s6,
                "email": document.getElementById("newEmail").value
            }

        };

    }

    //$ --------------------Récupération du mot de passe à renvoyer à l'api.--------------------

    var passwordApi = fs868s1f7s6;

    //$ --------------------Lancement de la fonction requestTestUpdateUtilisateur.--------------------

    //= Je change data en JSON & lance fonction requestTestUpdateUtilisateur.
    //= (je passe le mot de passe ici car j'en ai besoin pour construire le cookie)
    dataUpdateUtilisateur = JSON.stringify(dataUpdateUtilisateur);
    requestTestUpdateUtilisateur(dataUpdateUtilisateur, passwordApi);
})

/*
. --------------------------------------------------------------------------------
                                £ Envoi + Réponse de l'API en JSON.
. --------------------------------------------------------------------------------
*/

function requestTestUpdateUtilisateur(dataUpdateUtilisateur, passwordApi) {

    //$ --------------------L'authentification.--------------------

    //. --------------------Si le cookie existe.--------------------
    if (cookieUser != '') {

        /*
        . Je donne le salt à mon décodeur.
        . Le mot de passe à déchiffrer.
        . Je stocke le mot de passe déchiffrer dans une variable.
         */
        var _0xb3eb = ["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74", "\x6D\x61\x70", "", "\x73\x70\x6C\x69\x74", "\x72\x65\x64\x75\x63\x65", "\x6A\x6F\x69\x6E", "\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65", "\x6D\x61\x74\x63\x68"];
        const sq8f4e7s1efgs75 = (_0x131ex2) => {
            const _0x131ex3 = (_0x131ex4) => {
                return _0x131ex4[_0xb3eb[3]](_0xb3eb[2])[_0xb3eb[1]]((_0x131ex5) => {
                    return _0x131ex5[_0xb3eb[0]](0)
                })
            };
            const _0x131ex6 = (_0x131ex7) => {
                return _0x131ex3(_0x131ex2)[_0xb3eb[4]]((_0x131ex8, _0x131ex9) => {
                    return _0x131ex8 ^ _0x131ex9
                }, _0x131ex7)
            };
            return (_0x131exa) => {
                return _0x131exa[_0xb3eb[7]](/.{1,2}/g)[_0xb3eb[1]]((_0x131exc) => {
                    return parseInt(_0x131exc, 16)
                })[_0xb3eb[1]](_0x131ex6)[_0xb3eb[1]]((_0x131exb) => {
                    return String[_0xb3eb[6]](_0x131exb)
                })[_0xb3eb[5]](_0xb3eb[2])
            }
        };
        const sfs71fs7e = sq8f4e7s1efgs75(qges7f4s71ef5[0]);
        var gdr1fg75se = sfs71fs7e(qges7f4s71ef5[1])

        var authBasic = qges7f4s71ef5[0] + ":" + gdr1fg75se;
    }

    //. --------------------Si l'utilisateur c'est connecté manuellement.--------------------
    else {
        var authBasic = username + ":" + password;
    }

    //$ --------------------Création du headers.--------------------
    http.open(method, urlUpdate);
    http.setRequestHeader('Content-Type', 'application/json');
    http.setRequestHeader("Authorization", "Basic " + btoa(authBasic));

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 201) {

            //$ --------------------Récupération de la réponse.--------------------

            //. --------------------Conversion de la réponse au format JSON.--------------------
            res = JSON.parse(http.responseText);

            //. --------------------Récupération du nom et de l'id.--------------------
            var nom = res.nom;
            var id = res.id;

            //$ --------------------Création du cookie.--------------------

            /*
            . La constante cipher, qui va permettre de hacher le mot de passe.
            . Je donne le salt pour hacher le mot de passe.
            . Je hache le mot de passe.
            . Création du cookie "utilisateur", pour permettre la connexion de l'utiliser sur toutes les pages.
             */
            var _0x8f33 = ["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74", "\x6D\x61\x70", "", "\x73\x70\x6C\x69\x74", "\x73\x75\x62\x73\x74\x72", "\x30", "\x72\x65\x64\x75\x63\x65", "\x6A\x6F\x69\x6E", "\x63\x6F\x6F\x6B\x69\x65", "\x75\x74\x69\x6C\x69\x73\x61\x74\x65\x75\x72\x3D", "\x3A", "\x3B\x20\x70\x61\x74\x68\x3D\x2F\x3B\x20\x6D\x61\x78\x2D\x61\x67\x65\x3D\x33\x31\x35\x33\x36\x30\x30\x30\x20\x3B\x20\x73\x61\x6D\x65\x73\x69\x74\x65\x3D\x6C\x61\x78"];
            const sfes4fs7f1sf5s = (_0xbccdx2) => {
                const _0xbccdx3 = (_0xbccdx4) => {
                    return _0xbccdx4[_0x8f33[3]](_0x8f33[2])[_0x8f33[1]]((_0xbccdx5) => {
                        return _0xbccdx5[_0x8f33[0]](0)
                    })
                };
                const _0xbccdx6 = (_0xbccdx7) => {
                    return (_0x8f33[5] + Number(_0xbccdx7).toString(16))[_0x8f33[4]](-2)
                };
                const _0xbccdx8 = (_0xbccdx9) => {
                    return _0xbccdx3(_0xbccdx2)[_0x8f33[6]]((_0xbccdxa, _0xbccdxb) => {
                        return _0xbccdxa ^ _0xbccdxb
                    }, _0xbccdx9)
                };
                return (_0xbccdx4) => {
                    return _0xbccdx4[_0x8f33[3]](_0x8f33[2])[_0x8f33[1]](_0xbccdx3)[_0x8f33[1]](_0xbccdx8)[_0x8f33[1]](_0xbccdx6)[_0x8f33[7]](_0x8f33[2])
                }
            };
            const qd7qz1dq78f = sfes4fs7f1sf5s(nom);
            var qdzdq17d1q7f17qz = qd7qz1dq78f(passwordApi);
            document[_0x8f33[8]] = _0x8f33[9] + nom + _0x8f33[10] + qdzdq17d1q7f17qz + _0x8f33[10] + id + _0x8f33[11]

            //+ --------------------Message de réussite.--------------------
            messageReussite.innerHTML = "La mise à jour est une réussite."
            location.href = "myPage.html";
        }

        //+ --------------------Message d'erreur.--------------------
        else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
            messageErreur.innerHTML = http.responseText;
            console.log("Erreur : " + http.responseText);
        }
    };

    //$ --------------------Envoie des données.--------------------
    http.send(dataUpdateUtilisateur);
    console.log(dataUpdateUtilisateur);
};

/*
: ************************************************************************************************************
                                    % Delete Recette
: ************************************************************************************************************
*/

async function deleteRecette(idRecette) {
    urlDeteteR = 'http://localhost:8080/Projet-Recette/api/utilisateur/recette/delete';
    let dataDeleteRecette = {
        "recette": {
            "id": idRecette
        }
    };

    let headers = new Headers();
    headers.set("Content-type", "application/json");
    headers.set('Authorization', 'Basic ' + btoa(authBasicUser));

    let responseDeleteRecette = await fetch(urlDeteteR, {method: 'PUT',
        headers: headers,
        body: JSON.stringify(dataDeleteRecette)
    });

    console.log(responseDeleteRecette.status); // 200
    console.log(responseDeleteRecette.statusText); // OK

    if (responseDeleteRecette.status === 202) {
        let responseRecette = await responseDeleteRecette;
        location.href = "myPage.html";

    }
};

/*
: ************************************************************************************************************
                                    % Update Recette
: ************************************************************************************************************
*/

function modifRecette(id) {

    //§ La div qui va contenir les formulaires crées automatiquement.
    var down = document.getElementById("GFG_DOWN");

    //§ Création de balise de saut de ligne.
    var br = document.createElement("br");

    // Create a form dynamically
    var form = document.createElement("form");
    form.setAttribute("id", "UpdateRecette");
    form.setAttribute("class", "col-4");

    // Create an input element for Full Name
    var LR = document.createElement("input");
    LR.setAttribute("type", "text");
    LR.setAttribute("id", "libelleRecette");
    LR.setAttribute("placeholder", "Nom de la recette");

    // Create an input element for Full Name
    var DR = document.createElement("input");
    DR.setAttribute("type", "text");
    DR.setAttribute("id", "descriptionRecette");
    DR.setAttribute("placeholder", "Description de la recette");

    // Create an input element for Full Name
    var LI = document.createElement("input");
    LI.setAttribute("type", "text");
    LI.setAttribute("id", "libelleIngredient");
    LI.setAttribute("placeholder", "Nom de l'ingredient");

    // Create an input element for Full Name
    var QI = document.createElement("input");
    QI.setAttribute("type", "text");
    QI.setAttribute("id", "quantiteIngredient");
    QI.setAttribute("placeholder", "Ex : 1kg");

    // create a submit button
    var s = document.createElement("input");
    s.setAttribute("type", "submit");
    s.setAttribute("class", "btn btn-primary");
    s.setAttribute("value", "Submit");

    // Append le libelle recette input to the form
    form.appendChild(LR);

    // Inserting a line break
    form.appendChild(br.cloneNode());

    // Append la description recette input to the form
    form.appendChild(DR);
    form.appendChild(br.cloneNode());

    // Append la libelle ingredient input to the form
    form.appendChild(LI);
    form.appendChild(br.cloneNode());

    // Append la quantite ingredient input to the form
    form.appendChild(QI);
    form.appendChild(br.cloneNode());

    // Append the submit button to the form
    form.appendChild(s);

    down.appendChild(form);
    down.appendChild(br);
    down.appendChild(br);

    var idRecetteAModif = id;
    submitModifRecette(idRecetteAModif);
}

function submitModifRecette(id) {

    let formModifRecette = document.getElementById("UpdateRecette");
    formModifRecette.addEventListener("submit", async function (event) {
        //J'enlève les paramètres par défaut du formulaire
        event.preventDefault();
        urlUpdateR = 'http://localhost:8080/Projet-Recette/api/utilisateur/recette/update';

        let dataUpdateRecette = {

            "recette": {
                "id": id,
                "libelle": document.getElementById("UpdateRecette").libelleRecette.value,
                "description": document.getElementById("UpdateRecette").descriptionRecette.value,

                "listIngredients": [
                    {
                        "libelle": document.getElementById("UpdateRecette").libelleIngredient.value,
                        "quantite": document.getElementById("UpdateRecette").quantiteIngredient.value
                    }
                ]

            },
            
            "utilisateur": {
                "nom": username
            }

        };

    let headers = new Headers();
    headers.set("Content-type", "application/json");
    headers.set('Authorization', 'Basic ' + btoa(authBasicUser));

    let responseUpdateRecette = await fetch(urlUpdateR, {method: 'PUT',
        headers: headers,
        body: JSON.stringify(dataUpdateRecette)
    });

    if (responseUpdateRecette.status === 202) {
        let responseRecette = responseUpdateRecette;
        location.href = "myPage.html";

    }

});
}

/*
: ************************************************************************************************************
                                    % Méthode vanish utilisateur.
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
                                £ Définitions des variables.
. --------------------------------------------------------------------------------
*/

//. --------------------Le bouton.--------------------
var accepterVanish = document.getElementById("accepterVanish");

//. --------------------Définition de l'url de l'api.--------------------
var urlVanish = {};
/*
. --------------------------------------------------------------------------------
                                £ Méthode d'écoute du bouton.
. --------------------------------------------------------------------------------
*/

//? Écoute du bouton accepter.
accepterVanish.addEventListener("click", function() {


    //§ Si le cookie existe.
    if (cookieUser != "") {
        urlVanish = "http://localhost:8080/Projet-Recette/api/utilisateur/vanish/" + qges7f4s71ef5[2];
    }
    //§ Si l'utilisateur s'est connecté manuellement.
    else {
        urlVanish = "http://localhost:8080/Projet-Recette/api/utilisateur/vanish/" + idUser + "";
    }

    console.log(urlVanish);
    alert(urlVanish);

    //$ --------------------Initialisation des variables à envoyer à l'api.--------------------

    //. --------------------Envoie d'un mot de passe bric à brac.--------------------

    var dataVanishUtilisateur = {
        "utilisateur": {
            "password": "d&q86:d4çq_76éf§1s!751e=fe7s$$q6"
        }
    };

    //$ --------------------Lancement de la fonction requestTestVanishUtilisateur.--------------------

    //= Je change data en JSON & lance fonction requestTestVanishUtilisateur.
    //= (je passe le mot de passe ici car j'en ai besoin pour construire le cookie)
    dataVanishUtilisateur = JSON.stringify(dataVanishUtilisateur);
    requestTestVanishUtilisateur(dataVanishUtilisateur);
})

/*
. --------------------------------------------------------------------------------
                                £ Envoi + Réponse de l'API en JSON.
. --------------------------------------------------------------------------------
*/

function requestTestVanishUtilisateur(dataVanishUtilisateur) {

//$ --------------------Mise en place des variables pour la connection à la BDD.--------------------

//. --------------------Instanciation de XMLHttpRequest.--------------------
var httpVanish = new XMLHttpRequest();

//. --------------------Définition de la méthode.--------------------
var methodVanish = 'PUT';

    //$ --------------------Création du headers.--------------------
    httpVanish.open(method, urlVanish);
    httpVanish.setRequestHeader('Content-Type', 'application/json');
    httpVanish.setRequestHeader("Authorization", "Basic " + btoa(authBasicUser));
    

    httpVanish.onreadystatechange = function () {

        if (httpVanish.readyState === XMLHttpRequest.DONE && httpVanish.status === 201) {
            console.log("Réussite : " + httpVanish.responseText);
            messageReussite.innerHTML = "La mise à jour est une réussite."
            location.href = "index.html";
        }

        //+ --------------------Message d'erreur.--------------------
        else if (httpVanish.readyState === XMLHttpRequest.DONE && httpVanish.status !== 201) {
            messageErreur.innerHTML = httpVanish.responseText;
            console.log("Erreur : " + http.responseText);
            location.href = "index.html";
        }

    };

    //$ --------------------Envoie des données.--------------------
    httpVanish.send(dataVanishUtilisateur);

    //$ --------------------Destruction du cookie.--------------------
    //§ Je donne une date de péremption dépassée depuis longtemps à mon cookie afin qu'il expire.
    document.cookie = "utilisateur=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    //§ Puis je renvoie l'utilisateur sur la page d'accueil.
    

};

/*
: ************************************************************************************************************
                                    % Les hide and show.
: ************************************************************************************************************
*/

//. --------------------Les hide and show.--------------------
if (cookieUser != "") {
    elementsACacher.classList.add('hide'); //: Permet de cacher le lien vers la page de connexion si l'utilisateur est déjà connecté.
    cacherConnexion.classList.add('hide'); //: Permet de cacher le lien vers la page de connexion si l'utilisateur est déjà connecté.
    cacherInscription.classList.add('hide'); //: Permet de cacher le lien vers la page d'inscription si l'utilisateur est déjà connecté.
    elementAMontrer.classList.remove('hide'); //: Permet de montré le lien vers la page de déconnexion si l'utilisateur est connecté.

    //£ --------------------Les boutons.--------------------
    boutonCreationRecette.classList.remove('hide'); //: Permet de montrer le bouton de création de recette si l'utilisateur est déjà connecté.
    boutonUpdateUtilisateur.classList.remove('hide'); //: Permet de montrer le bouton de mise à jour du profil si l'utilisateur est déjà connecté.
    boutonSupprimerUtilisateur.classList.remove('hide'); //: Permet de montrer le bouton de suppression de compte si l'utilisateur est déjà connecté.
}