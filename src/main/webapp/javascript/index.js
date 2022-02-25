/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
: ************************************************************************************************************
                                    % Méthode Fetch Index List Recette
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
        £ Fonction fetch async/await pour l'affichage de la liste des recettes.
. --------------------------------------------------------------------------------
*/

async function fetchRecette() {
    let responseRecette = await fetch('http://localhost:8080/Projet-Recette/api/utilisateur/recette/enregistrez/liste');
    if (responseRecette.status === 201) {

        /*
        . --------------------------------------------------------------------------------
                                    £ Définitions des variables.
        . --------------------------------------------------------------------------------
        */

        var tableRecette = document.getElementById("ListRecettes");

        //§ await va attendre une réponse du serveur avant d'afficher la liste des recettes.
        let dataRecette = await responseRecette.json();

        /*
        . --------------------------------------------------------------------------------
                                    £ Afficher la liste des recettes sur la page.
        . --------------------------------------------------------------------------------
        */

        for (let iterRecette in dataRecette) {
            var textRecette = "<tr>";
            textRecette += "<td class='recettes'><button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#exampleModal"+ iterRecette +"'>" + dataRecette[iterRecette].libelle + "</button></td>"+
        '<div class="modal fade" id="exampleModal'+ iterRecette +'" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
                '<div class="modal-content">'+

                    '<div class="modal-header">'+
                        '<h5 class="modal-title" id="exampleModalLabel">'+ dataRecette[iterRecette].libelle +'</h5>'+
                        '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>'+
                    '</div>'+

                    '<div class="modal-body">'+
                        '<p id="descR">' + dataRecette[iterRecette].description + '</p>'+
                    '</div>'+

                    '<div class="modal-footer">'+
                        '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>'+
                        '<button type="button" class="btn btn-primary">Save changes</button>'+
                    '</div>'+

                '</div>'+
            '</div>'+
        '</div>';
            textRecette += "</tr>";
            tableRecette.innerHTML += textRecette;
        }

    }
}

/*
: ************************************************************************************************************
                                    % Méthode Fetch Index List Ingredient
: ************************************************************************************************************
*/

/*
. --------------------------------------------------------------------------------
        £ Fonction fetch async/await pour l'affichage de la liste des recettes.
. --------------------------------------------------------------------------------
*/

async function fetchIngredient() {

let responseIng = await fetch('http://localhost:8080/Projet-Recette/api/utilisateur/ingredient/enregistrez/liste');

    if (responseIng.status === 201) {

        /*
        . --------------------------------------------------------------------------------
                                    £ Définitions des variables.
        . --------------------------------------------------------------------------------
        */

        var tableIng = document.getElementById("ListIng");

        //§ await va attendre une réponse du serveur avant d'afficher la liste des ingrédients.
        let dataIng = await responseIng.json();

        /*
        . --------------------------------------------------------------------------------
                                    £ Afficher la liste des ingrédients sur la page.
        . --------------------------------------------------------------------------------
        */

        for (let iterIng in dataIng) {

            var textIng = "<tr>";

            textIng += "<td class='ingredients'><button type='button' class='btn btn-secondary' data-bs-toggle='modal' data-bs-target='#modalIng"+ iterIng +"'>" + dataIng[iterIng].libelle + "</button></td>"+
        '<div class="modal fade" id="modalIng'+ iterIng +'" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
                '<div class="modal-content">'+

                    '<div class="modal-header">'+
                        '<h5 class="modal-title" id="exampleModalLabel">'+ dataIng[iterIng].libelle +'</h5>'+
                        '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>'+
                    '</div>'+

                    '<div class="modal-body">'+
                        '<p id="descR">' + dataIng[iterIng].quantite + '</p>'+
                    '</div>'+

                    '<div class="modal-footer">'+
                        '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>'+
                        '<button type="button" class="btn btn-primary">Save changes</button>'+
                    '</div>'+

                '</div>'+
            '</div>'+
        '</div>';

            textIng += "</tr>";
            tableIng.innerHTML += textIng;

        }
    }
}

/*
: ************************************************************************************************************
                                    % Méthode Search Reactive pour Index List Recette
: ************************************************************************************************************
*/

function search_recettes() {

    /*
    . --------------------------------------------------------------------------------
                                £ Définitions des variables.
    . --------------------------------------------------------------------------------
    */

    //. --------------------La barre de recherche.--------------------
    let input = document.getElementById('searchbar').value;

    //. --------------------Transforme le texte de la barre de recherche en lettres minuscules.--------------------
    input = input.toLowerCase();

    //. --------------------Les recettes.--------------------
    let x = document.getElementsByClassName('recettes');

    for (i = 0; i < x.length; i++) {
        if (!x[i].innerHTML.toLowerCase().includes(input)) {
            x[i].style.display = "none";
        } else {
            x[i].style.display = "list-item";
        }
    }
}

/**
 
 Methode Search Réactive pour Index Liste Ingrédients
 
 ***/

function search_ingredients(){
    let input = document.getElementById('searchbar2').value;
    input = input.toLowerCase();
    let x = document.getElementsByClassName('ingredients');

    for (i = 0; i < x.length; i++) {
        if (!x[i].innerHTML.toLowerCase().includes(input)) {
            x[i].style.display = "none";
        } else {
            x[i].style.display = "list-item";
        }
    }
}

/*
: ************************************************************************************************************
                                    % Appel des fonctions
: ************************************************************************************************************
*/

//. --------------------Afficher la liste des recettes.--------------------
fetchRecette();

//. --------------------Afficher la liste des ingrédients.--------------------
fetchIngredient();