package org.example.notify.repositories;

import org.example.notify.models.Preference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Persists and retrieves information that represents a notification preference
 */
@Repository
public interface PreferenceRepository extends MongoRepository<Preference, String> {

    public Preference findByPersonId(String personId);
}
