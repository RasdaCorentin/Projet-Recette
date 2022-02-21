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

//= Méthode d'écoute du formulaire.
let formUpdate = document.getElementById("UpdateUtilisateur");

//? Écoute du bouton submit.
formUpdate.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    //= Url api.
    var url = "'http://localhost:8080/Projet-Recette/api/utilisateur/update";
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