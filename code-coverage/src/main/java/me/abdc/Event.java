package me.abdc;

public class Event {

    int maxNumberOfParticipants;

    int numberOfEnrollment;

    public boolean isFull() {
        if (maxNumberOfParticipants == 0) {
            return false;
        }

        if (numberOfEnrollment < maxNumberOfParticipants) {
            return false;
        }

        return true;
    }
}

