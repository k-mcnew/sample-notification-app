package org.example.notify.services.impl;

import org.example.notify.models.PhoneNumber;
import org.example.notify.models.ShortMessage;
import org.example.notify.services.ShortMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShortMessageServiceImpl implements ShortMessageService {

    @Value("${identity.api.url}")
    private String identityApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MailSender mailSender;

    @Async
    public void sendMessage(String to, String from, String title, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject(title);
        message.setText(msg);

        // get the senders mobile phone number, then lookup the numbers carrier data.
        ShortMessage fromCarrierData = lookupCarrierData("1", getMobilePhoneNumber(from).getPhoneNumber());
        message.setFrom(fromCarrierData.getSmsGatewayAddress());

        // get the recipients mobile phone number, then lookup the numbers carrier data.
        ShortMessage toCarrierData = lookupCarrierData("1", getMobilePhoneNumber(to).getPhoneNumber());
        message.setTo(toCarrierData.getSmsGatewayAddress());

        mailSender.send(message);
    }

    /**
     * Method lookups up a person's mobile phone number through the identity service.
     * @param recipientId the unique identifier for an Identity
     * @return the mobile phone number for the given unique identifier.
     */
    private PhoneNumber getMobilePhoneNumber(String recipientId) {
        return restTemplate.getForObject(identityApiUrl + "/" +recipientId + "/phoneNumber?isPrimary=false", PhoneNumber.class);
    }

    /**
     * This method mimics a call to a carrier lookup service.
     * @param countryCode the country code for the phone number
     * @param phoneNumber the phone number to lookup
     * @return the carrier data for the given phone number
     */
    private ShortMessage lookupCarrierData(String countryCode, String phoneNumber) {
        ShortMessage sm = new ShortMessage();
        sm.setCountryCode(Integer.valueOf(countryCode));
        sm.setPhoneNumber(phoneNumber);
        sm.setIsWireless(true);
        sm.setPhoneCarrier("Verizon Wireless");
        sm.setSmsGatewayAddress(phoneNumber + "@vtext.com");
        sm.setMmsGatewayAddress(phoneNumber + "@vzwpix.com");

        return sm;
    }

}
