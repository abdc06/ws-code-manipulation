package me.abdc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void isFull() {
        Event event = new Event();
        event.maxNumberOfParticipants = 0;

        assertFalse(event.isFull());

        event.maxNumberOfParticipants = 100;
        event.numberOfEnrollment = 10;

        assertFalse(event.isFull());

        event.numberOfEnrollment = 100;

        assertTrue(event.isFull());
    }

}