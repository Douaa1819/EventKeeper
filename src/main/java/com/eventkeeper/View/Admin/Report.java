package com.eventkeeper.View.Admin;

import com.eventkeeper.models.Event;
import com.eventkeeper.models.Registration;
import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;
import com.eventkeeper.services.RegistrationService;

import java.util.List;
import java.util.Scanner;


public class Report {

    private final RegistrationService registrationService;

    private final Scanner scanner = new Scanner(System.in);

    public Report( RegistrationService registrationService) {

        this.registrationService = registrationService;
    }

    public  void run(){
        while(true){
            System.out.println("***********************************");
            System.out.println("\n gestion de rapports:");
            System.out.println("1. Rapport de participant");
            System.out.println("2. Rapport d'un événement");
            System.out.println("3. Quitter");
            System.out.println("***********************************");
            System.out.print("Choisissez une option: ");

            int choice = getIntInput();
            switch (choice){
                case 1: participantRepport();
                break;

                case 2:   EventRepport();
                break;
                case 3 :
                    return;
                default:  System.out.println("Option invalide. Retour au menu précédent.");
                    return;
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


    public void participantRepport()
    {
        System.out.print("Entrez l'ID de participant auquel vous voulez vous inscrire: ");
        int id = getIntInput();
        List <Registration> registrations = registrationService.RepportForParticipant(id);

        if (registrations.isEmpty()) {
            System.out.println("Aucun participant trouvé.");
        } else {
            for (Registration registration : registrations) {
                System.out.println(registration);
            }
        }
    }


    public void EventRepport()
    {
        System.out.print("Entrez l'ID de l'événement auquel vous voulez vous inscrire: ");
        int id = getIntInput();
        List <Registration> registrations = registrationService.RepportForEvent(id);

        if (registrations.isEmpty()) {
            System.out.println("Aucun événement trouvé.");
        } else {
            for (Registration registration : registrations) {
                System.out.println(registration);
            }
        }
    }

}
