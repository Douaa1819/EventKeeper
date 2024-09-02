package com.eventkeeper;

import com.eventkeeper.dao.EventDAOImpl;
import com.eventkeeper.dao.ParticipantDAO;
import com.eventkeeper.menu.MenuConsole;
import com.eventkeeper.models.Admin;
import com.eventkeeper.models.Participant;
import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialisation des DAO et des services
        EventDAOImpl eventDAO = new EventDAOImpl();
        ParticipantDAO participantDAO = new ParticipantDAO();

        EventService eventService = new EventService(eventDAO);
        ParticipantService participantService = new ParticipantService(participantDAO);

        MenuConsole menuConsole = new MenuConsole(eventService, participantService);

        // Gestion de l'entrée utilisateur pour la sélection du rôle
        Scanner scanner = new Scanner(System.in);
        int role = promptForRole(scanner);

        // Création de l'utilisateur en fonction du rôle choisi
        if (role == 0) {
            Admin admin = createAdmin(scanner);
            greetUser(admin.getFirstName(), admin.getLastName(), "Administrateur");
            menuConsole.startAdminMenu();
        } else if (role == 1) {
            Participant participant = createParticipant(scanner);
            greetUser(participant.getFirstName(), participant.getLastName(), "Participant");
            menuConsole.startParticipantMenu();
        } else {
            System.out.println("Rôle invalide. Veuillez redémarrer l'application.");
        }

        scanner.close();
    }

    // Méthode pour demander le rôle à l'utilisateur
    private static int promptForRole(Scanner scanner) {
        System.out.println("Entrez votre rôle (0 pour Admin, 1 pour Participant):");
        int role = scanner.nextInt();
        scanner.nextLine();  // Vider le tampon
        return role;
    }

    // Méthode pour créer un objet Admin
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

    // Méthode pour afficher un message de bienvenue à l'utilisateur
    private static void greetUser(String firstName, String lastName, String role) {
        System.out.println("Bienvenue, " + firstName + " " + lastName);
        System.out.println("Vous êtes un " + role + ".");
    }
}
