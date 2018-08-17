package com.capgemini.service;

import com.capgemini.types.EmployeeSearchCriteria;
import com.capgemini.types.EmployeeTO;

import java.util.List;

public interface EmployeeService {
    EmployeeTO addEmployee(EmployeeTO employeeTO);

    void removeEmployee(Long id);

    EmployeeTO updateEmployee(EmployeeTO employeeTO);

    EmployeeTO getEmployee(Long id);

    List<EmployeeTO> getEmployees();

    List<EmployeeTO> searchEmployee(EmployeeSearchCriteria employeeSearchCriteria);
}
