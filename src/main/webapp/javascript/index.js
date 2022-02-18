/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var myModal = new bootstrap.Modal(document.getElementById('myModal'));

//. --------------------Les variables pour les hide and show.--------------------
var elementsACacher = document.getElementById("elementsACacher");
var cacherConnexion = document.getElementById("cacherConnexion");
var cacherInscription = document.getElementById("cacherInscription");
var elementAMontrer = document.getElementById("elementAMontrer");

async function fetchData() {
    let response = await fetch('http://localhost:8080/Projet-Recette/api/utilisateur/recette/enregistrez/liste');

    console.log(response.status); // 200
    console.log(response.statusText); // OK

    if (response.status === 201) {
        let data = await response.json();
        console.log(data);
        var table = document.getElementById("ListRecettes");

        for (let i in data) {
            var text = "<tr>";
            text += "<td class='recettes'><button type='button' data-bs-toggle='modal' data-bs-target='#exampleModal'>" + data[i].libelle + "</button></td>";
            text += "</tr>";
            table.innerHTML += text;
        }
        console.log(text);
    }
}

fetchData();

function search_recettes() {
    let input = document.getElementById('searchbar').value;
    input = input.toLowerCase();
    let x = document.getElementsByClassName('recettes');

    for (i = 0; i < x.length; i++) {
        if (!x[i].innerHTML.toLowerCase().includes(input)) {
            x[i].style.display = "none";
        } else {
            x[i].style.display = "list-item";
        }
    }
}

//. --------------------Les hide and show.--------------------
if (cookieUtilisateur != "") {
    elementsACacher.classList.add('hide'); //: Permet de cacher le lien vers la page de connexion si l'utilisateur est déjà connecté.
    cacherConnexion.classList.add('hide'); //: Permet de cacher le lien vers la page de connexion si l'utilisateur est déjà connecté.
    cacherInscription.classList.add('hide'); //: Permet de cacher le lien vers la page d'inscription si l'utilisateur est déjà connecté.
    elementAMontrer.classList.remove('hide'); //: Permet de montré le lien vers la page de déconnexion si l'utilisateur est connecté.
}