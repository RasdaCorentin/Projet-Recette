/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
: ***********************************************************************************************************************
                                    % Méthode Fetch myPage List Recette du User.
: ***********************************************************************************************************************
*/

//? Méthode d'écoute sur le formulaire.
let form = document.getElementById("myForm");

//? Écoute du bouton submit.
form.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();

    //= Url api.
    var url = "'http://localhost:8080/Projet-Recette/api/utilisateur/recette/liste/";

    //= Mise en place des variable pour la connection à la BDD.
    var headers = new Headers();
    var username = document.getElementById("nom").value;
    var password = document.getElementById("password").value;
    var authBasic = username + ":" + password;
    async function fetchData() {
        //= Authentification + Réponse de l'API en JSON.
        headers.set('Authorization', 'Basic ' + btoa(authBasic));

        let response = await fetch(url, {method: 'GET', headers: headers});

        console.log(response.status); // 200
        console.log(response.statusText); // OK

        if (response.status === 201) {
            let data = await response.json();
            console.log(data);
            var table = document.getElementById("ListRecettes")
            //= Fonction d'affichage JSON => HTML
            for (let i in data) {
                var text = "<tr>"
                text += "<td>" + data[i].libelle + "</td>"
                        + "<td>" + data[i].description + "</td>"
                text += "</tr>";
                table.innerHTML += text;
            }
        }
    }
    fetchData();
});

/*
: ************************************************************************************************************
                                    % Méthode Ajouter recette User.
: ************************************************************************************************************
*/

//= Méthode d'écoute du formulaire.
let formCreaR = document.getElementById("creationRecette");

//? Écoute du bouton submit.
formCreaR.addEventListener("submit", function (event) {
    //= J'enlève les paramètres par défaut du formulaire.
    event.preventDefault();
    console.log("OK!");
    });

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