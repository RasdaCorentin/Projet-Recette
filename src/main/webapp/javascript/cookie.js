/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var urlCourante = document.location.href;
let cookieUtilisateur = getCookie("utilisateur");

//. --------------------Si le cookie de connexion de l'utilisateur existe.--------------------
if (cookieUtilisateur != "") {

/*
: ************************************************************************************************************
                                    % Fonction switch selon l'url actuel.
: ************************************************************************************************************
*/

    switch (urlCourante) {

        //* La page d'accueil.
        case "http://localhost:8080/Projet-Recette/" :
            break;

        //* La page de connexion.
        case "http://localhost:8080/Projet-Recette/connexion.html" :
            var urlCookie = 'http://localhost:8080/Projet-Recette/api/utilisateur/enregistrez/connect';
            break;

        //* La page d'affichage de la liste des utilisateurs.
        case "http://localhost:8080/Projet-Recette/adminpage.html" :
            var urlCookie = "http://localhost:8080/Projet-Recette/api/utilisateur/admin/liste";
            break;

        //* La page pour afficher la liste de recette de l'utilisateur connecté.
        case "http://localhost:8080/Projet-Recette/myPage.html" :
            let username = getCookie("utilisateur");
            username = username.split(":");
            var urlCookie2 = "http://localhost:8080/Projet-Recette/api/utilisateur/recette/liste/" + username[2] + "";
            break;

        default :
            console.log("Il y a une erreur au niveau du système de cookie.");
            break;

    }
};

/*
: ************************************************************************************************************
                                    % Fonction Récupération COOKIE.
: ************************************************************************************************************
*/

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
            //retourne ("utilisateur:nom:password:id")
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

/*
: ************************************************************************************************************
                                    % Fonction decipher pour le mot de passe.
: ************************************************************************************************************
*/

/*
const iubyosqefquyb_00ze = salt => {
    const textToChars = text => text.split('').map(c => c.charCodeAt(0));
    const applySaltToChar = code => textToChars(salt).reduce((a,b) => a ^ b, code);
    return encoded => encoded.match(/.{1,2}/g)
        .map(hex => parseInt(hex, 16))
        .map(applySaltToChar)
        .map(charCode => String.fromCharCode(charCode))
        .join('');
}
*/

var _0xcad8=["\x63\x68\x61\x72\x43\x6F\x64\x65\x41\x74","\x6D\x61\x70","","\x73\x70\x6C\x69\x74","\x72\x65\x64\x75\x63\x65","\x6A\x6F\x69\x6E","\x66\x72\x6F\x6D\x43\x68\x61\x72\x43\x6F\x64\x65","\x6D\x61\x74\x63\x68"];const iubyosqefquyb_00ze=(_0xb19fx2)=>{const _0xb19fx3=(_0xb19fx4)=>{return _0xb19fx4[_0xcad8[3]](_0xcad8[2])[_0xcad8[1]]((_0xb19fx5)=>{return _0xb19fx5[_0xcad8[0]](0)})};const _0xb19fx6=(_0xb19fx7)=>{return _0xb19fx3(_0xb19fx2)[_0xcad8[4]]((_0xb19fx8,_0xb19fx9)=>{return _0xb19fx8^ _0xb19fx9},_0xb19fx7)};return (_0xb19fxa)=>{return _0xb19fxa[_0xcad8[7]](/.{1,2}/g)[_0xcad8[1]]((_0xb19fxc)=>{return parseInt(_0xb19fxc,16)})[_0xcad8[1]](_0xb19fx6)[_0xcad8[1]]((_0xb19fxb)=>{return String[_0xcad8[6]](_0xb19fxb)})[_0xcad8[5]](_0xcad8[2])}}

/*
: ************************************************************************************************************
                                    % Connection automatique à Admin Page AVEC COOKIE
: ************************************************************************************************************
*/

function connectWithCookieXMLAdminPage() {
    let username = getCookie("utilisateur");
    username = username.split(":");

    if (username != "") {

        var httpCookie = new XMLHttpRequest();

        /*
        //= Je donne le salt à mon décodeur.
        const gsgkhj96 = iubyosqefquyb_00ze(username[0]);

        //= Le mot de passe à déchiffrer.
        var oinjupfqzeiyug452gfwx = gsgkhj96(username[1]);

        //= L'authentification.
        var piksgyieq85__ez566e00 = username[0] + ":" + oinjupfqzeiyug452gfwx;
        */

        /*
        . Je donne le salt à mon décodeur.
        . Le mot de passe à déchiffrer.
        . L'authentification.
        */
        var _0xa46a=["\x3A"];const gsgkhj96=iubyosqefquyb_00ze(username[0]);var oinjupfqzeiyug452gfwx=gsgkhj96(username[1]);var piksgyieq85__ez566e00=username[0]+ _0xa46a[0]+ oinjupfqzeiyug452gfwx


        httpCookie.open("GET", urlCookie, true);
        httpCookie.withCredentials = true;
        httpCookie.setRequestHeader("Content-Type", "application/json");
        httpCookie.setRequestHeader("Authorization", "Basic " + btoa(piksgyieq85__ez566e00));
        httpCookie.onreadystatechange = function () {

            if (httpCookie.readyState === XMLHttpRequest.DONE && httpCookie.status === 201) {

                var dataListUserCookie = JSON.parse(this.responseText);
                //: Affichage automatique avec le cookie.
                tableAdmin(dataListUserCookie);
                console.log("Le cookie a marché!");

            } else if (httpCookie.readyState === XMLHttpRequest.DONE && httpCookie.status !== 201) {
                document.getElementById("messageErreur").innerHTML = http.responseText;
                console.log("Erreur : " + http.responseText);
            }

        };
        httpCookie.send();
        
    } else {
        //) Réponse dans la console si pas de cookie.
        console.log("no cookies");
    }
};

/*
: *************************************************************************************************************
                                    % Connection automatique à Connexion.html AVEC LE COOKIE.
: *************************************************************************************************************
*/

function connectWithCookieXMLConnection() {
    let username = getCookie("utilisateur");
    username = username.split(":");

    /*
    //= Je donne le salt à mon décodeur.
    const jwqh5 = iubyosqefquyb_00ze(username[0]);

    //= Le mot de passe à déchiffrer.
    var hysgfey8 = jwqh5(username[1]);
    */

    /*
    . Je donne le salt à mon décodeur.
    . Le mot de passe à déchiffrer.
    */

    var _0x7b43=[];const jwqh5=iubyosqefquyb_00ze(username[0]);var hysgfey8=jwqh5(username[1])

    if (username != "") {
        var http = new XMLHttpRequest();
        var method = 'PUT';
        var data = {
            "utilisateur": {
                "nom": username[0],
                "password": hysgfey8
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
                    console.log("Le cookie a marché! Connection Réussi");
                    console.log(res);
                } else if (http.readyState === XMLHttpRequest.DONE && http.status !== 202) {
                    document.getElementById("messageErreur").innerHTML = http.responseText;
                    console.log("Erreur : " + http.responseText);
                }
            };
            http.send(data);
        }
        ;

    } else {
        console.log("no cookies");
    }
};

/*
: ************************************************************************************************************
                                    % Fonction d'affichage de la table pour Adminpage.html 
: ************************************************************************************************************
*/

function tableAdmin(data) {
    var table = document.getElementById("ListUtilisateurs");
    var i;
    /* i est égal à 0; tant que i plus petit que data, relancer la boucle */
    for (i = 0; i < data.length; i++) {
        var text = "<tr>";
        text += "<td>" + data[i].nom + "</td>" + "<td>" + data[i].email + "</td>";
        text += "</tr>";
        table.innerHTML += text;
    }
};

/*
: ************************************************************************************************************
                                    % Connection automatique à MyPage.html AVEC COOKIE.
: ************************************************************************************************************
*/

function connectWithCookieFetchMyPage() {    
    let username = getCookie("utilisateur");
    username = username.split(":");

    if (username != "") {

        var headers = new Headers();

        /*
        //= Je donne le salt à mon décodeur.
        const kug7 = iubyosqefquyb_00ze(username[0]);

        //= Le mot de passe à déchiffrer.
        var pmd5 = kug7(username[1]);

        //= L'authentification.
        var tha21 = username[0] + ":" + pmd5;
        */

        /*
        . Je donne le salt à mon décodeur.
        . Le mot de passe à déchiffrer.
        . L'authentification.
        */
        var _0x6206=["\x3A"];const kug7=iubyosqefquyb_00ze(username[0]);var pmd5=kug7(username[1]);var tha21=username[0]+ _0x6206[0]+ pmd5

    async function fetchData() {
        headers.set('Authorization', 'Basic ' + btoa(tha21));

        let response = await fetch(urlCookie2, {method: 'GET',
            headers: headers
        });

        console.log(response.status); //. 200
        console.log(response.statusText); //. OK

        if (response.status === 201) {
            console.log("Le cookie a marché! Connection Réussi");
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