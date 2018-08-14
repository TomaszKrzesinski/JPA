package com.capgemini.service;

import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;

import java.util.List;

public interface AgencyService {
    AgencyTO addAgency(AgencyTO agency);

    void removeAgency(Long id);

    AgencyTO updateAgency(AgencyTO agency);

    EmployeeTO addEmployee(EmployeeTO employee);

    void removeEmployee(Long id);

    List<EmployeeTO> findAllAgencyEmployees(Long agencyID);

    List<EmployeeTO> findAllAgencyEmployeesKeepingCar(Long agencyID, Long carID);
}
