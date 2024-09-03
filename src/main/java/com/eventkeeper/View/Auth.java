package com.eventkeeper.View;

import com.eventkeeper.View.Admin.EventsView;
import com.eventkeeper.View.Admin.ParticipantView;
import com.eventkeeper.View.Participant.InscriptionView;
import com.eventkeeper.models.Admin;
import com.eventkeeper.models.Participant;

import java.util.Scanner;



public class Auth {
private static final Scanner scanner = new Scanner(System.in);
private static final EventsView eventsview = new EventsView();
private static final InscriptionView inscriptionview = new InscriptionView();
private static final ParticipantView participantview = new ParticipantView();
    private static int promptForRole(Scanner scanner) {
        System.out.println("Entrez votre rôle (0 pour Admin, 1 pour Participant):");
        int role = scanner.nextInt();
        scanner.nextLine();
        return role;
    }

    public void auth() {
        while (true) {
            int role = promptForRole(scanner);

            if (role == 0) {
                Admin admin = createAdmin(scanner);
                greetUser(admin.getFirstName(), admin.getLastName(), "Administrateur");
              eventsview.run();
            } else if (role == 1) {
                Participant participant = createParticipant(scanner);
                greetUser(participant.getFirstName(), participant.getLastName(), "Participant");
                inscriptionview .run();
            } else {
                System.out.println("Rôle invalide. Veuillez redémarrer l'application.");
                break;
            }
        }

        scanner.close();
    }


    private static Admin createAdmin(Scanner scanner) {
        System.out.print("Entrez votre prénom: ");
        String firstName = scanner.nextLine();
        System.out.print("Entrez votre nom de famille: ");
        String lastName = scanner.nextLine();
        System.out.print("Entrez votre email: ");
        String email = scanner.nextLine();
        return new Admin(1, firstName, lastName, email);
    }

    // Méthode pour créer un objet Participant
    private static Participant createParticipant(Scanner scanner) {
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


    private static void greetUser(String firstName, String lastName, String role) {
        System.out.println("Bienvenue, " + firstName + " " + lastName);
        System.out.println("Vous êtes un " + role + ".");
    }
}
