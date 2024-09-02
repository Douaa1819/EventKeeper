
package com.eventkeeper.menu;

import com.eventkeeper.models.Event;
import com.eventkeeper.models.Participant;
import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private final EventService eventService;
    private final ParticipantService participantService;
    private final Scanner scanner;


    public MenuConsole(EventService eventService) {
        this(eventService, null, new Scanner(System.in));
    }

    public MenuConsole(EventService eventService, ParticipantService participantService, Scanner scanner) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.scanner = scanner;
    }


    public void start() {
        while (true) {
            System.out.println("\nBienvenue dans EventKeeper !");
            System.out.println("Êtes-vous :");
            System.out.println("0. Administrateur");
            System.out.println("1. Participant");
            System.out.println("2. Quitter");
            System.out.print("Choisissez une option: ");

            int roleChoice = getIntInput();

            switch (roleChoice) {
                case 0:
                    adminMenu();
                    break;
                case 1:
                    participantMenu();
                    break;
                case 2:
                    System.out.println("Au revoir!");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }


    private void adminMenu() {
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\nMenu Administrateur:");
            System.out.println("1. Ajouter un événement");
            System.out.println("2. Modifier un événement");
            System.out.println("3. Supprimer un événement");
            System.out.println("4. Lister tous les événements");
            System.out.println("5. Gérer les participants");
            //System.out.println("6. Gérer les inscriptions");
            System.out.println("7. Retour au menu principal");
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
                    manageParticipants();
                    break;
                case 6:
                    adminRunning = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private void participantMenu() {
        boolean participantRunning = true;
        while (participantRunning) {
            System.out.println("\nMenu Participant:");
            System.out.println("1. Voir les événements");
            System.out.println("2. S'inscrire à un événement");
            System.out.println("3. Se désinscrire d'un événement");
            System.out.println("4. Voir mes inscriptions");
            System.out.println("5. Retour au menu principal");
            System.out.print("Choisissez une option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    listAllEvents();
                    break;
                case 2:
                    participantRunning = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }


    private void addEvent() {
        System.out.print("Titre de l'événement: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();


        Date date = new Date();

        System.out.print("Lieu: ");
        String location = scanner.nextLine();

        System.out.print("Type: ");
        String type = scanner.nextLine();

        Event event = new Event(0, title, description, date, location, type);
        eventService.createEvent(event);

        System.out.println("Événement ajouté avec succès.");
    }


    private void updateEvent() {
        System.out.print("ID de l'événement à modifier: ");
        int eventId = getIntInput();

        Event event = eventService.getEventById(eventId);
        if (event == null) {
            System.out.println("Événement non trouvé.");
            return;
        }

        System.out.print("Nouveau titre (laissez vide pour conserver l'actuel): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            event.setTitle(title);
        }

        System.out.print("Nouvelle description (laissez vide pour conserver l'actuelle): ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            event.setDescription(description);
        }



        eventService.updateEvent(event);
        System.out.println("Événement mis à jour avec succès.");
    }


    private void deleteEvent() {
        System.out.print("ID de l'événement à supprimer: ");
        int eventId = getIntInput();

        eventService.deleteEvent(eventId);
        System.out.println("Événement supprimé avec succès.");
    }


    public void listAllEvents() {
        List<Event> events = eventService.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("Aucun événement disponible.");
            return;
        }
        for (Event event : events) {
            System.out.println(event);
        }
    }


    private void manageParticipants() {
        while (true) {
            System.out.println("\nGestion des Participants:");
            System.out.println("1. Ajouter un participant");
            System.out.println("2. Modifier un participant");
            System.out.println("3. Supprimer un participant");
            System.out.println("4. Lister tous les participants");
            System.out.println("5. Retour au menu Administrateur");
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
                    listAllParticipants();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }


    private void addParticipant() {
        System.out.print("Prénom du participant: ");
        String firstName = scanner.nextLine();

        System.out.print("Nom du participant: ");
        String lastName = scanner.nextLine();

        System.out.print("Email du participant: ");
        String email = scanner.nextLine();

        System.out.print("Numéro de téléphone: ");
        String phoneNumber = scanner.nextLine();

        Participant participant = new Participant(0, firstName, lastName, email, phoneNumber);
        participantService.addParticipant(participant);

        System.out.println("Participant ajouté avec succès.");
    }


    private void updateParticipant() {
        System.out.print("ID du participant à modifier: ");
        int participantId = getIntInput();

        Participant participant = participantService.getParticipantById(participantId);
        if (participant == null) {
            System.out.println("Participant non trouvé.");
            return;
        }

        System.out.print("Nouveau prénom (laissez vide pour conserver l'actuel): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {
            participant.setFirstName(firstName);
        }

        System.out.print("Nouveau nom (laissez vide pour conserver l'actuel): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {
            participant.setLastName(lastName);
        }

        System.out.print("Nouvel email (laissez vide pour conserver l'actuel): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            participant.setEmail(email);
        }

        System.out.print("Nouveau numéro de téléphone (laissez vide pour conserver l'actuel): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            participant.setPhoneNumber(phoneNumber);
        }

        participantService.updateParticipant(participant);
        System.out.println("Participant mis à jour avec succès.");
    }


    private void deleteParticipant() {
        System.out.print("ID du participant à supprimer: ");
        int participantId = getIntInput();

        participantService.deleteParticipant(participantId);
        System.out.println("Participant supprimé avec succès.");
    }


    private void listAllParticipants() {
        List<Participant> participants = participantService.getAllParticipants();
        if (participants.isEmpty()) {
            System.out.println("Aucun participant disponible.");
            return;
        }
        for (Participant participant : participants) {
            System.out.println(participant);
        }
    }







    private int getIntInput() {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException ex) {
                System.out.print("Entrée invalide. Veuillez saisir un nombre entier: ");
            }
        }
    }
}
