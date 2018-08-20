package com.capgemini.service;

import com.capgemini.types.CarTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CarService {
	CarTO getCarByID(Long id);

	List<CarTO> getAllCars();

	CarTO addCar(CarTO car);

	void removeCar(Long id);

	CarTO updateCar(CarTO car);

	CarTO assignKeeper(Long carID, Long employeeID);

	Set<CarTO> findCarsByKeeper(Long keeperId);

	List<CarTO> findCarsByTypeAndBrand(String type, String brand);

	Long countCarsRentedBetweenTimePeriod(Date searchDateFrom, Date searchDateTo);
}
