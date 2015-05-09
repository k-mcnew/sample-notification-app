package org.example.notify.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Model for an email address
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailAddress {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
