package org.example.notify.services;

/**
 * Service for sending SMS texts as emails.
 */
public interface ShortMessageService {

    /**
     * Sends SMS texts as emails
     * @param to the person id to send the notification to
     * @param from the person id the notification is from
     * @param title the title or subject of the message
     * @param msg the body of the text message
     */
    void sendMessage(String to, String from, String title, String msg);
}
