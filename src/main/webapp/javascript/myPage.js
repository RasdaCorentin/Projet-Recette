/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
/********************************
 
 Methode Fetch myPage List Recette du User
 
 *******************************/

let formIdUser = document.getElementById("Identification");
var authBasicUser = {};
var username = {};
var password = {};

//Ecoute bouton submit
formIdUser.addEventListener("submit", async function (event) {
    //J'enléve les paramétres par défaut du formulaire
    event.preventDefault();
    elementsIdentificationACacher.classList.add('hide');
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
        headers: headers
    })
    console.log(responseIdUser.status); // 200
    console.log(responseIdUser.statusText); // OK

    if (responseIdUser.status === 202) {
        let data = await responseIdUser.json();
        idUser = data.id;
    }
    fetchData(idUser);
});
/********************************
 
 Methode Fetch myPage List Recette du User
 
 *******************************/

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
        let dataRecette = await response.json();
        console.log(dataRecette);
        var table = document.getElementById("ListRecettes")
        // Fonction d'affichage JSON => HTML
        for (let iterRecette in dataRecette) {
            var text = "<tr>"
            text += "<td>" + dataRecette[iterRecette].libelle + "</td>"
                        + "<td data-bs-toggle='modal' data-bs-target='#exampleModal"+ iterRecette +"'>" + dataRecette[iterRecette].description + "</button></td>"+
        '<div class="modal fade" id="exampleModal'+ iterRecette +'" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
                '<div class="modal-content">'+

                    '<div class="modal-header">'+
                        '<h5 class="modal-title" id="exampleModalLabel">' + dataRecette[iterRecette].libelle + '</h5>'+
                        '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>'+
                    '</div>'+
                    '<div class="modal-footer">'+
                        '<button type="button" onclick="deleteRecette('+ dataRecette[iterRecette].id +')" class="btn btn-danger">Supprimer Recette</button>'+
                        '<button type="button" onclick="modifRecette()" class="btn btn-secondary">Modifier Recette</button>'+
                    '</div>'+

                '</div>'+
            '</div>'+
        '</div>';
            text += "</tr>";
                table.innerHTML += text;
            }
        }
    }

/********************************
 
 Methode Ajouter recette User
 
 *******************************/


//Methode d'écoute sur Formulaire
let formCreaR = document.getElementById("creationRecette");
//Ecoute bouton submit
formCreaR.addEventListener("submit", function (event) {
    //J'enléve les paramétres par défaut du formulaire
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
            //Ici je récupére la réponse en JSON que je met dans var nom & id 
            res = JSON.parse(httpCr.responseText);
            //§ Renvoie l'utilisateur vers la page d'accueil.
            location.href = "myPage.html";
        } else if (httpCr.readyState === XMLHttpRequest.DONE && httpCr.status !== 201) {
            alert("Error : " + httpCr.responseText);
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
    if(champNewNom.value=='' && cookieUtilisateur!='') {
        newNom = qges7f4s71ef5[0];
    } 
    //§ Si le champ est vide et que l'utilisateur a utilisé le formulaire de la page pour se connecter :
    else if (champNewNom.value=='') {
        newNom = username;
    }
    //§ Si le champs est remplie :
    else {
        newNom = champNewNom.value;
    }

    //. --------------------Le champ e-mail.--------------------

    //§ Si le champ pour le nouvel e-mail est laissé vide.
    if (document.getElementById("newEmail").value=='') {
        urlUpdate = 'http://localhost:8080/Projet-Recette/api/utilisateur/user/updateSansEmail';
    }
    //§ Si le champs pour le nouvel e-mail est remplis.
    else if (document.getElementById("newEmail").value!='') {
        urlUpdate = 'http://localhost:8080/Projet-Recette/api/utilisateur/user/update';
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
        var _0xf57e=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65","\x6D\x61\x74\x63\x68"];const qd47qd1q85d1fq=(_0x659cx2)=>{const _0x659cx3=(_0x659cx4)=>{return _0x659cx4[_0xf57e[3]](_0xf57e[2])[_0xf57e[1]]((_0x659cx5)=>{return _0x659cx5[_0xf57e[0]](0)})};const _0x659cx6=(_0x659cx7)=>{return _0x659cx3(_0x659cx2)[_0xf57e[4]]((_0x659cx8,_0x659cx9)=>{return _0x659cx8^ _0x659cx9},_0x659cx7)};return (_0x659cxa)=>{return _0x659cxa[_0xf57e[7]](/.{1,2}/g)[_0xf57e[1]]((_0x659cxc)=>{return parseInt(_0x659cxc,16)})[_0xf57e[1]](_0x659cx6)[_0xf57e[1]]((_0x659cxb)=>{return String[_0xf57e[6]](_0x659cxb)})[_0xf57e[5]](_0xf57e[2])}};const qdz8q7fgd17z=qd47qd1q85d1fq(qges7f4s71ef5[0]);fs868s1f7s6= qdz8q7fgd17z(qges7f4s71ef5[1])
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

    /*
    & Entourer la vérification de cookie et la prise manuel des identifiants par la vérification du champs email.
    ! Il va falloir doubler les lignes 333 à 336.
    */

    //§ Si le champ pour le nouvel e-mail est laissé vide.
    if (document.getElementById("newEmail").value=='') {
        alert("vide");
    }

    //§ Si le champs pour le nouvel e-mail est remplis.
    else if (document.getElementById("newEmail").value!='') {
        alert("plein");
    }

    //. --------------------Si le cookie existe.--------------------

    if (cookieUtilisateur != '') {

        /*
        . Je donne le salt à mon décodeur.
        . Le mot de passe à déchiffrer.
        . Je stocke le mot de passe déchiffrer dans une variable.
        */
        var _0xb284=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65","\x6D\x61\x74\x63\x68"];const s7g1s71eg71=(_0xcb87x2)=>{const _0xcb87x3=(_0xcb87x4)=>{return _0xcb87x4[_0xb284[3]](_0xb284[2])[_0xb284[1]]((_0xcb87x5)=>{return _0xcb87x5[_0xb284[0]](0)})};const _0xcb87x6=(_0xcb87x7)=>{return _0xcb87x3(_0xcb87x2)[_0xb284[4]]((_0xcb87x8,_0xcb87x9)=>{return _0xcb87x8^ _0xcb87x9},_0xcb87x7)};return (_0xcb87xa)=>{return _0xcb87xa[_0xb284[7]](/.{1,2}/g)[_0xb284[1]]((_0xcb87xc)=>{return parseInt(_0xcb87xc,16)})[_0xb284[1]](_0xcb87x6)[_0xb284[1]]((_0xcb87xb)=>{return String[_0xb284[6]](_0xcb87xb)})[_0xb284[5]](_0xb284[2])}};const qzdq748q1d=s7g1s71eg71(qges7f4s71ef5[0]);qzdq748q1d(qges7f4s71ef5[1]);var ifd747er=qzdq748q1d(qges7f4s71ef5[1])

        var dataUpdateUtilisateur = {
            "utilisateur": {
                "nom": qges7f4s71ef5[0],
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

    //$ --------------------L'authentification.--------------------

    //. --------------------Si le cookie existe.--------------------
    if (cookieUtilisateur != '') {

        /*
        . Je donne le salt à mon décodeur.
        . Le mot de passe à déchiffrer.
        . Je stocke le mot de passe déchiffrer dans une variable.
        */
        var _0xb3eb=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65","\x6D\x61\x74\x63\x68"];const sq8f4e7s1efgs75=(_0x131ex2)=>{const _0x131ex3=(_0x131ex4)=>{return _0x131ex4[_0xb3eb[3]](_0xb3eb[2])[_0xb3eb[1]]((_0x131ex5)=>{return _0x131ex5[_0xb3eb[0]](0)})};const _0x131ex6=(_0x131ex7)=>{return _0x131ex3(_0x131ex2)[_0xb3eb[4]]((_0x131ex8,_0x131ex9)=>{return _0x131ex8^ _0x131ex9},_0x131ex7)};return (_0x131exa)=>{return _0x131exa[_0xb3eb[7]](/.{1,2}/g)[_0xb3eb[1]]((_0x131exc)=>{return parseInt(_0x131exc,16)})[_0xb3eb[1]](_0x131ex6)[_0xb3eb[1]]((_0x131exb)=>{return String[_0xb3eb[6]](_0x131exb)})[_0xb3eb[5]](_0xb3eb[2])}};const sfs71fs7e=sq8f4e7s1efgs75(qges7f4s71ef5[0]);var gdr1fg75se=sfs71fs7e(qges7f4s71ef5[1])

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
            var _0x8f33=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x73\x75\x62\x73\x74\x72","\x30","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x63\x6F\x6F\x6B\x69\x65","\x75\x74\x69\x6C\x69\x73\x61\x74\x65\x75\x72\x3D","\x3A","\x3B\x20\x70\x61\x74\x68\x3D\x2F\x3B\x20\x6D\x61\x78\x2D\x61\x67\x65\x3D\x33\x31\x35\x33\x36\x30\x30\x30\x20\x3B\x20\x73\x61\x6D\x65\x73\x69\x74\x65\x3D\x6C\x61\x78"];const sfes4fs7f1sf5s=(_0xbccdx2)=>{const _0xbccdx3=(_0xbccdx4)=>{return _0xbccdx4[_0x8f33[3]](_0x8f33[2])[_0x8f33[1]]((_0xbccdx5)=>{return _0xbccdx5[_0x8f33[0]](0)})};const _0xbccdx6=(_0xbccdx7)=>{return (_0x8f33[5]+ Number(_0xbccdx7).toString(16))[_0x8f33[4]](-2)};const _0xbccdx8=(_0xbccdx9)=>{return _0xbccdx3(_0xbccdx2)[_0x8f33[6]]((_0xbccdxa,_0xbccdxb)=>{return _0xbccdxa^ _0xbccdxb},_0xbccdx9)};return (_0xbccdx4)=>{return _0xbccdx4[_0x8f33[3]](_0x8f33[2])[_0x8f33[1]](_0xbccdx3)[_0x8f33[1]](_0xbccdx8)[_0x8f33[1]](_0xbccdx6)[_0x8f33[7]](_0x8f33[2])}};const qd7qz1dq78f=sfes4fs7f1sf5s(nom);var qdzdq17d1q7f17qz=qd7qz1dq78f(passwordApi);document[_0x8f33[8]]= _0x8f33[9]+ nom+ _0x8f33[10]+ qdzdq17d1q7f17qz+ _0x8f33[10]+ id+ _0x8f33[11]

            //+ --------------------Message de réussite.--------------------
            document.getElementById("messageErreur").innerHTML = "La mise à jour est une réussite."
        } 

        //+ --------------------Message d'erreur.--------------------
        else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
            document.getElementById("messageErreur").innerHTML = http.responseText;
            console.log("Erreur : " + http.responseText);
        }
    };

    //$ --------------------Envoie des données.--------------------
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
var elementsIdentificationACacher = document.getElementById("Identification");

//. --------------------Les hide and show.--------------------
if (cookieUtilisateur != "") {
    elementsACacher.classList.add('hide'); //: Permet de cacher le lien vers la page de connexion si l'utilisateur est déjà connecté.
    cacherConnexion.classList.add('hide'); //: Permet de cacher le lien vers la page de connexion si l'utilisateur est déjà connecté.
    cacherInscription.classList.add('hide'); //: Permet de cacher le lien vers la page d'inscription si l'utilisateur est déjà connecté.
    elementAMontrer.classList.remove('hide'); //: Permet de montré le lien vers la page de déconnexion si l'utilisateur est connecté.
    elementsIdentificationACacher.classList.add('hide'); //: Permet de cacher le formulaire si l'utilisateur est déjà connecté.
}