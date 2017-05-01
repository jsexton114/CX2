package com.civicxpress.letters;

import java.util.ArrayList;
import java.util.List;

public class Address {
    private long id;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Address() {

    }

    public Address(String name, String addressLine1, String addressLine2, String city, String state, String postalCode, String country) {
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    private String getFormattedCityStatePostalCode() {
        StringBuilder formattedCityStatePostalCode = new StringBuilder();
        if (this.city != null) {
            formattedCityStatePostalCode.append(this.city);
            formattedCityStatePostalCode.append(", ");
        }
        if (this.state != null) {
            formattedCityStatePostalCode.append(this.state);
            formattedCityStatePostalCode.append(", ");
        }
        if (this.postalCode != null) {
            formattedCityStatePostalCode.append(postalCode);
        }
        return formattedCityStatePostalCode.toString();
    }

    public String getFormattedSingleLineAddress() {
        String formattedAddress = "";
        String lineSeparator = ", ";
        formattedAddress = getFormattedMultipleLinesAddress(lineSeparator, false);
        return formattedAddress;
    }

    public String getFormattedMultipleLinesAddress(String lineSeparator, boolean includeFinalLineSeparator) {
        StringBuilder formattedAddress = new StringBuilder();
        String formattedCityStatePostalCode = getFormattedCityStatePostalCode();
        if (this.name != null) {
            formattedAddress.append(name);
            formattedAddress.append(lineSeparator);
        }
        if (this.addressLine1 != null) {
            formattedAddress.append(addressLine1);
            formattedAddress.append(lineSeparator);
        }
        if (this.addressLine2 != null) {
            formattedAddress.append(addressLine2);
            formattedAddress.append(lineSeparator);
        }
        formattedAddress.append(formattedCityStatePostalCode);
        if (includeFinalLineSeparator) formattedAddress.append(lineSeparator);
        return formattedAddress.toString();
    }

    public List<String> getMultipleLinesAddress() {
        List<String> addressLines = new ArrayList<String>();
        String formattedCityStatePostalCode = getFormattedCityStatePostalCode();
        if (this.name != null) {
            addressLines.add(name);
        }
        if (this.addressLine1 != null) {
            addressLines.add(addressLine1);
        }
        if (this.addressLine2 != null) {
            addressLines.add(addressLine2);
        }
        addressLines.add(formattedCityStatePostalCode);
        return addressLines;
    }

    public String getFormattedMultipleLinesAddress() {
        final String UNIX_LINE_SEPARATOR = "\n";
        String formattedAddress = "";
        formattedAddress = getFormattedMultipleLinesAddress(UNIX_LINE_SEPARATOR, true);
        return formattedAddress;
    }

    public String getFormattedHtmlAddress() {
        final String HTML_LINE_BREAK = "<br/>";
        String formattedAddress = "";
        formattedAddress = getFormattedMultipleLinesAddress(HTML_LINE_BREAK, true);
        return formattedAddress;
    }

    @Override
    public String toString() {
        String formattedAddress = getFormattedSingleLineAddress();
        return formattedAddress;
    }

}
