package org.example.notify.repositories;

import org.example.notify.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Persists and retrieves information that represents a notification
 */
@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
