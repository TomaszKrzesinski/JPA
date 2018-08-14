package com.capgemini.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Employee")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Set<CarEntity> carsUnderKeep = new HashSet<>();

    public boolean addCarUnderKeep(CarEntity car){
        car.addKeeper(this);
        return carsUnderKeep.add(car);
    }

    public boolean removeCarUnderKeep(CarEntity car) {
        return carsUnderKeep.remove(car);
    }
}
