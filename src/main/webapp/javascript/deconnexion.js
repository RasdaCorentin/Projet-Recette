/*
: ************************************************************************************************************
                                    % Détruire le cookie utilisateur.
: ************************************************************************************************************
*/

function deconnexion() {
    //= Je donne une date de péremption dépassée depuis longtemps à mon cookie afin qu'il expire.
    document.cookie = "utilisateur=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    //= Puis je renvoie l'utilisateur sur la page d'accueil.
    location.href = "index.html";
}

deconnexion();