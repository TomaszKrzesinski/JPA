package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

    @Override
    public CarEntity setKeeper(Long carID, Long keeperID) {
        return null;
    }

    @Override
    public List<CarEntity> findCarsByKeeper() {
        return null;
    }

    @Override
    public List<CarEntity> findCarsByTypeAndBrand(String type, String brand) {
        return null;
    }
}

