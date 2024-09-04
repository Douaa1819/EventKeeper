package com.eventkeeper.dao;

import com.eventkeeper.models.Event;
import com.eventkeeper.models.Participant;
import com.eventkeeper.models.Registration;

import java.util.List;

public interface RegistrationDAO {
    void save(Registration registration);
    void delete(int eventId, int participantId);
    List<Registration> findByEventId(int eventId);
    List<Registration> findByParticipantId(int participantId);
    void registerParticipant(Event event, Participant participant);
    List<Registration> getRegistrationsByParticipantId(int participantId);
    List<Registration> RepportForParticipant(int id);
    List<Registration>RepportForEvent(int id);
}
