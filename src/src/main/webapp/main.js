/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

/*

Methode Post Formulaire Inscription

*/

                // Accédez à l'élément form …
                var form = document.getElementById("myForm");

                // … et prenez en charge l'événement submit.
                form.addEventListener("submit", function (event) {
                    event.preventDefault();
                    data;
                    requestTest();
                });
                //            var element = document.getElementById("enregistrez");
                //            element.addEventListener("click", requestTest);
                var http = new XMLHttpRequest();
                var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez';
                var method = 'POST';
                var data = {
                    "utilisateur": {
                        "nom": document.getElementById("nom").value,
                        "password": document.getElementById("password").value,
                        "email": document.getElementById("email").value
                    }
                };
                data = JSON.stringify(data);

                function requestTest() {
                    http.open(method, url);
                    http.setRequestHeader('Content-Type', 'application/json');
                    http.onreadystatechange = function () {
                        if (http.readyState === XMLHttpRequest.DONE && http.status === 201) {
                            console.log(http.responseText);
                        } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
                            console.log("Error");
                        }
                    };
                    http.send(data);
                    alert(data);
                }