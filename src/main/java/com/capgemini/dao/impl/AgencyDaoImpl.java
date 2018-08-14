package com.capgemini.dao.impl;

import com.capgemini.dao.AgencyDao;
import com.capgemini.dao.CarDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AgencyDaoImpl extends AbstractDao<AgencyEntity, Long> implements AgencyDao {

}
