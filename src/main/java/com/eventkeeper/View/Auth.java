package com.eventkeeper.View;

import com.eventkeeper.View.Admin.EventsView;
import com.eventkeeper.View.Admin.ParticipantView;
import com.eventkeeper.View.Participant.InscriptionView;
import com.eventkeeper.models.Admin;
import com.eventkeeper.models.Participant;
import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;
import com.eventkeeper.services.RegistrationService;
import com.eventkeeper.dao.EventDAO;
import com.eventkeeper.dao.ParticipantDAO;
import com.eventkeeper.dao.RegistrationDAO;
import com.eventkeeper.dao.EventDAOImpl;
import com.eventkeeper.dao.ParticipantDAOImpl;
import com.eventkeeper.dao.RegistrationDAOImpl;

import java.util.Scanner;

public class Auth {
    private final Scanner scanner = new Scanner(System.in);

    // Créer les instances DAO une seule fois
    private final EventDAO eventDAO = new EventDAOImpl();
    private final RegistrationDAO registrationDAO = new RegistrationDAOImpl();
    private final ParticipantDAO participantDAO = new ParticipantDAOImpl();

    // Créer les services en utilisant les DAO partagés
    private final EventService eventService = new EventService(eventDAO);
    private final RegistrationService registrationService = new RegistrationService(registrationDAO);
    private final ParticipantService participantService = new ParticipantService(participantDAO);

    // Créer les vues en passant les services partagés
    private final EventsView eventsView = new EventsView(eventService, participantService, registrationService);
    private final InscriptionView inscriptionView = new InscriptionView(eventService, registrationService, participantService);
    private final ParticipantView participantView = new ParticipantView(); // Assurez-vous qu'elle utilise aussi les services partagés si nécessaire

    private int promptForRole() {
        System.out.println("Entrez votre rôle (0 pour Admin, 1 pour Participant):");
        while (true) {
            try {
                int role = Integer.parseInt(scanner.nextLine());
                return role;
            } catch (NumberFormatException ex) {
                System.out.print("Entrée invalide. Veuillez saisir un nombre entier: ");
            }
        }
    }

    public void auth() {
        while (true) {
            int role = promptForRole();

            if (role == 0) {
                Admin admin = createAdmin();
                greetUser(admin.getFirstName(), admin.getLastName(), "Administrateur");
                eventsView.run();
            } else if (role == 1) {
                Participant participant = createParticipant();
                greetUser(participant.getFirstName(), participant.getLastName(), "Participant");
                inscriptionView.run(participant);
            } else {
                System.out.println("Rôle invalide. Veuillez réessayer.");
            }
        }

        // scanner.close(); // Ne jamais fermer le scanner lié à System.in
    }

    private Admin createAdmin() {
        System.out.print("Entrez votre prénom: ");
        String firstName = scanner.nextLine();
        System.out.print("Entrez votre nom de famille: ");
        String lastName = scanner.nextLine();
        System.out.print("Entrez votre email: ");
        String email = scanner.nextLine();
        return new Admin(1, firstName, lastName, email);
    }

    private Participant createParticipant() {
        System.out.print("Entrez votre prénom: ");
        String firstName = scanner.nextLine();
        System.out.print("Entrez votre nom de famille: ");
        String lastName = scanner.nextLine();
        System.out.print("Entrez votre email: ");
        String email = scanner.nextLine();
        System.out.print("Entrez votre numéro de téléphone: ");
        String phoneNumber = scanner.nextLine();
        return new Participant(1, firstName, lastName, email, phoneNumber);
    }

    private void greetUser(String firstName, String lastName, String role) {
        System.out.println("Bienvenue, " + firstName + " " + lastName);
        System.out.println("Vous êtes un " + role + ".");
    }
}
