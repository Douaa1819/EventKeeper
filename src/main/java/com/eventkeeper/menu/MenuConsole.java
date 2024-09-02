package com.eventkeeper.menu;

import com.eventkeeper.models.Event;
import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private final EventService eventService;
    private final ParticipantService participantService;
    private final Scanner scanner;

    // Constructor to initialize the services and scanner
    public MenuConsole(EventService eventService, ParticipantService participantService) {
        this.eventService = eventService;
        this.participantService = participantService;
        this.scanner = new Scanner(System.in);
    }

    // Method to start the admin menu
    public void startAdminMenu() {
        while (true) {
            System.out.println("\nMenu Administrateur:");
            System.out.println("1. Ajouter un événement");
            System.out.println("2. Modifier un événement");
            System.out.println("3. Supprimer un événement");
            System.out.println("4. Lister tous les événements");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option: ");

            int choice = getIntInput(); // Get user input for menu choice

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
            System.out.println("2. Quitter");
            System.out.print("Choisissez une option: ");

            int choice = getIntInput(); // Get user input for menu choice

            switch (choice) {
                case 1:
                    listAllEvents();
                    break;
                case 2:
                    System.out.println("Vous quittez le menu Participant.");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
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

    // Method to add an event
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

    // Method to update an event
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

    // Method to delete an event
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

    // Helper method to convert a string to a date
    private Date convertStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return format.parse(dateString);
        } catch (Exception e) {
            System.out.println("Format de date invalide.");
            return null;
        }
    }
}
