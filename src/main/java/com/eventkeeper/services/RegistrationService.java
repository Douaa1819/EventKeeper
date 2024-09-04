package com.eventkeeper.services;

import com.eventkeeper.dao.RegistrationDAO;
import com.eventkeeper.models.Event;
import com.eventkeeper.models.Participant;
import com.eventkeeper.models.Registration;

import java.util.List;

public class RegistrationService {
    private final RegistrationDAO registrationDAO;

    public RegistrationService(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    public void registerParticipant(Event event, Participant participant) {
        Registration registration = new Registration(event, participant);
        registrationDAO.save(registration);
    }

    public void unregisterParticipant(int eventId, int participantId) {
        registrationDAO.delete(eventId, participantId);
    }

    public List<Registration> getRegistrationsByEventId(int eventId) {
        return registrationDAO.findByEventId(eventId);
    }

    public List<Registration> getRegistrationsByParticipantId(int participantId) {
        return registrationDAO.findByParticipantId(participantId);
    }

    public List<Registration> RepportForParticipant(int id ){
        return  registrationDAO.RepportForParticipant(id);
    }
    public List <Registration>RepportForEvent(int id){

        return  registrationDAO.RepportForParticipant(id);
    }

}
