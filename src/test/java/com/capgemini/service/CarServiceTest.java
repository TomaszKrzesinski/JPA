package com.capgemini.service;

import com.capgemini.types.CarTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.Builder;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarServiceTest {
    @Autowired
    CarService carService;

    @Test
    public void shouldAddCarAndReturnWithID() {
        //given
        CarTO car = getCar();
        //when
        CarTO savedCar = carService.addCar(car);
        //then
        Assert.assertEquals(savedCar.getId(), (Long)1L);
        Assert.assertNotNull(savedCar);
    }

    @Test
    public void shouldFindCarAddedToDatabase() {
        //given
        CarTO car = getCar();
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
        CarTO car = getCar();
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
        CarTO car = getCar();
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
        CarTO car = getCar();
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

    private CarTO getCar() {
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


}
