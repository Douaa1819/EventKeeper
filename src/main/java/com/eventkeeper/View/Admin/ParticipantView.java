package com.eventkeeper.View.Admin;

import com.eventkeeper.dao.EventDAO;
import com.eventkeeper.dao.EventDAOImpl;
import com.eventkeeper.dao.ParticipantDAO;
import com.eventkeeper.dao.ParticipantDAOImpl;
import com.eventkeeper.models.Participant;
import com.eventkeeper.services.EventService;
import com.eventkeeper.services.ParticipantService;
import com.eventkeeper.services.RegistrationService;

import java.util.Scanner;

public class ParticipantView {
    private static  final EventDAO eventDAO = new EventDAOImpl();
    private static  final ParticipantDAO participantDAO = new ParticipantDAOImpl();
    private static final EventService eventService = new EventService(eventDAO);
    private  static final  ParticipantService participantService = new ParticipantService(participantDAO);

    private static final Scanner scanner = new Scanner(System.in);;


    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.print("Entrée invalide. Veuillez saisir un nombre entier: ");
            }
        }
    }


    //Menu Console

    public void run(){


        while (true) {
            System.out.println("***********************************");
            System.out.println("\nGestion des Participants:");
            System.out.println("1. Ajouter un participant");
            System.out.println("2. Modifier un participant");
            System.out.println("3. Supprimer un participant");
            System.out.println("4. Afficher la liste des participants");
            System.out.println("5. Retour au menu principal");
            System.out.println("***********************************");
            System.out.print("Choisissez une option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addParticipant();
                    break;
                case 2:
                    updateParticipant();
                    break;
                case 3:
                    deleteParticipant();
                    break;
                case 4:
                    listParticipants();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }



    }
//ajouter participant
    private void addParticipant() {
        System.out.println("Ajouter un participant");
        System.out.print("Entrez le prénom: ");
        String firstName = scanner.nextLine();
        System.out.print("Entrez le nom de famille: ");
        String lastName = scanner.nextLine();
        System.out.print("Entrez l'email: ");
        String email = scanner.nextLine();
        System.out.print("Entrez le numéro de téléphone: ");
        String phoneNumber = scanner.nextLine();
        Participant participant = new Participant(1, firstName, lastName, email, phoneNumber);
        participantService.addParticipant(participant);
        System.out.println("Participant ajouté avec succès.");
    }
//modifier participant
    private void updateParticipant() {
        System.out.println("Modifier un participant");
        System.out.print("Entrez l'ID du participant à modifier: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Participant participant = participantService.getParticipantById(id);
        if (participant != null) {
            System.out.print("Entrez le nouveau prénom (laisser vide pour ne pas modifier): ");
            String firstName = scanner.nextLine();
            System.out.print("Entrez le nouveau nom de famille (laisser vide pour ne pas modifier): ");
            String lastName = scanner.nextLine();
            System.out.print("Entrez le nouvel email (laisser vide pour ne pas modifier): ");
            String email = scanner.nextLine();
            System.out.print("Entrez le nouveau numéro de téléphone (laisser vide pour ne pas modifier): ");
            String phoneNumber = scanner.nextLine();

            if (!firstName.isEmpty()) participant.setFirstName(firstName);
            if (!lastName.isEmpty()) participant.setLastName(lastName);
            if (!email.isEmpty()) participant.setEmail(email);
            if (!phoneNumber.isEmpty()) participant.setPhoneNumber(phoneNumber);

            participantService.updateParticipant(participant);
            System.out.println("Participant mis à jour avec succès.");
        } else {
            System.out.println("Participant non trouvé.");
        }
    }
    //supprimer un participant
    private void deleteParticipant() {
        System.out.println("Supprimer un participant");
        System.out.print("Entrez l'ID du participant à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        participantService.deleteParticipant(id);
        System.out.println("Participant supprimé avec succès.");
    }

    //afficher tous les participant
    private void listParticipants() {
        System.out.println("Liste des participants:");
        for (Participant participant : participantService.getAllParticipants()) {
            System.out.println(participant);
        }
    }



}
