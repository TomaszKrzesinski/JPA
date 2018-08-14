package com.capgemini.service;

import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;

import java.util.List;

public interface AgencyService {
    AgencyTO getAgency(Long id);

    List<AgencyTO> getAgencies();

    AgencyTO addAgency(AgencyTO agency);

    void removeAgency(Long id);

    AgencyTO updateAgency(AgencyTO agency);

    EmployeeTO addEmployee(Long agencyId, Long employeeId);

    void removeEmployee(Long agencyId, Long employeeId);

    List<EmployeeTO> findAllAgencyEmployees(Long agencyID);

    List<EmployeeTO> findAllAgencyEmployeesKeepingCar(Long agencyID, Long carID);
}
