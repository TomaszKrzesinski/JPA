package com.capgemini.service.impl;

import com.capgemini.dao.AgencyDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.mappers.Mapper;
import com.capgemini.service.AgencyService;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {
    private Mapper<AgencyEntity,AgencyTO> agencyMapper;
    private Mapper<EmployeeEntity,EmployeeTO> employeeMapper;
    private AgencyDao agencyDao;
    private EmployeeDao employeeDao;

    @Autowired
    public AgencyServiceImpl(Mapper<AgencyEntity, AgencyTO> agencyMapper,
                             Mapper<EmployeeEntity,EmployeeTO> employeeMapper,
                             AgencyDao agencyDao,
                             EmployeeDao employeeDao) {
        this.agencyMapper = agencyMapper;
        this.employeeMapper = employeeMapper;
        this.agencyDao = agencyDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public AgencyTO getAgency(Long id) {
        return agencyMapper.mapToTO(agencyDao.findOne(id));
    }

    @Override
    public List<AgencyTO> getAgencies() {
        return agencyMapper.mapListToTO(agencyDao.findAll());
    }

    @Override
    public AgencyTO addAgency(AgencyTO agency) {
        return agencyMapper.mapToTO(agencyDao.save(agencyMapper.mapToEntity(agency)));
    }

    @Override
    public void removeAgency(Long id) {
        agencyDao.delete(id);
    }

    @Override
    public AgencyTO updateAgency(AgencyTO agency) {
        return agencyMapper.mapToTO(agencyDao.update(agencyMapper.mapToEntity(agency)));
    }

    @Override
    public AgencyTO assignEmployee(Long agencyId, Long employeeId) {
        EmployeeEntity employee = employeeDao.findOne(employeeId);
        AgencyEntity agency = agencyDao.getOne(agencyId);
        if(employee == null || agency == null) return null;

        agency.addEmployee(employee);
        return agencyMapper.mapToTO(agency);
    }

    @Override
    public void removeEmployee(Long agencyId, Long employeeId) {
        EmployeeEntity employee = employeeDao.findOne(employeeId);
        AgencyEntity agency = agencyDao.getOne(agencyId);

        if(employee != null || agency != null) {
            agency.removeEmployee(employee);
            employee.setAgency(null);
        }
    }

    @Override
    public Set<EmployeeTO> findAllAgencyEmployees(Long agencyID) {
        AgencyEntity agency = agencyDao.getOne(agencyID);
        if(agency!=null) {
             return employeeMapper.mapSetToTO(agency.getEmployees());
        }
        return null;
    }

    @Override
    public List<EmployeeTO> findAllAgencyEmployeesKeepingCar(Long agencyID, Long carID) {
        return null;
    }


}
