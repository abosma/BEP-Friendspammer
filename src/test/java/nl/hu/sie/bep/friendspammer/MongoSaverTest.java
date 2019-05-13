package nl.hu.sie.bep.friendspammer;

import nl.hu.sie.bep.domain.MessagesDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class MongoSaverTest {

    @Test
    public void getAllMessagesTest() {
        MessagesDTO messages = MongoSaver.getAllMessages();

        assertNotNull(messages);
        assertEquals(false, messages.getDocuments().isEmpty());
    }
}