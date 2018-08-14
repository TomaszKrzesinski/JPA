package com.capgemini.service.impl;

import com.capgemini.service.AgencyService;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;

import java.util.List;

public class AgencyServiceImpl implements AgencyService {

    @Override
    public AgencyTO addAgency(AgencyTO agency) {
        return null;
    }

    @Override
    public void removeAgency(Long id) {

    }

    @Override
    public AgencyTO updateAgency(AgencyTO agency) {
        return null;
    }

    @Override
    public EmployeeTO addEmployee(EmployeeTO employee) {
        return null;
    }

    @Override
    public void removeEmployee(Long id) {

    }

    @Override
    public List<EmployeeTO> findAllAgencyEmployees(Long agencyID) {
        return null;
    }

    @Override
    public List<EmployeeTO> findAllAgencyEmployeesKeepingCar(Long agencyID, Long carID) {
        return null;
    }
}
