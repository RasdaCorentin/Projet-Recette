Yoann : - Pas eu le temp sde regarder les filtres
- Cryptage update utilisateur : 
                // Je dois crypter le nouveau mot de passe avant de l'envoyer.
                String salt = BCrypt.gensalt();
                String passwordHash = BCrypt.hashpw( user.getMdp(), salt );

                utilisateurAModifier.setSalt( salt );
                utilisateurAModifier.setMdp( passwordHash);

Bon travail au niveau d'Utilisateur
Agnieska : 
-Bon message d'erreur 
-boolean closeeentityManager ???