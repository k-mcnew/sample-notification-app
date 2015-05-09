package org.example.notify.services.impl;

import org.example.notify.models.EmailAddress;
import org.example.notify.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${identity.api.url}")
    private String identityApiUrl;

    @Async
    public void sendEmail(String from, String to, String title, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(getEmailAddress(from).getEmail());
        message.setSubject(title);
        message.setText(msg);
        message.setTo(getEmailAddress(to).getEmail());
        mailSender.send(message);
    }

    /**
     * Lookups up email address based on the person id given
     * @param recipientId the person id to lookup an email address with
     * @return the requested email address
     */
    private EmailAddress getEmailAddress(String recipientId) {
        return restTemplate.getForObject(identityApiUrl + "/" +recipientId + "/emailAddress", EmailAddress.class);
    }
}
