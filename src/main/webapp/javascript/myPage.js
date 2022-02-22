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
}
;
/*
: ************************************************************************************************************
                                    % Méthode Update utilisateur.
: ************************************************************************************************************
*/

//= Méthode d'écoute du formulaire.
let formUpdate = document.getElementById("UpdateUtilisateur");

//? Écoute du bouton submit.
formUpdate.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    /*
    username :
    0 => nom
    1 => password
    2 => id
    */

    //. --------------------Le champ newNom.--------------------
    var newNom;
    var champNewNom = document.getElementById('newNom');

    //§ Si le champ est vide mais qu'un cookie est présent :
    if(champNewNom.value=='' && cookieUtilisateur!='') {
        newNom = JSON.stringify(donneeUtilisateurCookie[0]);
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
    const qd47qd1q85d1fq = salt => {
        const textToChars = text => text.split('').map(c => c.charCodeAt(0));
        const applySaltToChar = code => textToChars(salt).reduce((a,b) => a ^ b, code);
        return encoded => encoded.match(/.{1,2}/g)
            .map(hex => parseInt(hex, 16))
            .map(applySaltToChar)
            .map(charCode => String.fromCharCode(charCode))
            .join('');
    }

    const qdz8q7fgd17z = qd47qd1q85d1fq(donneeUtilisateurCookie[0])

    fs868s1f7s6 = qdz8q7fgd17z(donneeUtilisateurCookie[1]);
    */

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

    //= J'initialise les variable à envoyer à l'API.
    // var data = {
    //     "utilisateur": {
    //         "nom": document.getElementById("nom").value,
    //         "password": document.getElementById("password").value,
    //         "email": document.getElementById("email").value
    //     }
    // };

    /*
    : ************************************************************************************************************
                                    % Je prend une seconde fois le mot de passe pour le mettre dans le cookie.
    : ************************************************************************************************************
    */

    // var password = document.getElementById("password").value;

    // //= Je change data en JSON & lance fonction requestTest.
    // //= (je passe le mot de passe ici car j'en ai besoin pour construire le cookie)
    // data = JSON.stringify(data);
    // requestTest(data, password);
})

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