package com.capgemini.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Employee")
public class EmployeeEntity {
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

    @ManyToOne
    AgencyEntity agency;

    @ManyToOne
    RankEntity rank;

    @ManyToMany
    @JoinTable(
            name = "car_keeper",
            joinColumns = { @JoinColumn(name = "car_id") },
            inverseJoinColumns = { @JoinColumn (name = "employee_id") }
    )
    private Set<CarEntity> carsUnderKeep;

    public EmployeeEntity() {
    }

    public EmployeeEntity(String firstName, String lastName, Address address, Date birthDate, RankEntity rank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthDate = birthDate;
        this.rank = rank;
        carsUnderKeep = new HashSet<>();
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

    public RankEntity getRank() {
        return rank;
    }

    public void setRank(RankEntity rank) {
        this.rank = rank;
    }

    public AgencyEntity getAgency() {
        return agency;
    }

    public void setAgency(AgencyEntity agency) {
        this.agency = agency;
    }

    public Set<CarEntity> getCarsUnderKeep() {
        return carsUnderKeep;
    }

    public boolean addCarUnderKeep(CarEntity car){
        return carsUnderKeep.add(car);
    }

    public boolean removeCarUnderKeep(CarEntity car) {
        return carsUnderKeep.remove(car);
    }
}
