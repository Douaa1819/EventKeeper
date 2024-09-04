# EventKeeper

🎉 Projet EventKeeper 🎉
EventKeeper est une application basée sur Java conçue pour gérer divers événements, participants et inscriptions. Le projet est structuré avec une séparation claire entre l'accès aux données, la logique métier, et l'interface utilisateur.

🌟 Fonctionnalités
📅 Gérer les Événements: Ajouter, modifier, supprimer, lister, et rechercher des événements.
ajouterEvenement()
modifierEvenement()
supprimerEvenement()
listerEvenements()
rechercherEvenementsParCritere()
👥 Gérer les Participants: Ajouter, modifier, supprimer, et lister les participants.
ajouterParticipant()
modifierParticipant()
supprimerParticipant()
listerParticipants()
📝 Gérer les Inscriptions: Inscrire et désinscrire des participants aux événements, afficher les inscriptions.
inscrireParticipant()
desinscrireParticipant()
afficherInscriptionsEvenement()
afficherEvenementsPourParticipant()
🗂️ Structure du Projet
Le projet est organisé en packages sous src/main/java/com/eventkeeper/ :

dao/: Objets d'accès aux données pour gérer les opérations sur la base de données.
models/: Modèles représentant les entités.
services/: Logique métier et services.
utils/: Classes utilitaires.
view/: Vues de l'interface utilisateur.
🚀 Comment Exécuter
Compilez le projet en utilisant un compilateur Java (JDK 17 recommandé).
Exécutez le fichier Main.java pour démarrer l'application.
📋 Exigences
Java 17 ou version ultérieure.
📄 Licence
Ce projet est sous licence MIT.

📊 Gestion des Événements
Ajouter un Événement (ajouterEvenement)

Description: Permettre à l'administrateur de créer un nouvel événement en fournissant les informations suivantes :
Titre de l'événement
Description de l'événement
Date de l'événement
Lieu de l'événement
Type d'événement
Processus:
L'utilisateur entre les détails de l'événement.
Le système valide les entrées.
L'événement est ajouté à la base de données et un message de succès est affiché.
Modifier un Événement (modifierEvenement)

Description: Permettre à l'administrateur de mettre à jour les informations d'un événement existant.
Processus:
L'utilisateur sélectionne l'événement à modifier en utilisant son ID.
Le système affiche les informations actuelles de l'événement.
L'utilisateur modifie les champs nécessaires.
Les modifications sont enregistrées dans la base de données.
Supprimer un Événement (supprimerEvenement)

Description: Permettre à l'administrateur de supprimer un événement existant.
Processus:
L'utilisateur sélectionne l'événement à supprimer en utilisant son ID.
Le système supprime l'événement de la base de données.
Un message de confirmation est affiché.
Afficher la Liste des Événements (listerEvenements)

Description: Afficher tous les événements existants dans la base de données.
Processus:
L'utilisateur choisit l'option pour lister tous les événements.
Le système récupère et affiche les événements dans la console.
Rechercher des Événements par Critère (rechercherEvenementsParCritere)

Description: Permettre à l'administrateur de rechercher des événements en fonction de critères spécifiques (date, lieu, type).
Processus:
L'utilisateur sélectionne un critère de recherche.
Le système affiche les événements correspondant au critère sélectionné.
👥 Gestion des Participants
Ajouter un Participant (ajouterParticipant)

Description: Permettre à l'administrateur d'ajouter un participant.
Processus:
L'utilisateur entre les détails du participant (prénom, nom de famille, email, numéro de téléphone).
Le système enregistre le participant dans la base de données.
Modifier les Détails d'un Participant (modifierParticipant)

Description: Permettre à l'administrateur de mettre à jour les informations d'un participant.
Processus:
L'utilisateur sélectionne le participant à modifier en utilisant son ID.
Le système affiche les informations actuelles du participant.
L'utilisateur modifie les champs nécessaires.
Les modifications sont enregistrées dans la base de données.
Supprimer un Participant (supprimerParticipant)

Description: Permettre à l'administrateur de supprimer un participant.
Processus:
L'utilisateur sélectionne le participant à supprimer en utilisant son ID.
Le système supprime le participant de la base de données.
Un message de confirmation est affiché.
Afficher la Liste des Participants (listerParticipants)

Description: Afficher tous les participants enregistrés dans la base de données.
Processus:
L'utilisateur choisit l'option pour lister tous les participants.
Le système récupère et affiche les participants dans la console.
📝 Gestion des Inscriptions
Inscrire un Participant à un Événement (inscrireParticipant)

Description: Permettre à l'administrateur d'inscrire un participant à un événement spécifique.
Processus:
L'utilisateur sélectionne l'événement et le participant.
Le système enregistre l'inscription.
Désinscrire un Participant d'un Événement (desinscrireParticipant)

Description: Permettre à l'administrateur de désinscrire un participant d'un événement.
Processus:
L'utilisateur sélectionne l'inscription à supprimer.
Le système supprime l'inscription de la base de données.
Afficher les Inscriptions d'un Événement (afficherInscriptionsEvenement)

Description: Afficher tous les participants inscrits à un événement spécifique.
Processus:
L'utilisateur sélectionne l'événement.
Le système affiche les participants inscrits à cet événement.
Afficher les Événements auxquels un Participant est Inscrit (afficherEvenementsPourParticipant)

Description: Permettre à l'administrateur de voir tous les événements auxquels un participant est inscrit.
Processus:
L'utilisateur sélectionne le participant.
Le système affiche les événements associés à ce participant.
🎨 Interface Utilisateur
Menu Administrateur (EventsView.java)
Description: Interface principale pour gérer les événements, participants et inscriptions.
Fonctionnalités:
Ajouter, modifier, supprimer, lister et rechercher des événements.
Accès à la gestion des participants et des inscriptions.
Génération de rapports.
Menu Participant (ParticipantView.java)
Description: Interface dédiée à la gestion des participants.
Fonctionnalités:
Ajouter, modifier, supprimer, et lister les participants.
Retour au menu principal.
