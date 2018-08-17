package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.RentalDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.RentalEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RentalDaoImpl extends AbstractDao<RentalEntity, Long> implements RentalDao {
}
