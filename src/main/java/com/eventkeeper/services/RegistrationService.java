package com.eventkeeper.services;

import com.eventkeeper.dao.RegistrationDAO;
import com.eventkeeper.models.Registration;

import java.util.List;

public class RegistrationService {
    private final RegistrationDAO registrationDAO;

    public RegistrationService(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    public void registerParticipant(int eventId, int participantId) {
        Registration registration = new Registration(eventId, participantId);
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
}
