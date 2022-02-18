/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

<<<<<<< Updated upstream
                        async function fetchData() {
                            let response = await fetch('http://localhost:8080/Projet-Recette/api/utilisateur/recette/enregistrez/liste');

                            console.log(response.status); // 200
                            console.log(response.statusText); // OK

                            if (response.status === 201) {
                                let data = await response.json();
                                console.log(data);
                                var table = document.getElementById("ListRecettes")
                                
                                for (let i in data) {
                                    
                                    var text = "<tr>"
                                    text += "<td>" + data[i].libelle + "</td>" 
                                          + "<td>" + data[i].description + "</td>"
                                  text += "</tr>";
                                  table.innerHTML += text
                                }
                                console.log(text);
                            }
                        }
                        fetchData();
=======
/********************************
 
 Methode Fetch Index List Recette
 
 *******************************/

//Fonction fetch async/await pour l'affichage de la liste utilisateur
async function fetchData() {
    let response = await fetch('http://localhost:8080/Projet-Recette/api/utilisateur/recette/enregistrez/liste');

    console.log(response.status); // 200
    console.log(response.statusText); // OK

    if (response.status === 201) {
        //await attente d'une réponse serveur pour afficher la liste utilisateur
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

var myModal = new bootstrap.Modal(document.getElementById('myModal'));
>>>>>>> Stashed changes
