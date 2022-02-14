/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*

Methode Get List Utilisateur

*/
//var form = document.getElementById("myForm");
// form.addEventListener("submit", function (event) {
//            event.preventDefault();
//            var http = new XMLHttpRequest();
//            var url = "http://localhost:8080/Projet-Recette/api/utilisateur/admin/liste";
//            var username = document.getElementById("nom").value;
//            var password = document.getElementById("password").value;
//            var authBasic = username + ":" + password;
//            
//            http.open("GET", url, true);
//            http.withCredentials= true;
//            http.setRequestHeader('Content-Type', 'application/json');
//            http.setRequestHeader("Authorization", "Basic " + btoa(authBasic));
//            http.onreadystatechange = function () {
//                if (http.readyState === XMLHttpRequest.DONE && http.status === 201) {
//                    var myArr = this.responseText;
//                    myFunction(myArr);
//                } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 201) {
//                    console.log("Error");
//                }
//            };
//            http.send();
//            alert(authBasic);
//            function myFunction(arr) {
//                var out = "";
//                var i;
//                /* i est égal à 0; tant que i plus petit que myArr, relancer la boucle */
//                for (i = 0; i < arr.length; i++) {
//                    out += '<li>' + "Id = " + arr[i].id + '</br>'
//                            + "Email = " + arr[i].email + '</br>' +
//                            "Username = " + arr[i].nom + '</li>';
//                }
//                document.getElementById("listeUtilisateurs").innerHTML = out;
//            }
//            });