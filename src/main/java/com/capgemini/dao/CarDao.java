package com.capgemini.dao;

import com.capgemini.domain.CarEntity;

import java.util.Date;
import java.util.List;

public interface CarDao extends Dao<CarEntity, Long> {
    CarEntity setKeeper(Long carID, Long keeperID);

    List<CarEntity> findCarsByKeeper();

    List<CarEntity> findCarsByTypeAndBrand(String type, String brand);

    Integer getRentalsCount(Long carID);

    List<CarEntity> findCarsRentedToDistinctClientsMoreThan(Long distinctClientsNumber);

    Long countCarsRentedBetweenTimePeriod(Date searchDateFrom, Date searchDateTo);
}

