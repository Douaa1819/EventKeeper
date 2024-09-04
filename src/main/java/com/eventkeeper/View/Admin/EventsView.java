package com.eventkeeper.View.Admin;

import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;
import com.eventkeeper.services.RegistrationService;
import com.eventkeeper.View.Admin.ParticipantView;

import com.eventkeeper.models.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EventsView {
    private final EventService eventService;
    private final ParticipantService participantService;
    private final RegistrationService registrationService;
    private final ParticipantView participantView;
    private final Report rapport;
    private final Scanner scanner = new Scanner(System.in);

    public EventsView(EventService eventService, ParticipantService participantService, RegistrationService registrationService) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.registrationService = registrationService;
        this.participantView = new ParticipantView();
        this.rapport=new Report(registrationService);// Passez les services si ParticipantView en a besoin
    }
    public void run() {
        while (true) {
            System.out.println("***********************************");
            System.out.println("\nMenu Administrateur:");
            System.out.println("1. Ajouter un événement");
            System.out.println("2. Modifier un événement");
            System.out.println("3. Supprimer un événement");
            System.out.println("4. Lister tous les événements");
            System.out.println("5. Rechercher des événements");
            System.out.println("6. Gérer les participants");
            System.out.println("7. gestion des rapports");
            System.out.println("8. Quitter");
            System.out.println("***********************************");
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
                    participantView.run();
                    break;
                case 7:
                    rapport.run();
                    break;
                case 8:
                    System.out.println("Vous quittez le menu Administrateur.");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.print("Entrée invalide. Veuillez saisir un nombre entier: ");
            }
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
        System.out.println("***********************************");
        System.out.println("Recherche d'événements:");

        System.out.println("1. Rechercher par date");
        System.out.println("2. Rechercher par lieu");
        System.out.println("3. Rechercher par type");
        System.out.println("4. Retourner au menu précédent");
        System.out.println("***********************************");
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
