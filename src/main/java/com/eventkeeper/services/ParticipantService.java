package com.eventkeeper.services;

import com.eventkeeper.dao.ParticipantDAO;
import com.eventkeeper.models.Participant;

import java.util.List;

public class ParticipantService {
    private ParticipantDAO participantDAO;

    public ParticipantService(ParticipantDAO participantDAO) {
        this.participantDAO = participantDAO;
    }

    public void addParticipant(Participant participant) {
        participantDAO.addParticipant(participant);
    }

    public void updateParticipant(Participant participant) {
        participantDAO.updateParticipant(participant);
    }

    public void deleteParticipant(int participantId) {
        participantDAO.deleteParticipant(participantId);
    }

    public List<Participant> getAllParticipants() {
        return participantDAO.getAllParticipants();
    }

    public Participant getParticipantById(int participantId) {
        return participantDAO.getParticipantById(participantId);
    }
}
