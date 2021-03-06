package com.capgemini.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Agency")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AgencyEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Address address;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @OneToMany(mappedBy = "agency")
    Set<EmployeeEntity> employees = new HashSet<>();

    @OneToMany(mappedBy = "agencyFrom")
    Set<RentalEntity> rentalsFrom = new HashSet<>();

    @OneToMany(mappedBy = "agencyTo")
    Set<RentalEntity> rentalsTo = new HashSet<>();

    @Version
    private Long version;

    public boolean addEmployee(EmployeeEntity employee) {
        employee.setAgency(this);
        boolean result = employees.add(employee);

        return result;

    }

    public boolean removeEmployee(EmployeeEntity employee) {
        return employees.remove(employee);
    }

    public boolean addRentalFrom(RentalEntity rental) {
        rental.setAgencyFrom(this);
        return rentalsFrom.add(rental);
    }

    public boolean removeRentalFrom(RentalEntity rental) {
        return rentalsFrom.remove(rental);
    }

    public boolean addRentalTo(RentalEntity rental) {
        rental.setAgencyTo(this);
        return rentalsTo.add(rental);
    }

    public boolean removeRentalTo(RentalEntity rental) {
        return rentalsTo.remove(rental);
    }
}