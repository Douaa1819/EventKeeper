package com.eventkeeper;

import com.eventkeeper.View.Auth;
import com.eventkeeper.dao.*;
import com.eventkeeper.menu.MenuConsole;
import com.eventkeeper.models.Admin;
import com.eventkeeper.models.Participant;
import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;
import com.eventkeeper.services.RegistrationService;

import java.util.Scanner;

public class Main {
    public static final Auth auth = new Auth();

    public static void main(String[] args) {


        auth.auth();


    }
        // Initialisation des DAO et des services
//        EventDAO eventDAO = new EventDAOImpl();
//        ParticipantDAO participantDAO = new ParticipantDAOImpl();
//        RegistrationDAO registrationDAO = new RegistrationDAOImpl();
//        EventService eventService = new EventService(eventDAO);
//        ParticipantService participantService = new ParticipantService(participantDAO);
//        RegistrationService registrationService = new RegistrationService(registrationDAO);
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            MenuConsole menuConsole = new MenuConsole(eventService, participantService, registrationService, scanner);
//
//            int role = promptForRole(scanner);
//
//            if (role == 0) {
//                Admin admin = createAdmin(scanner);
//                greetUser(admin.getFirstName(), admin.getLastName(), "Administrateur");
//                menuConsole.startAdminMenu();
//            } else if (role == 1) {
//                Participant participant = createParticipant(scanner);
//                greetUser(participant.getFirstName(), participant.getLastName(), "Participant");
//                menuConsole.startParticipantMenu();
//            } else {
//                System.out.println("Rôle invalide. Veuillez redémarrer l'application.");
//                break;
//            }
//        }
//
//        scanner.close();
//    }
//
//
//    private static int promptForRole(Scanner scanner) {
//        System.out.println("Entrez votre rôle (0 pour Admin, 1 pour Participant):");
//        int role = scanner.nextInt();
//        scanner.nextLine();
//        return role;
//    }
//
//    // Méthode pour créer un objet Admin
//    private static Admin createAdmin(Scanner scanner) {
//        System.out.print("Entrez votre prénom: ");
//        String firstName = scanner.nextLine();
//        System.out.print("Entrez votre nom de famille: ");
//        String lastName = scanner.nextLine();
//        System.out.print("Entrez votre email: ");
//        String email = scanner.nextLine();
//        return new Admin(1, firstName, lastName, email);
//    }
//
//    // Méthode pour créer un objet Participant
//    private static Participant createParticipant(Scanner scanner) {
//        System.out.print("Entrez votre prénom: ");
//        String firstName = scanner.nextLine();
//        System.out.print("Entrez votre nom de famille: ");
//        String lastName = scanner.nextLine();
//        System.out.print("Entrez votre email: ");
//        String email = scanner.nextLine();
//        System.out.print("Entrez votre numéro de téléphone: ");
//        String phoneNumber = scanner.nextLine();
//        return new Participant(1, firstName, lastName, email, phoneNumber);
//    }
//
//
//    private static void greetUser(String firstName, String lastName, String role) {
//        System.out.println("Bienvenue, " + firstName + " " + lastName);
//        System.out.println("Vous êtes un " + role + ".");
//    }
}
