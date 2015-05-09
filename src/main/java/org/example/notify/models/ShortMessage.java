package org.example.notify.models;


/**
 * Model holds information to represent a short message text
 */
public class ShortMessage {

    // country code 1, only US and Canada supported.
    private int countryCode = 1;
    private String phoneNumber;
    private String phoneCarrier;
    private boolean isWireless;
    private String smsGatewayAddress;
    private String mmsGatewayAddress;

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneCarrier() {
        return phoneCarrier;
    }

    public void setPhoneCarrier(String phoneCarrier) {
        this.phoneCarrier = phoneCarrier;
    }

    public boolean isWireless() {
        return isWireless;
    }

    public void setIsWireless(boolean isWireless) {
        this.isWireless = isWireless;
    }

    public String getSmsGatewayAddress() {
        return smsGatewayAddress;
    }

    public void setSmsGatewayAddress(String smsGatewayAddress) {
        this.smsGatewayAddress = smsGatewayAddress;
    }

    public String getMmsGatewayAddress() {
        return mmsGatewayAddress;
    }

    public void setMmsGatewayAddress(String mmsGatewayAddress) {
        this.mmsGatewayAddress = mmsGatewayAddress;
    }
}
