package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.mappers.Mapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private Mapper<CarEntity,CarTO> carMapper;
    private EmployeeDao employeeDao;
    private CarDao carDao;


    @Autowired
    public CarServiceImpl(CarDao carDao, EmployeeDao employeeDao, Mapper<CarEntity, CarTO> carMapper) {
        this.carDao = carDao;
        this.employeeDao = employeeDao;
        this.carMapper = carMapper;
    }

    @Override
    public CarTO getCarByID(Long id) {
        return carMapper.mapToTO(carDao.findOne(id));
    }

    @Override
    public List<CarTO> getAllCars() {
        return carMapper.mapListToTO(carDao.findAll());
    }

    @Override
    public CarTO addCar(CarTO carTO) {
        CarEntity carEntity = carMapper.mapToEntity(carTO);
        return carMapper.mapToTO(carDao.save(carEntity));
    }

    @Override
    public void removeCar(Long id) {
        carDao.delete(id);
    }

    @Override
    public CarTO updateCar(CarTO car) {
        CarEntity carEntity = carDao.findOne(car.getId());
        updateEntity(carEntity,car);
        return carMapper.mapToTO(carEntity);
    }

    @Override
    public CarTO assignKeeper(Long carID, Long employeeID) {
        CarEntity car = carDao.findOne(carID);
        car.addKeeper(employeeDao.getOne(employeeID));
        return carMapper.mapToTO(car);
    }

    @Override
    public Set<CarTO> findCarsByKeeper(Long keeperId) {
        EmployeeEntity employee = employeeDao.getOne(keeperId);
        Set<CarEntity> cars = null;
        if(employee != null) {
            cars = employee.getCarsUnderKeep();
        }
        return carMapper.mapSetToTO(cars);
    }

    @Override
    public List<CarTO> findCarsByTypeAndBrand(String type, String brand) {
        return carMapper.mapListToTO(carDao.findCarsByTypeAndBrand(type,brand));
    }

    @Override
    public Long countCarsRentedBetweenTimePeriod(Date searchDateFrom, Date searchDateTo) {
        return carDao.countCarsRentedBetweenTimePeriod(searchDateFrom, searchDateTo);
    }

    private void updateEntity(CarEntity entity, CarTO carTO) {
        entity.setType(carTO.getType());
        entity.setBrand(carTO.getBrand());
        entity.setProductionYear(carTO.getProductionYear());
        entity.setColour(carTO.getColour());
        entity.setEngineCapacity(carTO.getEngineCapacity());
        entity.setPower(carTO.getPower());
        entity.setMillage(carTO.getMillage());
    }
}
