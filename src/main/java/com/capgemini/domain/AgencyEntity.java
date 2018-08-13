package com.capgemini.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Agency")
public class AgencyEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "agency")
    Set<EmployeeEntity> employees;

    @OneToMany(mappedBy = "agencyFrom")
    Set<RentalEntity> rentalsFrom;

    @OneToMany(mappedBy = "agencyTo")
    Set<RentalEntity> rentalsTo;

    public AgencyEntity() {
    }

    public AgencyEntity(Address address) {
        this.address = address;
        employees = new HashSet<>();
        rentalsFrom = new HashSet<>();
        rentalsFrom = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public boolean addEmployee(EmployeeEntity employee) {
        return employees.add(employee);
    }

    public boolean removeEmployee(EmployeeEntity employee) {
        return employees.remove(employee);
    }

    public Set<RentalEntity> getRentalsFrom() {
        return rentalsFrom;
    }

    public boolean addRentalFrom(RentalEntity rental) {
        return rentalsFrom.add(rental);
    }

    public boolean removeRentalFrom(RentalEntity rental) {
        return rentalsFrom.remove(rental);
    }

    public Set<RentalEntity> getRentalsTo() {
        return rentalsTo;
    }

    public boolean addRentalTo(RentalEntity rental) {
        return rentalsTo.add(rental);
    }

    public boolean removeRentalTo(RentalEntity rental) {
        return rentalsTo.remove(rental);
    }

}