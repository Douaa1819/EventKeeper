package com.eventkeeper.View.Participant;

import com.eventkeeper.models.Event;
import com.eventkeeper.models.Participant;
import com.eventkeeper.models.Registration;
import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;
import com.eventkeeper.services.RegistrationService;

import java.util.List;
import java.util.Scanner;

public class InscriptionView {
    private final EventService eventService;
    private final RegistrationService registrationService;
    private final ParticipantService participantService;
    private final Scanner scanner;

    public InscriptionView(EventService eventService, RegistrationService registrationService, ParticipantService participantService) {
        this.eventService = eventService;
        this.registrationService = registrationService;
        this.participantService = participantService;
        this.scanner = new Scanner(System.in);
    }

    public void run(Participant participant) {
        while (true) {
            System.out.println("***********************************");
            System.out.println("\nMenu Participant:");
            System.out.println("1. Voir tous les événements");
            System.out.println("2. S'inscrire à un événement");
            System.out.println("3. Désinscrire à un événement");
            System.out.println("4. Voir mes inscriptions");
            System.out.println("5. Quitter");
            System.out.println("***********************************");
            System.out.print("Choisissez une option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    listAllEvents();
                    break;
                case 2:
                    registerForEvent(participant);
                    break;
                case 3:
                    desinscrireParticipant(participant);
                    break;
                case 4:
                    viewMyRegistrations(participant);
                    break;
                case 5:
                    System.out.println("Vous quittez le menu Participant.");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private void listAllEvents() {
        try {
            List<Event> events = eventService.getAllEvents();
            System.out.println("Nombre d'événements récupérés: " + events.size());
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

    private void registerForEvent(Participant participant) {
        try {
            System.out.print("Entrez l'ID de l'événement auquel vous voulez vous inscrire: ");
            int eventId = getIntInput();
            Event event = eventService.getEventById(eventId);
            if (event == null) {
                System.out.println("L'événement avec l'ID " + eventId + " n'existe pas.");
                return;
            }

            registrationService.registerParticipant(event, participant);
            System.out.println("Inscription réussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription: " + e.getMessage());
        }
    }

    private void desinscrireParticipant(Participant participant) {
        try {
            System.out.print("Entrez l'ID de l'événement duquel vous voulez vous désinscrire: ");
            int eventId = getIntInput();

            // Vérifier si l'événement est bien enregistré pour ce participant
            List<Registration> registrations = registrationService.getRegistrationsByParticipantId(participant.getId());
            boolean registered = registrations.stream().anyMatch(reg -> reg.getEvent().getId() == eventId);

            if (!registered) {
                System.out.println("Vous n'êtes pas inscrit à cet événement.");
                return;
            }

            registrationService.unregisterParticipant(eventId, participant.getId());
            System.out.println("Désinscription réussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la désinscription: " + e.getMessage());
        }
    }

    private void viewMyRegistrations(Participant participant) {
        try {
            List<Registration> registrations = registrationService.getRegistrationsByParticipantId(participant.getId());

            if (registrations.isEmpty()) {
                System.out.println("Aucune inscription trouvée pour ce participant.");
            } else {
                System.out.println("Vos inscriptions:");
                for (Registration registration : registrations) {
                    Event event = eventService.getEventById(registration.getEvent().getId());
                    if (event != null) {
                        System.out.println("Événement:");
                        System.out.println("ID: " + event.getId());
                        System.out.println("Nom: " + event.getTitle());
                        System.out.println("Date: " + event.getDate());
                        System.out.println("Lieu: " + event.getLocation());
                        System.out.println("Description: " + event.getDescription());
                        System.out.println("Détails de l'inscription:");
                        System.out.println("ID de l'événement: " + registration.getEvent().getId());
                        System.out.println("ID du participant: " + registration.getParticipant().getId());
                        System.out.println();
                    } else {
                        System.out.println("L'événement avec l'ID " + registration.getEvent().getId() + " n'existe pas.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage des inscriptions: " + e.getMessage());
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
}
