/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

/*
 
 Methode Post Formulaire Inscription
 
 */

var form = document.getElementById("myForm");
var http = new XMLHttpRequest();
var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez';
var method = 'POST';




// … et prenez en charge l'événement submit.
form.addEventListener("submit", function (event) {
    event.preventDefault();
    var data = {
        "utilisateur": {
            "nom": document.getElementById("nom").value,
            "password": document.getElementById("password").value,
            "email": document.getElementById("email").value
        }
    };
    var password = document.getElementById("password").value;
    data = JSON.stringify(data);
    requestTest(data, password);
});

function requestTest(data, password) {
    http.open(method, url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE && http.status === 201) {
            res = JSON.parse(http.responseText);
            var nom = res.nom;
            var id = res.id;
            document.cookie = "utilisateur=" + nom + ":" + password + ":" + id;
            "path=/"; "max-age=600000000"; "samesite=lax"; //$ max-age=31536000 pour une année entière.   
            alert("Cookie:" + document.cookie);
        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
            console.log("Error");
        }
    };
    http.send(data);
    alert(data);
}
;

//document.cookie = "utilisateur=Corentin:123:1"; "path=/"; "max-age=600000000"; "samesite=lax";
            