package com.capgemini.dao;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeSearchCriteria;

import java.util.List;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {
    List<EmployeeEntity> searchEmployee(EmployeeSearchCriteria employeeSearchCriteria);
}
