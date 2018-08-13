package com.capgemini.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Client")
public class ClientEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Embedded
    private Address address;

    @Column(name = "birth_date", nullable = false)
    Date birthDate;

    @OneToMany(mappedBy = "client")
    private Set<RentalEntity> rentals;

    public ClientEntity() {
    }

    public ClientEntity(String firstName, String lastName, Address address, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthDate = birthDate;
        rentals = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<RentalEntity> getRentals() {
        return rentals;
    }

    public boolean addRental(RentalEntity rental) {
        return rentals.add(rental);
    }

    public boolean removeRental(RentalEntity rental) {
        return rentals.remove(rental);
    }
}
