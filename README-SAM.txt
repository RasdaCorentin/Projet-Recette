FONCTIONNALITÉS UTILISATEUR :

Usecase 1 : liste utilisateurs
- v1/utilisateur/liste

Usecase 2 : Créer utilisateur
 - v1/utilisateur/create

Usecase 3 : Modifier utilisateur
- v1/utilisateur/update/(+ id)

Usecase 4 : Supprimer utilisateur
- v1/utilisateur/delete/(+ id)


FONCTIONNALITÉS RECETTE :

Usecase 1 : Création d'une recette avec id de l'utilisateur
- v1/recette/create/(+ id utilisateur)

Usecase 2 : Modification de la recette avec son id
- v1/recette/update/(+id recette)
NB : Supprime l'id utilisateur associé (corrections à venir)

Usecase 3 : Suppression de la recette avec son id
- v1/recette/delete/(+id recette)


FONCTIONNALITÉS INGREDIENT :

Usecase 1 : Liste ingrédients (avec recettes associées et utilisateurs associés)
- v1/ingredient/liste

Usecase 2 : Création d'un ingrédient
- v1/ingredient/create

Usecase 3 : Modification d'un ingredient (avec son id)
 - v1/ingredient/update/(+id ingredient)
NB : ne permet pas de modifier l'id_utilisateur associé (v2 en cours)

Usecase 4 : Suppression d'un ingrédient (avec son id)
- v1/ingredient/delete/(+id ingredient)


FONCTIONNALITÉS AUTORISATIONS :
Usecase 1 : "admin" dans l'url
- test/admin/user

Usecase 2 : Nouvel utilisateur (url "new" par défaut)
- v1/utilisateur/new


FONCTIONNALITÉS ADMIN
Usecase 1 : Liste des utilisateurs
- v1/utilisateur/admin/liste
NB : Affiche la liste des recettes avec ingredients dans la console.

Usecase 2 : Modification d'un compte utilisateur (rendre inactif, ou changer son role) avec son id
- v1/utilisateur/admin/update/(+id utilisateur concerné) 

Usecase 3 : Suppression d'un compte utilisateur (avec son id)
- v1/utilisateur/admin/delete/(+id utilisateur)

Usecase 4 : Création d'un compte utilisateur avec gestion du statut et du mode "actif"
- v1/utilisateur/admin/create-user

Usecase 5 : Création, modification et suppression d'une recette
- v1/utilisateur/admin/create(ou update, ou delete)-recette/(+id recette)
NB : créé, modifie ou supprime, mais ne persiste pas les ingredients en base (V2 en cours)

Usecase 6 : Création et modification d'ingrédient
- v1/utilisateur/admin/create(ou update)-ingredient/(+id ingredient)



