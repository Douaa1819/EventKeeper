package com.eventkeeper.dao;

import com.eventkeeper.models.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {
    private List<Participant> participants = new ArrayList<>();

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void updateParticipant(Participant participant) {
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).getId() == participant.getId()) {
                participants.set(i, participant);
                return;
            }
        }
    }

    public void deleteParticipant(int participantId) {
        participants.removeIf(participant -> participant.getId() == participantId);
    }

    public List<Participant> getAllParticipants() {
        return participants;
    }

    public Participant getParticipantById(int participantId) {
        for (Participant participant : participants) {
            if (participant.getId() == participantId) {
                return participant;
            }
        }
        return null;
    }
}
