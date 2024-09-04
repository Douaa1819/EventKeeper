package com.eventkeeper;

import com.eventkeeper.View.Auth;
import com.eventkeeper.dao.*;
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

}
