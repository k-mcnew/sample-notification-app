package org.example.notify.services;

/**
 * Service for sending email notifications
 */
public interface EmailService {

    /**
     * Method that asynchronously sends notification emails
     * @param fromRecipientId the person id the notification is from
     * @param toRecipientId the person id the notification is to
     * @param title the title or subject for the notification
     * @param msg the body of the notification
     */
    void sendEmail(String fromRecipientId, String toRecipientId, String title, String msg);
}
