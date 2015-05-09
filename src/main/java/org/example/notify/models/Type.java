package org.example.notify.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Enumeration that represents the different types of notifications that can be sent
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum Type {

    EMAIL, SMSTEXT, VOICEMAIL

}
