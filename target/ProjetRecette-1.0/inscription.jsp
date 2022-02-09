<%-- 
    Document   : inscription
    Created on : 8 févr. 2022, 14:50:01
    Author     : samha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page Inscription</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        
                <div class="p-5 bg-success text-white text-center">
  <h1>Hello from Recette Service Web 1.0 !</h1>
  <p>Désolé, cette interface est juste là pour la déco pour le moment !</p> 
</div>
        
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link active" href="index.html">Accueil</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/RecetteServiceWeb/connexion.jsp">Se connecter</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/RecetteServiceWeb/inscription.jsp">S'inscrire</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Forum</a>
      </li>
    </ul>
       <form class="d-flex">
        <input class="form-control me-2" type="text" placeholder="Chercher">
        <button class="btn btn-success" type="button">Trouver</button>
      </form>
  </div>
</nav>
        
        <div class="container mt-3">
  
  <form action="/action_page.php">
    <div class="mb-3 mt-3">
      <label for="nom">Nom : </label>
      <input type="text" class="form-control" id="nom" placeholder="Votre nom" name="nom">
    </div>
    <div class="mb-3 mt-3">
      <label for="prenom">Prénom :</label>
      <input type="prenom" class="form-control" id="prenom" placeholder="Votre prénom" name="prenom">
    </div>
    <div class="mb-3 mt-3">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Votre email" name="email">
    </div>
    <div class="mb-3">
      <label for="password1">Mot de passe :</label>
      <input type="password" class="form-control" id="password1" placeholder="Votre password" name="password1">
    </div>
      <div class="mb-3">
      <label for="password2">Confirmez votre mot de passe :</label>
      <input type="password" class="form-control" id="password2" placeholder="Confirmer votre password" name="password2">
    </div>
    <div class="form-check mb-3">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember"> Je confirme avoir pris connaissance des règles d'utilisation du site
      </label>
    </div>
    <button type="submit" class="btn btn-primary">Envoyer</button>
  </form>
</div>
    </body>
</html>
