package com.eventkeeper.menu;

import com.eventkeeper.Main;
import com.eventkeeper.models.Event;
import com.eventkeeper.models.Participant;
import com.eventkeeper.models.Registration;
import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;
import com.eventkeeper.services.RegistrationService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private final EventService eventService;
    private final ParticipantService participantService;
    private final RegistrationService registrationService;
    private final Scanner scanner;


    public MenuConsole(EventService eventService, ParticipantService participantService, RegistrationService registrationService, Scanner scanner) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.registrationService = registrationService;
        this.scanner = scanner;
    }

    public void startAdminMenu() {
        while (true) {
            System.out.println("\nMenu Administrateur:");
            System.out.println("1. Ajouter un événement");
            System.out.println("2. Modifier un événement");
            System.out.println("3. Supprimer un événement");
            System.out.println("4. Lister tous les événements");
            System.out.println("5. Rechercher des événements");
            System.out.println("6. Gérer les participants");
            System.out.println("7. Gérer les inscriptions");
            System.out.println("8. Quitter");
            System.out.print("Choisissez une option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    updateEvent();
                    break;
                case 3:
                    deleteEvent();
                    break;
                case 4:
                    listAllEvents();
                    break;
                case 5:
                    searchEvents();
                    break;
                case 6:
                    manageParticipants();
                    break;
                case 7:
                    manageRegistrations();
                    break;
                case 8:
                    System.out.println("Vous quittez le menu Administrateur.");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }


    // Method to start the participant menu
    public void startParticipantMenu() {
        while (true) {
            System.out.println("\nMenu Participant:");
            System.out.println("1. Voir tous les événements");
            System.out.println("2. S'inscrire à un événement");
            System.out.println("3. Voir mes inscriptions");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    listAllEvents();
                    break;
                case 2:
                    registerForEvent();
                    break;
                case 3:
                    viewMyRegistrations();
                    break;
                case 4:
                    System.out.println("Vous quittez le menu Participant.");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private void registerForEvent() {
        try {

            System.out.print("Entrez votre ID de participant: ");
            int participantId = getIntInput();
            System.out.print("Entrez l'ID de l'événement auquel vous voulez vous inscrire: ");
            int eventId = getIntInput();
            registrationService.registerParticipant(eventId, participantId);
            System.out.println("Inscription réussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription: " + e.getMessage());
        }
    }

    private void viewMyRegistrations() {
        try {
            System.out.print("Entrez votre ID de participant: ");
            int participantId = getIntInput();

            List<Registration> registrations = registrationService.getRegistrationsByParticipantId(participantId);
            if (registrations.isEmpty()) {
                System.out.println("Aucune inscription trouvée pour ce participant.");
            } else {
                System.out.println("Vos inscriptions:");
                for (Registration registration : registrations) {
                    // Fetch and display event details
                    Event event = eventService.getEventById(registration.getEventId());
                    System.out.println("Événement: " + event);

                    // Display registration details
                    System.out.println("Détails de l'inscription: " + registration);
                    System.out.println(); // Blank line for readability
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage des inscriptions: " + e.getMessage());
        }
    }

    // Helper method to safely get integer input
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.print("Entrée invalide. Veuillez saisir un nombre entier: ");
            }
        }
    }

    //Gestion des participant
    private void manageParticipants() {
        while (true) {
            System.out.println("\nGestion des Participants:");
            System.out.println("1. Ajouter un participant");
            System.out.println("2. Modifier un participant");
            System.out.println("3. Supprimer un participant");
            System.out.println("4. Afficher la liste des participants");
            System.out.println("5. Retour au menu principal");
            System.out.print("Choisissez une option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addParticipant();
                    break;
                case 2:
                    updateParticipant();
                    break;
                case 3:
                    deleteParticipant();
                    break;
                case 4:
                    listParticipants();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }






    private void manageRegistrations() {
        while (true) {
            System.out.println("\nGestion des Inscriptions:");
            System.out.println("1. Inscrire un participant à un événement");
            System.out.println("2. Désinscrire un participant d'un événement");
            System.out.println("3. Voir les inscriptions par événement");
            System.out.println("4. Voir les inscriptions par participant");
            System.out.println("5. Retour au menu principal");
            System.out.print("Choisissez une option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    inscrireParticipant();
                    break;
                case 2:
                    desinscrireParticipant();
                    break;
                case 3:
                    voirInscriptionsParEvenement();
                    break;
                case 4:
                    voirInscriptionsParParticipant();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private void inscrireParticipant() {
        try {
            System.out.print("Entrez l'ID du participant: ");
            int participantId = getIntInput();
            System.out.print("Entrez l'ID de l'événement: ");
            int eventId = getIntInput();

            registrationService.registerParticipant(eventId, participantId);
            System.out.println("Inscription réussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription: " + e.getMessage());
        }
    }

    private void desinscrireParticipant() {
        try {
            System.out.print("Entrez l'ID de l'événement: ");
            int eventId = getIntInput();
            System.out.print("Entrez l'ID du participant: ");
            int participantId = getIntInput();

            registrationService.unregisterParticipant(eventId, participantId);
            System.out.println("Désinscription réussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la désinscription: " + e.getMessage());
        }
    }

    private void voirInscriptionsParEvenement() {
        try {
            System.out.print("Entrez l'ID de l'événement: ");
            int eventId = getIntInput();

            List<Registration> registrations = registrationService.getRegistrationsByEventId(eventId);
            if (registrations.isEmpty()) {
                System.out.println("Aucune inscription trouvée pour cet événement.");
            } else {
                System.out.println("Inscriptions pour l'événement ID " + eventId + ":");
                for (Registration registration : registrations) {
                    // Fetch and display event details
                    Event event = eventService.getEventById(eventId);
                    System.out.println("Événement: " + event);

                    // Fetch and display participant details
                    Participant participant = participantService.getParticipantById(registration.getParticipantId());
                    System.out.println("Participant: " + participant);

                    System.out.println("Détails de l'inscription: " + registration);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage des inscriptions: " + e.getMessage());
        }
    }

    private void voirInscriptionsParParticipant() {
        try {
            System.out.print("Entrez l'ID du participant: ");
            int participantId = getIntInput();

            List<Registration> registrations = registrationService.getRegistrationsByParticipantId(participantId);
            if (registrations.isEmpty()) {
                System.out.println("Aucune inscription trouvée pour ce participant.");
            } else {
                System.out.println("Inscriptions pour le participant ID " + participantId + ":");
                for (Registration registration : registrations) {
                    // Fetch and display event details
                    Event event = eventService.getEventById(registration.getEventId());
                    System.out.println("Événement: " + event);

                    // Fetch and display participant details
                    Participant participant = participantService.getParticipantById(participantId);
                    System.out.println("Participant: " + participant);

                    // Display registration details
                    System.out.println("Détails de l'inscription: " + registration);
                    System.out.println(); // Blank line for readability
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage des inscriptions: " + e.getMessage());
        }
    }


    private void addParticipant() {
        System.out.println("Ajouter un participant");
        System.out.print("Entrez le prénom: ");
        String firstName = scanner.nextLine();
        System.out.print("Entrez le nom de famille: ");
        String lastName = scanner.nextLine();
        System.out.print("Entrez l'email: ");
        String email = scanner.nextLine();
        System.out.print("Entrez le numéro de téléphone: ");
        String phoneNumber = scanner.nextLine();
        Participant participant = new Participant(1, firstName, lastName, email, phoneNumber);
        participantService.addParticipant(participant);
        System.out.println("Participant ajouté avec succès.");
    }

    private void updateParticipant() {
        System.out.println("Modifier un participant");
        System.out.print("Entrez l'ID du participant à modifier: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Participant participant = participantService.getParticipantById(id);
        if (participant != null) {
            System.out.print("Entrez le nouveau prénom (laisser vide pour ne pas modifier): ");
            String firstName = scanner.nextLine();
            System.out.print("Entrez le nouveau nom de famille (laisser vide pour ne pas modifier): ");
            String lastName = scanner.nextLine();
            System.out.print("Entrez le nouvel email (laisser vide pour ne pas modifier): ");
            String email = scanner.nextLine();
            System.out.print("Entrez le nouveau numéro de téléphone (laisser vide pour ne pas modifier): ");
            String phoneNumber = scanner.nextLine();

            if (!firstName.isEmpty()) participant.setFirstName(firstName);
            if (!lastName.isEmpty()) participant.setLastName(lastName);
            if (!email.isEmpty()) participant.setEmail(email);
            if (!phoneNumber.isEmpty()) participant.setPhoneNumber(phoneNumber);

            participantService.updateParticipant(participant);
            System.out.println("Participant mis à jour avec succès.");
        } else {
            System.out.println("Participant non trouvé.");
        }
    }

    private void deleteParticipant() {
        System.out.println("Supprimer un participant");
        System.out.print("Entrez l'ID du participant à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        participantService.deleteParticipant(id);
        System.out.println("Participant supprimé avec succès.");
    }

    private void listParticipants() {
        System.out.println("Liste des participants:");
        for (Participant participant : participantService.getAllParticipants()) {
            System.out.println(participant);
        }
    }

    private void addEvent() {
        try {
            System.out.print("Entrez le titre de l'événement: ");
            String title = scanner.nextLine();
            System.out.print("Entrez la description de l'événement: ");
            String description = scanner.nextLine();

            System.out.print("Entrez la date de l'événement (dd-MM-yyyy): ");
            String dateString = scanner.nextLine();
            Date date = convertStringToDate(dateString); // Convert string to date

            System.out.print("Entrez le lieu de l'événement: ");
            String location = scanner.nextLine();

            System.out.print("Entrez le type d'événement: ");
            String type = scanner.nextLine();

            Event event = new Event(title, description, date, location, type);
            eventService.createEvent(event);
            System.out.println("Événement ajouté avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de l'événement: " + e.getMessage());
        }
    }

    private void updateEvent() {
        try {
            System.out.print("Entrez l'ID de l'événement à modifier: ");
            int eventId = getIntInput();

            Event existingEvent = eventService.getEventById(eventId);
            if (existingEvent == null) {
                System.out.println("Événement non trouvé.");
                return;
            }

            System.out.print("Entrez le nouveau titre (laissez vide pour ne pas changer): ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) {
                existingEvent.setTitle(newTitle);
            }

            System.out.print("Entrez la nouvelle description (laissez vide pour ne pas changer): ");
            String newDescription = scanner.nextLine();
            if (!newDescription.isEmpty()) {
                existingEvent.setDescription(newDescription);
            }

            System.out.print("Entrez la nouvelle date (dd-MM-yyyy) (laissez vide pour ne pas changer): ");
            String newDateString = scanner.nextLine();
            if (!newDateString.isEmpty()) {
                Date newDate = convertStringToDate(newDateString);
                existingEvent.setDate(newDate);
            }

            System.out.print("Entrez le nouveau lieu (laissez vide pour ne pas changer): ");
            String newLocation = scanner.nextLine();
            if (!newLocation.isEmpty()) {
                existingEvent.setLocation(newLocation);
            }

            System.out.print("Entrez le nouveau type (laissez vide pour ne pas changer): ");
            String newType = scanner.nextLine();
            if (!newType.isEmpty()) {
                existingEvent.setType(newType);
            }

            eventService.updateEvent(existingEvent);
            System.out.println("Événement mis à jour avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour de l'événement: " + e.getMessage());
        }
    }

    private void deleteEvent() {
        try {
            System.out.print("Entrez l'ID de l'événement à supprimer: ");
            int eventId = getIntInput();

            eventService.deleteEvent(eventId);
            System.out.println("Événement supprimé avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'événement: " + e.getMessage());
        }
    }
    private void searchEvents() {
        System.out.println("Recherche d'événements:");

        System.out.println("1. Rechercher par date");
        System.out.println("2. Rechercher par lieu");
        System.out.println("3. Rechercher par type");
        System.out.println("4. Retourner au menu précédent");
        System.out.print("Choisissez une option: ");

        int choice = getIntInput();
        Date date = null;
        String location = null;
        String type = null;

        switch (choice) {
            case 1:
                System.out.print("Entrez la date de l'événement (dd-MM-yyyy): ");
                date = convertStringToDate(scanner.nextLine());
                break;
            case 2:
                System.out.print("Entrez le lieu de l'événement: ");
                location = scanner.nextLine();
                break;
            case 3:
                System.out.print("Entrez le type d'événement: ");
                type = scanner.nextLine();
                break;
            case 4:
                return;
            default:
                System.out.println("Option invalide. Retour au menu précédent.");
                return;
        }

        List<Event> events = eventService.searchEvents(date, location, type);
        if (events.isEmpty()) {
            System.out.println("Aucun événement trouvé.");
        } else {
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }

    // Method to list all events
    private void listAllEvents() {
        try {
            List<Event> events = eventService.getAllEvents();
            if (events.isEmpty()) {
                System.out.println("Aucun événement trouvé.");
            } else {
                for (Event event : events) {
                    System.out.println(event);
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des événements: " + e.getMessage());
        }
    }

    private Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            return formatter.parse(dateString);
        } catch (Exception e) {
            System.out.println("Date invalide. Utilisation de la date actuelle.");
            return new Date();
        }
    }
}
