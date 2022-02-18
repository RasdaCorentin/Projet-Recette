let cookieUtilisateur = getCookie("utilisateur");
var urlCourante = document.location.href;
var elementsACacher = document.getElementById("elementsACacher");
var cacherConnexion = document.getElementById("cacherConnexion");
var cacherInscription = document.getElementById("cacherInscription");
var elementAMontrer = document.getElementById("elementAMontrer");

if (cookieUtilisateur != "") {
    switch (urlCourante) {

        case "http://localhost:8080/Projet-Recette/index.html" :
            elementsACacher.classList.add('hide');
            cacherConnexion.classList.add('hide');
            cacherInscription.classList.add('hide');
            elementAMontrer.classList.remove('hide');
            break;

        case "http://localhost:8080/Projet-Recette/connexion.html" :
            elementsACacher.classList.add('hide');
            cacherConnexion.classList.add('hide');
            cacherInscription.classList.add('hide');
            elementAMontrer.classList.remove('hide');
            break;
    
        case "http://localhost:8080/Projet-Recette/" :
            elementsACacher.classList.add('hide');
            elementAMontrer.classList.remove('hide');
            break;
    
        case "http://localhost:8080/Projet-Recette/adminpage.html" :
            var urlCookie = "http://localhost:8080/Projet-Recette/api/utilisateur/admin/liste";
            getCookie("utilisateur");
            connectWithCookieXML();
            elementsACacher.classList.add('hide');
            elementAMontrer.classList.remove('hide');
            break;
    
        case "http://localhost:8080/Projet-Recette/myPage.html" :
            elementsACacher.classList.add('hide');
            elementAMontrer.classList.remove('hide');
            break;
    
        default :
            alert("Il y a une erreur au niveau du système de cookie.");
            break;

    }
}

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(";");
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == " ") {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            console.log(c.substring(name.length, c.length));
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function connectWithCookieXML() {
    let username = getCookie("utilisateur");
    username = username.split(":");

    if (username != "") {

    var httpCookie = new XMLHttpRequest();
    var urlCookie = "http://localhost:8080/Projet-Recette/api/utilisateur/admin/liste";
    var authBasicCookie = username[0] + ":" + username[1];

    httpCookie.open("GET", urlCookie, true);
    httpCookie.withCredentials = true;
    httpCookie.setRequestHeader("Content-Type", "application/json");
    httpCookie.setRequestHeader("Authorization", "Basic " + btoa(authBasicCookie));
    httpCookie.onreadystatechange = function () {

        if (
            httpCookie.readyState === XMLHttpRequest.DONE &&
            httpCookie.status === 201
        ) {
            var dataListUserCookie = JSON.parse(this.responseText);
            myFunction(dataListUserCookie);
        } else if (
            httpCookie.readyState === XMLHttpRequest.DONE &&
            httpCookie.status !== 201
        ) {
            console.log("Error");
        }

    };
    httpCookie.send();
    } else {
        console.log("no cookies");
    }
}

function myFunction(data) {
    var table = document.getElementById("ListUtilisateurs");
    var i;
    /* i est égal à 0; tant que i plus petit que myArr, relancer la boucle */
    for (i = 0; i < data.length; i++) {
        var text = "<tr>";
        text += "<td>" + data[i].nom + "</td>" + "<td>" + data[i].email + "</td>";
        text += "</tr>";
        table.innerHTML += text;
    }
}