package com.capgemini.service;

import com.capgemini.dao.*;
import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

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
    ClientDao customerDao;

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

        CarEntity savedCar1 = carDao.save(car);






        //when




    }
}
