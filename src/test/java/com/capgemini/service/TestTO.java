package com.capgemini.service;

import com.capgemini.domain.Address;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import org.springframework.stereotype.Component;

import java.sql.Date;

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
}
