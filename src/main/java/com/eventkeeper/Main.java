package com.eventkeeper;

import com.eventkeeper.dao.EventDAOImpl;
import com.eventkeeper.menu.MenuConsole;
import com.eventkeeper.services.EventService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EventDAOImpl eventDAO = new EventDAOImpl();
        EventService eventService = new EventService(eventDAO);
        MenuConsole menuConsole = new MenuConsole(eventService);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez votre rôle (0 pour Admin, 1 pour Participant):");
        int role = scanner.nextInt();
        scanner.nextLine();

        if (role == 0) {

            System.out.println("Vous êtes un Administrateur.");
            menuConsole.start();

        } else if (role == 1) {
            System.out.println("Vous êtes un Participant.");
            boolean exit = false;
            while (!exit) {
                System.out.println("Choisissez une action :");
                System.out.println("1. Voir les événements");
                System.out.println("2. S'inscrire à un événement");
                System.out.println("3. Voir  les évenment que j'ai deja inscrire");
                System.out.println("4. Quitter");
                int action = scanner.nextInt();

                switch (action) {
                    case 1:
                        menuConsole.listAllEvents(); // Voir tous les événements
                        break;
                    case 2:
                        // menuConsole.registerForEvent(); // S'inscrire à un événement (à implémenter)
                        break;
                    case 3:
                        // menuConsole.viewRegistrations(); // Voir les inscriptions (à implémenter)
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Vous quittez le menu Participant.");
                        break;
                    default:
                        System.out.println("Action invalide. Veuillez réessayer.");
                }
            }
        } else {
            System.out.println("Rôle invalide. Veuillez redémarrer l'application.");
        }

        // Fermeture du scanner
        scanner.close();
    }
}
