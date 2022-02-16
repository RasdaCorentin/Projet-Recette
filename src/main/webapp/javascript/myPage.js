/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let form = document.getElementById("myForm");

form.addEventListener("submit", function (event) {
    event.preventDefault();
    var url = 'http://localhost:8080/Projet-Recette/api/utilisateur/recette/liste/1';
    var headers = new Headers();
    var username = document.getElementById("nom").value;
    var password = document.getElementById("password").value;
    var authBasic = username + ":" + password;
    async function fetchData() {
        //headers.append('Content-Type', 'application/json');
        headers.set('Authorization', 'Basic ' + btoa(authBasic));


        let response = await fetch(url, {method: 'GET',
            headers: headers
        });
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
                table.innerHTML += text;
            }
        }
    }
    fetchData();
});