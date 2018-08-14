package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.mappers.Mapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private Mapper<CarEntity,CarTO> carMapper;
    private CarDao carDao;

    @Autowired
    public CarServiceImpl(CarDao carDao, Mapper<CarEntity,CarTO> carMapper) {
        this.carDao = carDao;
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
        return carMapper.mapToTO(carDao.update(carMapper.mapToEntity(car)));
    }

    @Override
    public CarTO setKeeper(Long carID, Long employeeID) {
        return null;
    }

    @Override
    public List<CarTO> findCarsByKeeper(Long keeperId) {
        return null;
    }

    @Override
    public List<CarTO> findCarsByTypeAndBrand(String type, String brand) {
        return null;
    }
}
