package com.eventkeeper.dao;

import com.eventkeeper.models.Registration;

import java.util.List;

public interface RegistrationDAO {
    void save(Registration registration);
    void delete(int eventId, int participantId);
    List<Registration> findByEventId(int eventId);
    List<Registration> findByParticipantId(int participantId);
    void registerParticipant(int eventId, int participantId);
    List<Registration> getRegistrationsByParticipantId(int participantId);
}
