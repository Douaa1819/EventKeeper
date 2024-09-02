package com.eventkeeper.dao;

import com.eventkeeper.models.Participant;
import java.util.List;

public interface ParticipantDAO {
    void addParticipant(Participant participant);
    void updateParticipant(Participant participant);
    void deleteParticipant(int participantId);
    List<Participant> getAllParticipants();
    Participant getParticipantById(int participantId);
}
