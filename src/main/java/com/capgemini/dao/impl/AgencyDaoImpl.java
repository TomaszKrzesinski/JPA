package com.capgemini.dao.impl;

import com.capgemini.dao.AgencyDao;
import com.capgemini.dao.CarDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.types.EmployeeTO;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AgencyDaoImpl extends AbstractDao<AgencyEntity, Long> implements AgencyDao {

    @Override
    public List<EmployeeTO> findAllAgencyEmployeesKeepingCar(Long agencyID, Long carID) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "SELECT e " +
                        "FROM EmployeeEntity e " +
                        "INNER JOIN " +
                        "AND UPPER(c.brand) LIKE CONCAT('%', UPPER(:brand), '%')", CarEntity.class);
        query.setParameter("type", type);
        query.setParameter("brand", brand);
    }


    public List<CarEntity> findCarsByTypeAndBrand(String type, String brand) {
        TypedQuery<CarEntity> query = entityManager.createQuery("SELECT c FROM CarEntity c WHERE UPPER(c.type) LIKE CONCAT('%', UPPER(:type), '%') " +
                "AND UPPER(c.brand) LIKE CONCAT('%', UPPER(:brand), '%')", CarEntity.class);
        query.setParameter("type", type);
        query.setParameter("brand", brand);

        return query.getResultList();
    }
}
