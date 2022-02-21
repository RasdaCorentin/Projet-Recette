/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/********************************
 
 Methode Fetch Index List Recette
 
 *******************************/

//Fonction fetch async/await pour l'affichage de la liste utilisateur
async function fetchRecette() {
    let responseRecette = await fetch('http://localhost:8080/Projet-Recette/api/utilisateur/recette/enregistrez/liste');

    console.log(responseRecette.status); // 200
    console.log(responseRecette.statusText); // OK

    if (responseRecette.status === 201) {
        //await attente d'une réponse serveur pour afficher la liste utilisateur
        let dataRecette = await responseRecette.json();
        var tableRecette = document.getElementById("ListRecettes");

        for (let iterRecette in dataRecette) {
            var textRecette = "<tr>";
            textRecette += "<td class='recettes'><button type='button' class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#exampleModal"+ iterRecette +"'>" + dataRecette[iterRecette].libelle + "</button></td>"+
        '<div class="modal fade" id="exampleModal'+ iterRecette +'" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
                '<div class="modal-content">'+

                    '<div class="modal-header">'+
                        '<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>'+
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
fetchRecette();
/********************************
 
 Methode Fetch Index List Ingredient
 
 *******************************/
async function fetchIngredient() {
    let responseIng = await fetch('http://localhost:8080/Projet-Recette/api/utilisateur/ingredient/enregistrez/liste');

    console.log(responseIng.status); // 200
    console.log(responseIng.statusText); // OK

    if (responseIng.status === 201) {
        //await attente d'une réponse serveur pour afficher la liste utilisateur
        let dataIng = await responseIng.json();
        var tableIng = document.getElementById("ListIng");

        for (let iterIng in dataIng) {

            var textIng = "<tr>";
            
            textIng += "<td><button type='button' class='btn btn-secondary' data-bs-toggle='modal' data-bs-target='#modalIng"+ iterIng +"'>" + dataIng[iterIng].libelle + "</button></td>"+
        '<div class="modal fade" id="modalIng'+ iterIng +'" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
                '<div class="modal-content">'+

                    '<div class="modal-header">'+
                        '<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>'+
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
fetchIngredient();
/********************************
 
 Methode Search Reactive pour Index List Recette
 
 *******************************/

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
