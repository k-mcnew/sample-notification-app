package org.example.notify.services.impl;

import org.example.notify.models.Notification;
import org.example.notify.models.NotificationResults;
import org.example.notify.models.Preference;
import org.example.notify.models.Type;
import org.example.notify.repositories.NotificationRepository;
import org.example.notify.repositories.PreferenceRepository;
import org.example.notify.services.EmailService;
import org.example.notify.services.NotificationService;
import org.example.notify.services.ShortMessageService;
import org.joda.time.DateTime;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Inject
    private PreferenceRepository preferenceRepository;

    @Inject
    private EmailService emailService;

    @Inject
    private ShortMessageService shortMessageService;

    @Inject
    private NotificationRepository notificationRepository;

    @Override
    public String sendNotification(Notification notification) {
        notification.setCreated(DateTime.now());
        Notification saved = notificationRepository.save(notification);
        String notificationId = saved.getId();

        List<String> recipients = notification.getRecipients();

        if (recipients == null) {

        }

        for (String recipientId : recipients) {
            Preference preference = preferenceRepository.findByPersonId(recipientId);
            if (!hasPreferences(preference)) {
                preference = getDefaultPreference(recipientId);
            }
            sendToPreferences(recipientId, preference.getTypes(), notification);
        }

        saved.setDelivered(DateTime.now());
        notificationRepository.save(notification);

        return notificationId;
    }

    @Override
    public NotificationResults findAllNotifications(int offset, int limit) {
        NotificationResults results = new NotificationResults();

        results.setNotifications(notificationRepository.findAll(new PageRequest(offset - 1, limit, Sort.Direction.ASC, "id")));
        results.setCurrentPage(results.getNotifications().getTotalPages() + 1);
        //Pagination variables
        results.setBeginPage(Math.max(1, results.getCurrentPage() - 5));
        results.setEndPage(Math.min(results.getBeginPage() + 10, results.getNotifications().getTotalPages()));

        return results;
    }

    @Override
    public Notification findNotification(String notificationId) {
        return notificationRepository.findOne(notificationId);
    }

    /**
     * Creates a default preference for the given person id, the default is EMAIL
     * @param recipientId the current person id
     * @return a notification preference with EMAIL as the default.
     */
    private Preference getDefaultPreference(String recipientId) {
        Preference defaultPreference = new Preference();

        defaultPreference.setPersonId(recipientId);
        defaultPreference.setTypes(new ArrayList<Type>());
        defaultPreference.getTypes().add(Type.EMAIL);

        return preferenceRepository.save(defaultPreference);
    }

    /**
     * Method iterates over all notification preferences for the given person and sends a notification to each preference.
     * @param recipientId the unique identifier for the person or recipient
     * @param types the notification preferences for the person or recipient
     * @param notification the notification to send out
     */
    private void sendToPreferences(String recipientId, List<Type> types, Notification notification) {

        for (Type type : types) {
            switch (type) {
                case EMAIL:
                    emailService.sendEmail(notification.getFrom(), recipientId, notification.getTitle(), notification.getMessage());
                    break;
                case SMSTEXT:
                    shortMessageService.sendMessage(notification.getFrom(), recipientId, notification.getTitle(), notification.getMessage());
                    break;
                case VOICEMAIL:
                    // TODO - Call voicemail service
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Determines if the given preference has any values.
     * @param preference the preference to evaluate
     * @return TRUE if the preference has values, FALSE otherwise
     */
    private boolean hasPreferences(Preference preference) {
        if (preference == null || preference.getTypes() == null || preference.getTypes().isEmpty()) {
            return false;
        }

        return true;
    }
}
