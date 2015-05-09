package org.example.notify.controllers;

import org.example.notify.models.Notification;
import org.example.notify.models.NotificationResults;
import org.example.notify.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller provides endpoints to send notifications, and retrieve information on notification that have been sent
 */
@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {

    /** Notification service to perform CRUD operations on notification related information */
    @Autowired
    NotificationService notificationService;

    /**
     * Retrieves a subset of all sent notifications based on the given offset and limit request parameters
     * @param limit the max number of records to return for this request, defaulted to 25
     * @param offset where to start returning records from the entire set of results, defaulted to one
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public NotificationResults getNotifications(@RequestParam(defaultValue="25", required=false) Integer limit, @RequestParam(defaultValue="1", required=false) Integer offset) {

        return notificationService.findAllNotifications(offset, limit);
    }

    /**
     * Endpoint finds the Notification for the given Id.
     * @param notificationId the unqiue identifier for a created or sent notification
     * @return the Notification for the given notification id
     */
    @RequestMapping(value = "/{notificationId}", method = RequestMethod.GET)
    @ResponseBody
    public Notification getNotificationById(@PathVariable String notificationId) {

        return notificationService.findNotification(notificationId);
    }

    /**
     * Endpoint allows for sending a notification to one ore more recipients.
     * @param notification the notification to be sent
     * @return the unique identifier for this notification
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> sendNotification(@RequestBody Notification notification) {
        HashMap<String, String> result = new HashMap<String, String>();

        String notificationId = notificationService.sendNotification(notification);
        result.put("notificationId", notificationId);

        return result;
    }

}
