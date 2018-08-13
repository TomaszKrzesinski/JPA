package com.capgemini.domain;

import javax.persistence.*;

@Embeddable
public class Address {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    @Column(nullable = false, length = 50)
    private String country;

    @Column(name = "contact_number", nullable = false)
    private Long contactNumber;

    // for hibernate
    public Address() {
    }

    public Address(String street, String city, String postalCode, String country, Long contactNumber) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.contactNumber = contactNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }
}
