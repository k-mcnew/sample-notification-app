package org.example.notify.controllers;

import org.example.notify.models.Preference;
import org.example.notify.repositories.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Rest Controller provides endpoints to create, delete, and view preferences.
 */
@RestController
@RequestMapping(value = "/preferences")
public class PreferenceController {

    /** The preference repository to perform CRUD operations on notification preference data */
    @Autowired
    private PreferenceRepository repository;

    /**
     * Endpoint returns all notification preferences.
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Preference> getPreferences() { return repository.findAll(); }

    /**
     * Endpoint returns one notification preference for the given id
     * @param id the unique identifier for the desired notification preference
     * @return the notification preference for the given id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Preference getPreference(@PathVariable String id) {
        return repository.findOne(id);
    }

    /**
     * Endpoint to create a notification preference
     * @param preference the notification preference to persist to the datasource
     * @return the newly created notification preference
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody Preference preference) {
        return repository.save(preference).getId();
    }

    /**
     * Endpoint to delete a notification preference.
     * @param preferenceId the unique identifier for a notification preference
     * @param response the current server response
     */
    @RequestMapping(method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestParam String preferenceId, HttpServletResponse response) {
        repository.delete(preferenceId);
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
