package com.capgemini.dao;

import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CarDao extends Dao<CarEntity, Long> {
    CarEntity setKeeper(Long carID, Long keeperID);

    List<CarEntity> findCarsByKeeper();

    List<CarEntity> findCarsByTypeAndBrand(String type, String brand);
}

