package com.capgemini.dao;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.RentalEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalDao extends Dao<RentalEntity, Long> {
}
