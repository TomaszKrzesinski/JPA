package com.capgemini.dao;


import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.EmployeeEntity;

import java.util.List;

public interface AgencyDao extends Dao<AgencyEntity, Long> {
    List<EmployeeEntity> findAllAgencyEmployeesKeepingCar(Long agencyID, Long carID);
}
