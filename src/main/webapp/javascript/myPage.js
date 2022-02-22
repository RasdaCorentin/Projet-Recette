/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
: ************************************************************************************************************
                                    % Récupération du cookie.
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
            console.log(c.substring(name.length, c.length));
            //retourne ("utilisateur:nom:password:id")
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

let donneeUtilisateurCookie = getCookie("utilisateur");
donneeUtilisateurCookie = donneeUtilisateurCookie.split(":");

/*
: ************************************************************************************************************
                                    % Méthode Fetch myPage List Recette du User.
: ************************************************************************************************************
*/

let formIdUser = document.getElementById("Identification");
var authBasicUser = {};
var username = {};
var password = {};

//Écoute bouton submit
formIdUser.addEventListener("submit", async function (event) {
    //J'enlève les paramètres par défaut du formulaire
    event.preventDefault();
    username = document.getElementById("nom").value;
    password = document.getElementById("password").value;
    authBasicUser = username + ":" + password;

let _data = {
    "utilisateur": {
        "nom": username
    }
}
var headers = new Headers();
headers.set("Content-type", "application/json");
headers.set('Authorization', 'Basic ' + btoa(authBasicUser));
responseIdUser = await fetch('http://localhost:8080/Projet-Recette/api/utilisateur/read', {
    method: "POST",
    body: JSON.stringify(_data),
    headers:headers
})
        console.log(responseIdUser.status); // 200
        console.log(responseIdUser.statusText); // OK

        if (responseIdUser.status === 202) {
            let data = await responseIdUser.json();
            idUser = data.id;
        }
    fetchData(idUser);
});

/*
: ************************************************************************************************************
                                    % Méthode Fetch myPage List Recette du User.
: ************************************************************************************************************
*/

    async function fetchData(id) {
        //Url api
        var url = "http://localhost:8080/Projet-Recette/api/utilisateur/recette/liste/" + id + "";
        //Mise en place des variable pour la connection à la BDD
        var headers = new Headers();
        //Authentification + Réponse de l'API en JSON
        headers.set('Authorization', 'Basic ' + btoa(authBasicUser));


        let response = await fetch(url, {method: 'GET',
            headers: headers
        });

        console.log(response.status); // 200
        console.log(response.statusText); // OK

        if (response.status === 201) {
            let data = await response.json();
            console.log(data);
            var table = document.getElementById("ListRecettes")
            // Fonction d'affichage JSON => HTML
            for (let i in data) {
                var text = "<tr>"
                text += "<td>" + data[i].libelle + "</td>"
                        + "<td>" + data[i].description + "</td>"
                text += "</tr>";
                table.innerHTML += text;
            }
            elementsIdentificationACacher.classList.add('hide');
        }
    }

/*
: ************************************************************************************************************
                                    % Méthode Ajouter recette User.
: ************************************************************************************************************
*/


//Méthode d'écoute sur Formulaire
let formCreaR = document.getElementById("creationRecette");
//Écoute bouton submit
formCreaR.addEventListener("submit", function (event) {
    //J'enlève les paramètres par défaut du formulaire
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
            "nom": "Corentin"
        }
    };
    dataRecette = JSON.stringify(dataRecette);
    createRecette(dataRecette);
});

//Envois + Réponse de l'API en JSON
function createRecette(data) {

    //Mise en place des variable pour la connection à la BDD
    var httpCr = new XMLHttpRequest();
//Url api
    var urlCr = 'http://localhost:8080/Projet-Recette/api/utilisateur/recette/create';
    var methodCr = 'POST';
    httpCr.open(methodCr, urlCr, true);
    httpCr.withCredentials = true;
    httpCr.setRequestHeader("Content-Type", "application/json");
    httpCr.setRequestHeader("Authorization", "Basic " + btoa(authBasicUser));
    httpCr.onreadystatechange = function () {
        if (httpCr.readyState === XMLHttpRequest.DONE && httpCr.status === 201) {
            //Ici je récupère la réponse en JSON que je met dans var nom & id 
            res = JSON.parse(httpCr.responseText);
            //§ Renvoie l'utilisateur vers la page d'accueil.
            location.href = "myPage.html";
        } else if (httpCr.readyState === XMLHttpRequest.DONE && httpCr.status !== 201) {
            document.getElementById("messageErreur").innerHTML = http.responseText;
            console.log("Erreur : " + http.responseText);
        }
    };

    httpCr.send(data);
};

/*
: ************************************************************************************************************
                                    % Méthode Update utilisateur.
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
                                £ Méthode d'écoute du formulaire.
. --------------------------------------------------------------------------------
*/

//= Méthode d'écoute sur Formulaire.
let formUpdate = document.getElementById("UpdateUtilisateur");

//= Mise en place des variable pour la connection à la BDD
var http = new XMLHttpRequest();

//= Url api
var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/user/update';
var method = 'PUT';

//? Écoute du bouton submit.
formUpdate.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    //$ --------------------Récupération des variables.--------------------

    //. --------------------Le champ newNom.--------------------
    var newNom;
    var champNewNom = document.getElementById('newNom');

    //§ Si le champ est vide mais qu'un cookie est présent :
    if(champNewNom.value=='' && cookieUtilisateur!='') {
        newNom = donneeUtilisateurCookie[0];
        console.log("------------" + newNom + "----------------");
    } 
    //§ Si le champ est vide et que l'utilisateur a utilisé le formulaire de la page pour se connecter :
    else if (champNewNom.value=='') {
        newNom = username;
    }
    //§ Si le champs est remplie :
    else {
        newNom = champNewNom.value;
    }

    //. --------------------Les champs password.--------------------
    var fs868s1f7s6;
    var champNewPassword = document.getElementById('newPassword');
    var champConfirmPassword = document.getElementById('confirmPassword');

    /*
    . Je donne le salt à mon décodeur.
    . Le mot de passe à déchiffrer.
    . Je stocke le mot de passe déchiffrer dans la variable.
    */
    ///§ Si les champs new et confirm Password sont vide et que le cookie est définit :
    if (champNewPassword.value=='' && champConfirmPassword.value=='' && cookieUtilisateur!='') {
        var _0xf57e=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65","\x6D\x61\x74\x63\x68"];const qd47qd1q85d1fq=(_0x659cx2)=>{const _0x659cx3=(_0x659cx4)=>{return _0x659cx4[_0xf57e[3]](_0xf57e[2])[_0xf57e[1]]((_0x659cx5)=>{return _0x659cx5[_0xf57e[0]](0)})};const _0x659cx6=(_0x659cx7)=>{return _0x659cx3(_0x659cx2)[_0xf57e[4]]((_0x659cx8,_0x659cx9)=>{return _0x659cx8^ _0x659cx9},_0x659cx7)};return (_0x659cxa)=>{return _0x659cxa[_0xf57e[7]](/.{1,2}/g)[_0xf57e[1]]((_0x659cxc)=>{return parseInt(_0x659cxc,16)})[_0xf57e[1]](_0x659cx6)[_0xf57e[1]]((_0x659cxb)=>{return String[_0xf57e[6]](_0x659cxb)})[_0xf57e[5]](_0xf57e[2])}};const qdz8q7fgd17z=qd47qd1q85d1fq(donneeUtilisateurCookie[0]);fs868s1f7s6= qdz8q7fgd17z(donneeUtilisateurCookie[1])
    }
    //§ Si les champs new et confirm Password sont vides et que l'utilisateur a utilisé le formulaire de la page pour se connecter :
    else if (champNewPassword.value=='' && champConfirmPassword.value=='') {
        fs868s1f7s6 = password;
    }
    //§ Si le champ newPassword est renseigné mais pas le confirmPassword :
    else if (champNewPassword.value!='' && champConfirmPassword.value=='') {
        document.getElementById("messageErreur").innerHTML = "Veuillez remplir le champs de confirmation de votre nouveau mot de passe.";
    }
    //§ Si le champ confirmPassword est renseigné mais pas le newPassword :
    else if (champNewPassword.value=='' && champConfirmPassword.value!='') {
        document.getElementById("messageErreur").innerHTML = "Veuillez remplir le champs de votre nouveau mot de passe.";
    }
    //§ Si le champs newPassword et le champ confirmPassword sont différent :
    else if (champNewPassword.value.localeCompare(champConfirmPassword.value)!=0) {
        document.getElementById("messageErreur").innerHTML = "Veuillez vérifiez les champs mot de passe !";
    }
    //§ Si les deux champs sont bien identique :
    else if (champNewPassword.value.localeCompare(champConfirmPassword.value)==0) {
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

    if (cookieUtilisateur != '') {

        /*
        . Je donne le salt à mon décodeur.
        . Le mot de passe à déchiffrer.
        . Je stocke le mot de passe déchiffrer dans la variable.
        */
        var _0xb284=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65","\x6D\x61\x74\x63\x68"];const s7g1s71eg71=(_0xcb87x2)=>{const _0xcb87x3=(_0xcb87x4)=>{return _0xcb87x4[_0xb284[3]](_0xb284[2])[_0xb284[1]]((_0xcb87x5)=>{return _0xcb87x5[_0xb284[0]](0)})};const _0xcb87x6=(_0xcb87x7)=>{return _0xcb87x3(_0xcb87x2)[_0xb284[4]]((_0xcb87x8,_0xcb87x9)=>{return _0xcb87x8^ _0xcb87x9},_0xcb87x7)};return (_0xcb87xa)=>{return _0xcb87xa[_0xb284[7]](/.{1,2}/g)[_0xb284[1]]((_0xcb87xc)=>{return parseInt(_0xcb87xc,16)})[_0xb284[1]](_0xcb87x6)[_0xb284[1]]((_0xcb87xb)=>{return String[_0xb284[6]](_0xcb87xb)})[_0xb284[5]](_0xb284[2])}};const qzdq748q1d=s7g1s71eg71(donneeUtilisateurCookie[0]);qzdq748q1d(donneeUtilisateurCookie[1]);var ifd747er=qzdq748q1d(donneeUtilisateurCookie[1])

        var dataUpdateUtilisateur = {
            "utilisateur": {
                "nom": donneeUtilisateurCookie[0],
                "password": ifd747er,
                "newNom" : newNom,
                "newPassword" : fs868s1f7s6,
                "email" : document.getElementById("newEmail").value
            }
        };

    } 

    //. --------------------Si l'utilisateur c'est connecté manuellement.--------------------

    else {

        var dataUpdateUtilisateur = {
            "utilisateur": {
                "nom": username,
                "password": password,
                "newNom" : newNom,
                "newPassword" : fs868s1f7s6,
                "email" : document.getElementById("newEmail").value
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
            //= La constante cipher, qui va permettre de hacher le mot de passe.
            const sfes4fs7f1sf5s = salt => {
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
            const qd7qz1dq78f = sfes4fs7f1sf5s(nom);

            //= Je hache le mot de passe.
            var qdzdq17d1q7f17qz = qd7qz1dq78f(passwordApi);

            //§ Création du cookie "utilisateur", pour permettre la connexion de l'utiliser sur toutes les pages.
            document.cookie = "utilisateur=" + nom + ":" + qdzdq17d1q7f17qz + ":" + id +"; path=/; max-age=31536000 ; samesite=lax"; //= Ce cookie à une durée de vie d'un an.
            */

            /*
            . La constante cipher, qui va permettre de hacher le mot de passe.
            . Je donne le salt pour hacher le mot de passe.
            . Je hache le mot de passe.
            . Création du cookie "utilisateur", pour permettre la connexion de l'utiliser sur toutes les pages.
            */

            var _0x8f33=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x73\x75\x62\x73\x74\x72","\x30","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x63\x6F\x6F\x6B\x69\x65","\x75\x74\x69\x6C\x69\x73\x61\x74\x65\x75\x72\x3D","\x3A","\x3B\x20\x70\x61\x74\x68\x3D\x2F\x3B\x20\x6D\x61\x78\x2D\x61\x67\x65\x3D\x33\x31\x35\x33\x36\x30\x30\x30\x20\x3B\x20\x73\x61\x6D\x65\x73\x69\x74\x65\x3D\x6C\x61\x78"];const sfes4fs7f1sf5s=(_0xbccdx2)=>{const _0xbccdx3=(_0xbccdx4)=>{return _0xbccdx4[_0x8f33[3]](_0x8f33[2])[_0x8f33[1]]((_0xbccdx5)=>{return _0xbccdx5[_0x8f33[0]](0)})};const _0xbccdx6=(_0xbccdx7)=>{return (_0x8f33[5]+ Number(_0xbccdx7).toString(16))[_0x8f33[4]](-2)};const _0xbccdx8=(_0xbccdx9)=>{return _0xbccdx3(_0xbccdx2)[_0x8f33[6]]((_0xbccdxa,_0xbccdxb)=>{return _0xbccdxa^ _0xbccdxb},_0xbccdx9)};return (_0xbccdx4)=>{return _0xbccdx4[_0x8f33[3]](_0x8f33[2])[_0x8f33[1]](_0xbccdx3)[_0x8f33[1]](_0xbccdx8)[_0x8f33[1]](_0xbccdx6)[_0x8f33[7]](_0x8f33[2])}};const qd7qz1dq78f=sfes4fs7f1sf5s(nom);var qdzdq17d1q7f17qz=qd7qz1dq78f(passwordApi);document[_0x8f33[8]]= _0x8f33[9]+ nom+ _0x8f33[10]+ qdzdq17d1q7f17qz+ _0x8f33[10]+ id+ _0x8f33[11]

            console.log("Cookie:" + document.cookie);

        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
            document.getElementById("messageErreur").innerHTML = http.responseText;
            console.log("Erreur : " + http.responseText);
        }
    };

    http.send(dataUpdateUtilisateur);
    console.log(dataUpdateUtilisateur);
};

/*
: ************************************************************************************************************
                                    % Les hide and show.
: ************************************************************************************************************
*/

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