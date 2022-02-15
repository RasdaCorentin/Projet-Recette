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
