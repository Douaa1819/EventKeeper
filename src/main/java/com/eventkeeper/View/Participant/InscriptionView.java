package com.eventkeeper.View.Participant;

import com.eventkeeper.View.Admin.ParticipantView;
import com.eventkeeper.dao.*;
import com.eventkeeper.models.Event;
import com.eventkeeper.models.Registration;
import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;
import com.eventkeeper.services.RegistrationService;

import java.util.List;
import java.util.Scanner;



public class InscriptionView {
    private static  final EventDAO eventDAO = new EventDAOImpl();
    private static  final RegistrationDAO registractionDAO = new RegistrationDAOImpl() ;
        private static final RegistrationService registrationService = new RegistrationService(registractionDAO);
        private static final EventService eventService = new EventService(eventDAO);
        private static final Scanner scanner = new Scanner(System.in);


        public void run() {
            while (true) {
                System.out.println("\nMenu Participant:");
                System.out.println("1. Voir tous les événements");
                System.out.println("2. S'inscrire à un événement");
                System.out.println("3. Désinscrire à un événement ");
                System.out.println("4. Voir mes inscriptions");
                System.out.println("5. Quitter");
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
                        desinscrireParticipant();
                        break;
                    case 4:
                        viewMyRegistrations();
                        break;
                    case 5:
                        System.out.println("Vous quittez le menu Participant.");
                        return;
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
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


    }