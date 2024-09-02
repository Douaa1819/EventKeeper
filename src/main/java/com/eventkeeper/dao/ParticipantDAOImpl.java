package com.eventkeeper.dao;

import com.eventkeeper.models.Participant;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAOImpl implements ParticipantDAO {
    private List<Participant> participants = new ArrayList<>();
    private static int lastId = 0;
    @Override
    public void addParticipant(Participant participant) {
        participant.setId(++lastId);
        participants.add(participant);
    }

    @Override
    public void updateParticipant(Participant participant) {
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).getId() == participant.getId()) {
                participants.set(i, participant);
                return;
            }
        }
    }

    @Override
    public void deleteParticipant(int participantId) {
        participants.removeIf(participant -> participant.getId() == participantId);
    }

    @Override
    public List<Participant> getAllParticipants() {
        return new ArrayList<>(participants);
    }

    @Override
    public Participant getParticipantById(int participantId) {
        for (Participant participant : participants) {
            if (participant.getId() == participantId) {
                return participant;
            }
        }
        return null;
    }
}