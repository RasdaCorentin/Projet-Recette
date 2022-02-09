<%-- 
    Document   : connexion
    Created on : 8 févr. 2022, 14:49:48
    Author     : samha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page Connexion</title>
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
    
  <form action="/action_page.php" class="was-validated">
    <div class="mb-3 mt-3">
      <label for="uname" class="form-label">Username:</label>
      <input type="text" class="form-control" id="uname" placeholder="Enter username" name="uname" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="mb-3">
      <label for="pwd" class="form-label">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="form-check mb-3">
      <input class="form-check-input" type="checkbox" id="myCheck"  name="remember" required>
      <label class="form-check-label" for="myCheck">I agree on blabla.</label>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Check this checkbox to continue.</div>
    </div>
  <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>

    </body>
</html>
