package com.capgemini.service;

import com.capgemini.dao.*;
import com.capgemini.domain.*;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.profiles.active=hsql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarServiceTest {
    @Autowired
    CarService carService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    TestTO testTo;
    @Autowired
    CarDao carDao;
    @Autowired
    AgencyDao agencyDao;
    @Autowired
    ClientDao clientDao;

    @Autowired
    RentalDao rentalDao;

    @Test
    public void shouldAddCarAndReturnWithID() {
        //given
        CarTO car = testTo.getCar();
        //when
        CarTO savedCar = carService.addCar(car);
        //then
        Assert.assertEquals(savedCar.getId(), (Long)1L);
        Assert.assertNotNull(savedCar);
    }

    @Test
    public void shouldFindCarAddedToDatabase() {
        //given
        CarTO car = testTo.getCar();
        //when
        CarTO savedCar = carService.addCar(car);
        CarTO foundCar = carService.getCarByID(savedCar.getId());
        //then
        Assert.assertNotNull(foundCar);
        Assert.assertEquals(savedCar.getId(), foundCar.getId());
        System.out.println(foundCar.getId());
    }

    @Test
    public void shouldFindAllCarsAddedToDatabase() {
        //given
        CarTO car = testTo.getCar();
        //when
        CarTO savedCar1 = carService.addCar(car);
        CarTO savedCar2 = carService.addCar(car);
        List<CarTO> foundCars = carService.getAllCars();
        //then
        Assert.assertNotNull(foundCars);
        Assert.assertEquals((Integer)foundCars.size(),(Integer)2);
        Assert.assertTrue(foundCars.contains(savedCar1) && foundCars.contains(savedCar2));
    }

    @Test
    public void shouldRemoveCarFromDatabase() {
        //given
        CarTO car = testTo.getCar();
        //when
        CarTO savedCar = carService.addCar(car);
        Integer sizeAfterAdding = carService.getAllCars().size();
        carService.removeCar(savedCar.getId());
        List<CarTO> foundCars = carService.getAllCars();
        //then
        Assert.assertEquals((Integer)foundCars.size(),(Integer)(sizeAfterAdding-1));
    }

    @Test
    public void shouldUpdateCarDataAndReturnUpdatedDO() {
        //given
        CarTO car = testTo.getCar();
        //when
        CarTO savedCar = carService.addCar(car);

        savedCar.setColour("black");
        savedCar.setBrand("BMW");

        CarTO updatedCar = carService.updateCar(savedCar);
        //then
        Assert.assertEquals(updatedCar.getId(), savedCar.getId());
        Assert.assertEquals(updatedCar.getColour(), "black");
        Assert.assertEquals(updatedCar.getBrand(), "BMW");
    }

    @Test
    public void shouldAddKeeperToCar() {
        //given
        CarTO car = testTo.getCar();
        EmployeeTO employee = testTo.getEmployee();
        //when
        CarTO savedCar = carService.addCar(car);
        EmployeeTO savedEmployee = employeeService.addEmployee(employee);
        CarTO assignedCar = carService.assignKeeper(savedCar.getId(),savedEmployee.getId());

        Set<CarTO> assignedCars = carService.findCarsByKeeper(savedEmployee.getId());
        //then
        Assert.assertNotNull(assignedCars);
        Assert.assertEquals((Integer)1, (Integer)assignedCars.size());
        Assert.assertEquals(savedEmployee.getId(), assignedCars.iterator().next().getId());
    }

    @Test
    public void shouldFindCarsAssignedToKeeper() {
        //given
        CarTO car1 = testTo.getCar();
        CarTO car2 = testTo.getCar();
        car2.setBrand("Skoda");
        CarTO car3 = testTo.getCar();
        car2.setBrand("VW");
        CarTO car4 = testTo.getCar();
        car2.setBrand("BMW");
        CarTO car5 = testTo.getCar();
        car2.setBrand("RENAULT");

        EmployeeTO employee1 = testTo.getEmployee();
        EmployeeTO employee2 = testTo.getEmployee();
        employee2.setLastName("Frankowski");

        //when
        carService.addCar(car1);
        carService.addCar(car2);
        carService.addCar(car3);
        carService.addCar(car4);
        carService.addCar(car5);

        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);

        carService.assignKeeper(1L,1L);
        carService.assignKeeper(2L,1L);
        carService.assignKeeper(3L,1L);

        carService.assignKeeper(4L,1L);
        carService.assignKeeper(4L,2L);
        carService.assignKeeper(5L,2L);

        Set<CarTO> assignedCars1 = carService.findCarsByKeeper(1L);
        Set<CarTO> assignedCars2 = carService.findCarsByKeeper(2L);
        //then
        Assert.assertNotNull(assignedCars1);
        Assert.assertNotNull(assignedCars2);

        Assert.assertEquals((Integer)4, (Integer)assignedCars1.size());
        Assert.assertEquals((Integer)2, (Integer)assignedCars2.size());
    }

    @Test
    public void shouldFindCarByNameAndBrand(){
        //given
        CarTO car1 = testTo.getCar();
        car1.setType("Sedan");
        car1.setBrand("Mazda");
        CarTO car2 = testTo.getCar();
        car2.setType("Sedan");
        car2.setBrand("Skoda");
        CarTO car3 = testTo.getCar();
        car3.setType("Sedan");
        car3.setBrand("Mazda");
        CarTO car4 = testTo.getCar();
        car4.setType("Combi");
        car4.setBrand("BMW");
        CarTO car5 = testTo.getCar();
        car5.setType("Hatchback");
        car5.setBrand("RENAULT");

        //when
        carService.addCar(car1);
        carService.addCar(car2);
        carService.addCar(car3);
        carService.addCar(car4);
        carService.addCar(car5);

        List<CarTO> listOfCars1 = carService.findCarsByTypeAndBrand("Sedan", "Mazda");
        List<CarTO> listOfCars2 = carService.findCarsByTypeAndBrand("Sedan", "");
        List<CarTO> listOfCars3 = carService.findCarsByTypeAndBrand("", "Skoda");
        List<CarTO> listOfCars4 = carService.findCarsByTypeAndBrand("Ha", "Renault");
        List<CarTO> listOfCars5 = carService.findCarsByTypeAndBrand("", "KIA");

        Assert.assertEquals((Integer)2, (Integer)listOfCars1.size());
        Assert.assertEquals((Integer)3, (Integer)listOfCars2.size());
        Assert.assertEquals((Integer)1, (Integer)listOfCars3.size());
        Assert.assertEquals((Integer)1, (Integer)listOfCars4.size());
        Assert.assertEquals((Integer)0, (Integer)listOfCars5.size());
    }

    @Test
    public void shouldRemoveRentalsAfterRemovingCar() {
        //given
        CarEntity car = CarEntity.builder()
                .type("Sedan")
                .brand("Mazda")
                .colour("Yellow")
                .engineCapacity(1.8)
                .power(100)
                .millage(100000)
                .productionYear(2000)
                .build();
        CarEntity savedCar = carDao.save(car);

        AgencyEntity agency = AgencyEntity.builder()
                .address(Address.builder()
                        .contactNumber(5555555L)
                        .postalCode("55-555")
                        .country("Poland")
                        .city("Poznan")
                        .street("Sezamkowa 5").build())
                .build();
        AgencyEntity savedAgency = agencyDao.save(agency);

        ClientEntity client = ClientEntity.builder()
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
        ClientEntity savedClient = clientDao.save(client);

        RentalEntity rentalEntity = RentalEntity.builder()
                .agencyFrom(savedAgency)
                .agencyTo(savedAgency)
                .car(savedCar)
                .client(savedClient)
                .cost(100.00)
                .dateFrom(Date.from(Instant.now()))
                .dateTo(Date.from(Instant.now()))
                .build();
        RentalEntity savedRental = rentalDao.save(rentalEntity);

        Assert.assertNotNull(carDao.findOne(savedCar.getId()));
        Assert.assertNotNull(agencyDao.findOne(savedAgency.getId()));
        Assert.assertNotNull(clientDao.findOne(savedClient.getId()));
        Assert.assertNotNull(rentalDao.findOne(savedRental.getId()));

        Assert.assertEquals((Integer)1, (Integer)carDao.getRentalsCount(savedCar.getId()));
        Assert.assertEquals((Integer)1, (Integer)agencyDao.getRentalsFromCount(savedAgency.getId()));
        Assert.assertEquals((Integer)1, (Integer)agencyDao.getRentalsToCount(savedAgency.getId()));
        Assert.assertEquals((Integer)1, (Integer)clientDao.getRentalsCount(savedClient.getId()));
        Assert.assertEquals(savedCar.getId(), rentalDao.findOne(savedRental.getId()).getCar().getId());
        Assert.assertEquals(savedAgency.getId(), rentalDao.findOne(savedRental.getId()).getAgencyFrom().getId());
        Assert.assertEquals(savedAgency.getId(), rentalDao.findOne(savedRental.getId()).getAgencyTo().getId());
        Assert.assertEquals(savedClient.getId(), rentalDao.findOne(savedRental.getId()).getClient().getId());
        //when
        carDao.delete(savedCar.getId());
        //then
        Assert.assertNull(carDao.findOne(savedCar.getId()));
        Assert.assertNull(rentalDao.findOne(savedRental.getId()));
        Assert.assertEquals((Integer)0, (Integer)agencyDao.getRentalsFromCount(savedAgency.getId()));
        Assert.assertEquals((Integer)0, (Integer)agencyDao.getRentalsToCount(savedAgency.getId()));
        Assert.assertEquals((Integer)0, (Integer)clientDao.getRentalsCount(savedClient.getId()));
    }

    @Test
    public void shouldFindCarsThatWasRentedByMoreThan10DistinctClients() {
        //given
        CarEntity car1 = testTo.getCarEntity();
        CarEntity savedCar1 = carDao.save(car1);
        CarEntity car2 = testTo.getCarEntity();
        CarEntity savedCar2 = carDao.save(car2);
        CarEntity car3 = testTo.getCarEntity();
        CarEntity savedCar3 = carDao.save(car3);

        AgencyEntity agency = testTo.getAgencyEntity();
        AgencyEntity savedAgency = agencyDao.save(agency);

        ClientEntity client1 = testTo.getClientEntity();
        ClientEntity savedClient1 = clientDao.save(client1);
        ClientEntity client2 = testTo.getClientEntity();
        ClientEntity savedClient2 = clientDao.save(client2);
        ClientEntity client3 = testTo.getClientEntity();
        ClientEntity savedClient3 = clientDao.save(client3);
        ClientEntity client4 = testTo.getClientEntity();
        ClientEntity savedClient4 = clientDao.save(client4);
        ClientEntity client5 = testTo.getClientEntity();
        ClientEntity savedClient5 = clientDao.save(client5);
        ClientEntity client6 = testTo.getClientEntity();
        ClientEntity savedClient6 = clientDao.save(client6);


        RentalEntity rentalEntity1 = testTo.getRentalEntity(savedAgency, savedCar1, savedClient1); //car1 - 6 rentals by different users
        RentalEntity rentalEntity2 = testTo.getRentalEntity(savedAgency, savedCar1, savedClient2); // 8 rental overall
        RentalEntity rentalEntity3 = testTo.getRentalEntity(savedAgency, savedCar1, savedClient3);
        RentalEntity rentalEntity4 = testTo.getRentalEntity(savedAgency, savedCar1, savedClient4);
        RentalEntity rentalEntity5 = testTo.getRentalEntity(savedAgency, savedCar1, savedClient5);
        RentalEntity rentalEntity6 = testTo.getRentalEntity(savedAgency, savedCar1, savedClient6);
        RentalEntity rentalEntity7 = testTo.getRentalEntity(savedAgency, savedCar1, savedClient5);
        RentalEntity rentalEntity8 = testTo.getRentalEntity(savedAgency, savedCar1, savedClient6);

        RentalEntity rentalEntity9 = testTo.getRentalEntity(savedAgency, savedCar2, savedClient1); //car2 - 5 rentals by different users
        RentalEntity rentalEntity10 = testTo.getRentalEntity(savedAgency, savedCar2, savedClient2); // 8 rental overall
        RentalEntity rentalEntity11 = testTo.getRentalEntity(savedAgency, savedCar2, savedClient3);
        RentalEntity rentalEntity12 = testTo.getRentalEntity(savedAgency, savedCar2, savedClient4);
        RentalEntity rentalEntity13 = testTo.getRentalEntity(savedAgency, savedCar2, savedClient5);
        RentalEntity rentalEntity14 = testTo.getRentalEntity(savedAgency, savedCar2, savedClient3);
        RentalEntity rentalEntity15 = testTo.getRentalEntity(savedAgency, savedCar2, savedClient4);
        RentalEntity rentalEntity16 = testTo.getRentalEntity(savedAgency, savedCar2, savedClient5);

        RentalEntity rentalEntity17 = testTo.getRentalEntity(savedAgency, savedCar3, savedClient1); //car2 - 4 rentals by different users
        RentalEntity rentalEntity18 = testTo.getRentalEntity(savedAgency, savedCar3, savedClient2); // 4 rental overall
        RentalEntity rentalEntity19 = testTo.getRentalEntity(savedAgency, savedCar3, savedClient3);
        RentalEntity rentalEntity20 = testTo.getRentalEntity(savedAgency, savedCar3, savedClient4);

        rentalDao.save(rentalEntity1);
        rentalDao.save(rentalEntity2);
        rentalDao.save(rentalEntity3);
        rentalDao.save(rentalEntity4);
        rentalDao.save(rentalEntity5);
        rentalDao.save(rentalEntity6);
        rentalDao.save(rentalEntity7);
        rentalDao.save(rentalEntity8);
        rentalDao.save(rentalEntity9);
        rentalDao.save(rentalEntity10);
        rentalDao.save(rentalEntity11);
        rentalDao.save(rentalEntity12);
        rentalDao.save(rentalEntity13);
        rentalDao.save(rentalEntity14);
        rentalDao.save(rentalEntity15);
        rentalDao.save(rentalEntity16);
        rentalDao.save(rentalEntity17);
        rentalDao.save(rentalEntity18);
        rentalDao.save(rentalEntity19);
        rentalDao.save(rentalEntity20);

        List<CarEntity> resultList_8 = carDao.findCarsRentedToDistinctClientsMoreThan(6L);
        List<CarEntity> resultList_7 = carDao.findCarsRentedToDistinctClientsMoreThan(5L);
        List<CarEntity> resultList_6 = carDao.findCarsRentedToDistinctClientsMoreThan(4L);
        List<CarEntity> resultList_5 = carDao.findCarsRentedToDistinctClientsMoreThan(3L);

        Assert.assertEquals((Integer)0, (Integer)resultList_8.size());
        Assert.assertEquals((Integer)1, (Integer)resultList_7.size());
        Assert.assertEquals((Integer)2, (Integer)resultList_6.size());
        Assert.assertEquals((Integer)3, (Integer)resultList_5.size());
    }

    @Test
    public void shouldFindCarsRentedInGivenTimePeriod() {
        //given
        CarEntity car1 = testTo.getCarEntity();
        CarEntity savedCar1 = carDao.save(car1);
        CarEntity car2 = testTo.getCarEntity();
        CarEntity savedCar2 = carDao.save(car2);
        CarEntity car3 = testTo.getCarEntity();
        CarEntity savedCar3 = carDao.save(car3);
        CarEntity car4 = testTo.getCarEntity();
        CarEntity savedCar4 = carDao.save(car4);
        CarEntity car5 = testTo.getCarEntity();
        CarEntity savedCar5 = carDao.save(car5);

        AgencyEntity agency = testTo.getAgencyEntity();
        AgencyEntity savedAgency = agencyDao.save(agency);

        RentalEntity rentalEntity1 = testTo.getRentalEntity(savedAgency, savedCar1, savedClient1);
        RentalEntity rentalEntity2 = testTo.getRentalEntity(savedAgency, savedCar2, savedClient2);
        RentalEntity rentalEntity3 = testTo.getRentalEntity(savedAgency, savedCar3, savedClient3);
        RentalEntity rentalEntity4 = testTo.getRentalEntity(savedAgency, savedCar4, savedClient4);
        RentalEntity rentalEntity5 = testTo.getRentalEntity(savedAgency, savedCar5, savedClient5);

    }
}
