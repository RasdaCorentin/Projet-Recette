/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var urlCourante = document.location.href;

switch (urlCourante) {
    case "http://localhost:8080/Projet-Recette/connexion.html" :
        var urlCookie = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez/connect';
        break;
    case "http://localhost:8080/Projet-Recette/" :
        alert("index");
        break;

    case "http://localhost:8080/Projet-Recette/adminpage.html" :
        var urlCookie = "http://localhost:8080/Projet-Recette/api/utilisateur/admin/liste";
        break;

    case "http://localhost:8080/Projet-Recette/myPage.html" :
        let username = getCookie("utilisateur");
        username = username.split(":");
        var urlCookie2 = "http://localhost:8080/Projet-Recette/api/utilisateur/recette/liste/" + username[2] + "";
        break;

    default :
        alert("Nop");
        break;
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

function connectWithCookieXMLAdminPage() {
    let username = getCookie("utilisateur");
    username = username.split(":");

    if (username != "") {

        var httpCookie = new XMLHttpRequest();
        // var urlCookie = "http://localhost:8080/Projet-Recette/api/utilisateur/admin/liste";
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
                tableAdmin(dataListUserCookie);
                alert("Le cookie a marché!");
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
;

function connectWithCookieXMLConnection() {
    let username = getCookie("utilisateur");
    username = username.split(":");

    if (username != "") {
        var http = new XMLHttpRequest();
        var method = 'PUT';
        var data = {
            "utilisateur": {
                "nom": username[0],
                "password": username[1]
            }
        };
        console.log(data);
        data = JSON.stringify(data);
        requestTest(data);

        function requestTest(data) {
            http.open(method, url);
            http.setRequestHeader('Content-Type', 'application/json');
            http.onreadystatechange = function () {
                if (http.readyState === XMLHttpRequest.DONE && http.status === 202) {
                    var res = JSON.parse(http.responseText);
                    alert("Le cookie a marché! Connection Réussi");
                    console.log(res);
                } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 202) {
                    console.log("Error");
                }
            };
            http.send(data);
        }
        ;

    } else {
        console.log("no cookies");
    }
}
;

function tableAdmin(data) {
    var table = document.getElementById("ListUtilisateurs");
    var i;
    /* i est égal à 0; tant que i plus petit que myArr, relancer la boucle */
    for (i = 0; i < data.length; i++) {
        var text = "<tr>";
        text += "<td>" + data[i].nom + "</td>" + "<td>" + data[i].email + "</td>";
        text += "</tr>";
        table.innerHTML += text;
    }
};

function connectWithCookieFetchMyPage() {    
    let username = getCookie("utilisateur");
    username = username.split(":");
    
    if (username != "") {
    var headers = new Headers();
    var authBasic = username[0] + ":" + username[1];
    
    async function fetchData() {
        headers.set('Authorization', 'Basic ' + btoa(authBasic));

        let response = await fetch(urlCookie2, {method: 'GET',
            headers: headers
        });
        
        console.log(response.status); // 200
        console.log(response.statusText); // OK
        
        if (response.status === 201) {
            alert("Le cookie a marché! Connection Réussi");
            let data = await response.json();
            console.log(data);
            
            var table = document.getElementById("ListRecettes");

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
        
    } else {
        console.log("no cookies");
    }
    
}
