package com.eventkeeper.dao;

import com.eventkeeper.models.Event;
import com.eventkeeper.models.Participant;
import com.eventkeeper.models.Registration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationDAOImpl implements RegistrationDAO {
    private final List<Registration> registrations = new ArrayList<>();

    @Override
    public void save(Registration registration) {
        registrations.add(registration);
    }

    @Override
    public void delete(int eventId, int participantId) {
        registrations.removeIf(r -> r.getEvent().getId() == eventId && r.getParticipant().getId() == participantId);
    }

    @Override
    public List<Registration> findByEventId(int eventId) {
        List<Registration> result = new ArrayList<>();
        for (Registration registration : registrations) {
            if (registration.getEvent().getId() == eventId) {
                result.add(registration);
            }
        }
        return result;
    }

    @Override
    public List<Registration> findByParticipantId(int participantId) {
        List<Registration> result = new ArrayList<>();
        for (Registration registration : registrations) {
            if (registration.getParticipant().getId() == participantId) {
                result.add(registration);
            }
        }
        return result;
    }


    @Override
    public void registerParticipant( Event event, Participant participant) {
        // Create a new registration
        Registration registration = new Registration(event, participant);
        save(registration);
    }

    @Override
    public List<Registration> getRegistrationsByParticipantId(int participantId) {
        return findByParticipantId(participantId);
    }
    @Override
    public List<Registration> RepportForParticipant(int participantId){

       return registrations.stream().filter(r-> r.getParticipant().getId()==participantId).collect(Collectors.toList());

   }
   @Override
    public List <Registration>RepportForEvent(int EventId){

       return registrations.stream().filter(r-> r.getEvent().getId()==EventId).collect(Collectors.toList());
   }



}
