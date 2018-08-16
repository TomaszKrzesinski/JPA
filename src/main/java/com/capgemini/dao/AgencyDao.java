package com.capgemini.dao;


import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;

import java.util.List;
import java.util.Set;

public interface AgencyDao extends Dao<AgencyEntity, Long> {
    List<EmployeeTO> findAllAgencyEmployeesKeepingCar(Long agencyID, Long carID);
}
