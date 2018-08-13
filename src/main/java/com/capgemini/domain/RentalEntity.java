package com.capgemini.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Rental")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    CarEntity car;

    @ManyToOne
    ClientEntity client;

    @Column(name = "date_from", nullable = false)
    LocalDateTime dateFrom;

    @Column(name = "date_from", nullable = true)
    LocalDateTime dateTo;

    @ManyToOne
    AgencyEntity agencyFrom;

    @ManyToOne
    AgencyEntity agencyTo;

    @Column(nullable = true, precision = 2)
    private Double cost;

    public RentalEntity() {
    }

    public RentalEntity(CarEntity car,
                        ClientEntity client,
                        LocalDateTime dateFrom,
                        LocalDateTime dateTo,
                        AgencyEntity agencyFrom,
                        AgencyEntity agencyTo,
                        Double cost) {
        this.car = car;
        this.client = client;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.agencyFrom = agencyFrom;
        this.agencyTo = agencyTo;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public AgencyEntity getAgencyFrom() {
        return agencyFrom;
    }

    public void setAgencyFrom(AgencyEntity agencyFrom) {
        this.agencyFrom = agencyFrom;
    }

    public AgencyEntity getAgencyTo() {
        return agencyTo;
    }

    public void setAgencyTo(AgencyEntity agencyTo) {
        this.agencyTo = agencyTo;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
