package org.example.notify.services;

import org.example.notify.models.Notification;
import org.example.notify.models.NotificationResults;

/**
 * Service for sending and receiving notifications
 */
public interface NotificationService {

    /**
     * Sends notifications
     * @param notification the notification to send
     * @return the unique identifier for the sent notification
     */
    String sendNotification(Notification notification);

    /**
     * Returns a subset of all notifications for the given offset and limit.
     * @param offset where to start returning records from the entire set of results
     * @param limit the max number of notifications to be returned in a single request
     * @return a subset of all notifications for the given offset and limit
     */
    NotificationResults findAllNotifications(int offset, int limit);

    /**
     * Returns a single instance of a notification for the given unique identifier
     * @param notificationId the unique identifier for a notification
     * @return a notification
     */
    Notification findNotification(String notificationId);
}
