/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

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

//* Les cookies.

function setCookie(cname, cvalue, exdays) {

    const d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    let expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";

    }

function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');

    for(let i = 0; i < ca.length; i++) {

        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }

        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }

    }

    return "";
}

function checkCookie() {

    console.log("Bonjour.");
    let user = getCookie("utilisateur");

    if ( user != "" ) {
        alert("Bienvenue " + user);
    } else {
        user = prompt("Veuillez vous identifiez : ", "");
        if ( user != "" && user != null ) {
            setCookie("utilisateur", user, 365);
        }
    }

}

checkCookie();