package com.capgemini.service;

import com.capgemini.domain.*;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;

@Component
public class TestTO {
    public EmployeeTO getEmployee() {
        return EmployeeTO.builder()
                .firstName("Tomasz")
                .lastName("Krzesinski")
                .address(Address.builder()
                        .street("Wroclawska 23/1")
                        .city("Mrowino")
                        .postalCode("62-070")
                        .country("Polska")
                        .contactNumber(555666222L)
                        .build())
                .birthDate(Date.valueOf("1986-03-05"))
                .build();
    }

    public CarTO getCar() {
        return CarTO.builder()
                .type("Sedan")
                .brand("Mazda")
                .colour("Yellow")
                .engineCapacity(1.8)
                .power(100)
                .millage(100000)
                .productionYear(2000)
                .build();
    }
    public AgencyTO getAgency() {
        return AgencyTO.builder()
                .address(Address.builder()
                        .street("ul.Sezamkowa 6")
                        .city("Poznan")
                        .country("Polska")
                        .postalCode("62-652")
                        .contactNumber(555555555L)
                        .build())
                .build();
    }

    public CarEntity getCarEntity() {
        return CarEntity.builder()
                .type("Sedan")
                .brand("Mazda")
                .colour("Yellow")
                .engineCapacity(1.8)
                .power(100)
                .millage(100000)
                .productionYear(2000)
                .build();
    }

    public AgencyEntity getAgencyEntity() {
        return AgencyEntity.builder()
                .address(Address.builder()
                        .contactNumber(5555555L)
                        .postalCode("55-555")
                        .country("Poland")
                        .city("Poznan")
                        .street("Sezamkowa 5").build())
                .build();
    }

    public ClientEntity getClientEntity() {
        return ClientEntity.builder()
                .address(Address.builder()
                        .contactNumber(5555555L)
                        .postalCode("55-555")
                        .country("Poland")
                        .city("Poznan")
                        .street("Sezamkowa 5").build())
                .birthDate(Date.valueOf("1986-05-03"))
                .firstName("Tomasz")
                .lastName("Krzesinski")
                .build();
    }

    public RentalEntity getRentalEntity(AgencyEntity agency, CarEntity car, ClientEntity client) {
        return RentalEntity.builder()
                .agencyFrom(agency)
                .agencyTo(agency)
                .car(car)
                .client(client)
                .cost(100.00)
                .dateFrom(Date.from(Instant.now()))
                .dateTo(Date.from(Instant.now()))
                .build();
    }
}
