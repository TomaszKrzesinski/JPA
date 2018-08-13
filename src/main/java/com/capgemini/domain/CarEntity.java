package com.capgemini.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Car")
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

    @ManyToMany(mappedBy = "carsUnderKeep")
    private Set<EmployeeEntity> keepers;

    @OneToMany(mappedBy = "car")
    private Set<RentalEntity> rentals;

    public CarEntity() {
    }

    public CarEntity(String type, String brand, Integer productionYear, String colour, Double engineCapacity, Integer power, Integer millage) {
        this.type = type;
        this.brand = brand;
        this.productionYear = productionYear;
        this.colour = colour;
        this.engineCapacity = engineCapacity;
        this.power = power;
        this.millage = millage;
        keepers = new HashSet<>();
        rentals = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getMillage() {
        return millage;
    }

    public void setMillage(Integer millage) {
        this.millage = millage;
    }

    public Set<EmployeeEntity> getKeepers() {
        return keepers;
    }

    public boolean addKeeper(EmployeeEntity employee) {
        return keepers.add(employee);
    }

    public boolean removeKeeper(EmployeeEntity employee) {
        return keepers.remove(employee);
    }

    public Set<RentalEntity> getRentals() {
        return rentals;
    }

    public boolean addCarRental(RentalEntity rental) {
        return rentals.add(rental);
    }

    public boolean removeRental(RentalEntity rental) {
        return rentals.remove(rental);
    }
}
