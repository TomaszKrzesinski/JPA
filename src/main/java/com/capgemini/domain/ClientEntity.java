package com.capgemini.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Client")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Set<RentalEntity> rentals = new HashSet<>();

    public ClientEntity(String firstName, String lastName, Address address, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthDate = birthDate;
    }

    public boolean addRental(RentalEntity rental) {
        rental.setClient(this);
        return rentals.add(rental);
    }

    public boolean removeRental(RentalEntity rental) {
        return rentals.remove(rental);
    }
}
