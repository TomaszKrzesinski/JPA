package com.capgemini.service.impl;

import com.capgemini.dao.AgencyDao;
import com.capgemini.dao.CarDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.mappers.Mapper;
import com.capgemini.service.AgencyService;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {
    private Mapper<AgencyEntity,AgencyTO> agencyMapper;
    private AgencyDao agencyDao;

    @Autowired
    public AgencyServiceImpl(Mapper<AgencyEntity, AgencyTO> agencyMapper, AgencyDao agencyDao) {
        this.agencyMapper = agencyMapper;
        this.agencyDao = agencyDao;
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
    public EmployeeTO addEmployee(Long agencyId, Long employeeId) {
        return null;
    }

    @Override
    public void removeEmployee(Long agencyId, Long employeeId) {

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
