package com.capgemini.dao.impl;

import com.capgemini.dao.AgencyDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AgencyDaoImpl extends AbstractDao<AgencyEntity, Long> implements AgencyDao {

    @Override
    public List<EmployeeEntity> findAllAgencyEmployeesKeepingCar(Long agencyID, Long carID) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "SELECT e " +
                        "FROM EmployeeEntity e " +
                        "JOIN e.carsUnderKeep cars " +
                        "where cars.id = :carID AND e.agency.id = :agencyID",
                EmployeeEntity.class);
        query.setParameter("carID", carID);
        query.setParameter("agencyID", agencyID);
        return query.getResultList();
    }

    @Override
    public Integer getRentalsFromCount(Long agencyID) {
        AgencyEntity agency = findOne(agencyID);
        return agency.getRentalsFrom().size();
    }

    @Override
    public Integer getRentalsToCount(Long agencyID) {
        AgencyEntity agency = findOne(agencyID);
        return agency.getRentalsTo().size();
    }


    public List<CarEntity> findCarsByTypeAndBrand(String type, String brand) {
        TypedQuery<CarEntity> query = entityManager.createQuery("SELECT c FROM CarEntity c WHERE UPPER(c.type) LIKE CONCAT('%', UPPER(:type), '%') " +
                "AND UPPER(c.brand) LIKE CONCAT('%', UPPER(:brand), '%')", CarEntity.class);
        query.setParameter("type", type);
        query.setParameter("brand", brand);

        return query.getResultList();
    }
}
