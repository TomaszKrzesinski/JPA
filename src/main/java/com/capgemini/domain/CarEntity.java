package com.capgemini.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Car")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(name = "production_year", nullable = false, length = 4)
    private Integer productionYear;

    @Column(nullable = false, length = 50)
    private String colour;

    @Column(name = "engine_capacity", nullable = false, precision = 1)
    private Double engineCapacity;

    @Column(nullable = false, length = 3)
    private Integer power;

    @Column(nullable = false, length = 7)
    private Integer millage;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "carsUnderKeep")
    private Set<EmployeeEntity> keepers = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "car")
    private Set<RentalEntity> rentals = new HashSet<>();

    public CarEntity(String type, String brand, Integer productionYear, String colour, Double engineCapacity, Integer power, Integer millage) {
        this.type = type;
        this.brand = brand;
        this.productionYear = productionYear;
        this.colour = colour;
        this.engineCapacity = engineCapacity;
        this.power = power;
        this.millage = millage;
    }

    public boolean addKeeper(EmployeeEntity employee) {
        employee.addCarUnderKeep(this);
        return keepers.add(employee);
    }

    public boolean removeKeeper(EmployeeEntity employee) {
        return keepers.remove(employee);
    }

    public boolean addCarRental(RentalEntity rental) {
        return rentals.add(rental);
    }

    public boolean removeRental(RentalEntity rental) {
        return rentals.remove(rental);
    }
}
