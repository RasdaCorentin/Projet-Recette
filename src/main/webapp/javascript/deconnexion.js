function deconnexion() {
    document.cookie = "utilisateur=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    location.href = "index.html";
}

deconnexion();