package com.capgemini.service;

import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;

import java.util.List;
import java.util.Set;

public interface AgencyService {
    AgencyTO getAgency(Long id);

    List<AgencyTO> getAgencies();

    AgencyTO addAgency(AgencyTO agency);

    void removeAgency(Long id);

    AgencyTO updateAgency(AgencyTO agency);

    AgencyTO assignEmployee(Long agencyId, Long employeeId);

    void removeEmployee(Long agencyId, Long employeeId);

    Set<EmployeeTO> findAllAgencyEmployees(Long agencyID);

    List<EmployeeTO> findAllAgencyEmployeesKeepingCar(Long agencyID, Long carID);
}
