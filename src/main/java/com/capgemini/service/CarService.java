package com.capgemini.service;

import com.capgemini.types.CarTO;

import java.util.List;

public interface CarService {
	CarTO getCarByID(Long id);

	List<CarTO> getAllCars();

	CarTO addCar(CarTO car);

	void removeCar(Long id);

	CarTO updateCar(CarTO car);

	CarTO setKeeper(Long carID, Long employeeID);

	List<CarTO> findCarsByKeeper(Long keeperId);

	List<CarTO> findCarsByTypeAndBrand(String type, String brand);

}
