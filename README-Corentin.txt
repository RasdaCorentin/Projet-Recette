Persistence : 
<persistence-unit name="projet-recette" transaction-type="RESOURCE_LOCAL">
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/projet?zeroDateTimeBehavior=CONVERT_TO_NULL"/>

Environement:
{{http://localhost:8080/ProjetRecette/api/}}
(Pour avoir un admin, passez manuellement dans phpMyadmin) le rôle de l'utilisateur créer de 1 à 0.
End Point:

New User(No Auth):

	Créer: 
-{{url_projet}}utilisateur/enregistrez
Exemple postman:
{
    "nom" : "Mousse",
    "password" : "123",
    "email" : "Tache@gmail.com"
}

////////////////////////////////
	Connect:
-{{url_projet}}utilisateur/enregistrez/connect
Exemple postman:
{
    "utilisateur" : {
            "nom" : "Mousse",
            "password" : "123",
            "email" : "Tache@gmail.com"
    }
}


Admin:
	Liste Utilisateur:
-{{url_projet}}utilisateur/admin/liste
Exemple postman:

	Supprimer Utilisateur:
-{{url_projet}}utilisateur/admin/deleteU/5
Exemple postman: !!! Impossible de supprimer un utilisateur lié à une recette !!!

	Deconnecter Utilisateur:
-{{url_projet}}utilisateur/admin/disconnect/1
Exemple postman: !!! Modifie le mot de passe de l'utilisateur le rendant inutilisable
{
    "password" : "12345"
}
	Liste Recette:
-{{url_projet}}utilisateur/recette/admin/liste
Exemple postman:


User:
	Update Utilisateur:
-{{url_projet}}utilisateur/user/update
Exemple postman:
{
    "utilisateur" : {
        "nom" : "cupidon",
        "newNom" : "DarkSasuke",
        "email" : "rasengan@gmail.com",
        "password" : "12345"
    }
}

	Create Recette:
-{{url_projet}}utilisateur/recette/create
Exemple postman: 
{
    "recette" : {
        "libelle" : "Sushi",
        "description" : "Repas à base de poisson et de riz",
        "listeIngredients" : [
            {
                "libelle" : "Algues séché",
                "quantite" : "1"
            },
            {
                "libelle" : "Thon en tranche",
                "quantite" : "1"
            }
        ]
    },
    "utilisateur" : {
        "nom" : "Mousse"
    }
}

	Read Recette:
-{{url_projet}}utilisateur/recette/read/1
Exemple postman:
