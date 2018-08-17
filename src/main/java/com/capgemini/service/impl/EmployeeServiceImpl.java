package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.mappers.Mapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeSearchCriteria;
import com.capgemini.types.EmployeeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Mapper<EmployeeEntity, EmployeeTO> employeeMapper;
    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(Mapper<EmployeeEntity, EmployeeTO> employeeMapper, EmployeeDao employeeDao) {
        this.employeeMapper = employeeMapper;
        this.employeeDao = employeeDao;
    }

    @Override
    public EmployeeTO addEmployee(EmployeeTO employeeTO) {
        return employeeMapper.mapToTO(employeeDao.save(employeeMapper.mapToEntity(employeeTO)));
    }

    @Override
    public void removeEmployee(Long id) {
        employeeDao.delete(id);
    }

    @Override
    public EmployeeTO updateEmployee(EmployeeTO employeeTO) {
        return employeeMapper.mapToTO(employeeDao.update(employeeMapper.mapToEntity(employeeTO)));
    }

    @Override
    public EmployeeTO getEmployee(Long id) {
        return employeeMapper.mapToTO(employeeDao.findOne(id));
    }

    @Override
    public List<EmployeeTO> getEmployees() {
        return employeeMapper.mapListToTO(employeeDao.findAll());
    }

    @Override
    public List<EmployeeTO> searchEmployee(EmployeeSearchCriteria employeeSearchCriteria) {
        return employeeMapper.mapListToTO(employeeDao.searchEmployee(employeeSearchCriteria));
    }
}
