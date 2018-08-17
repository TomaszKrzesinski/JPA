package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

    @Override
    public CarEntity setKeeper(Long carID, Long keeperID) {
        return null;
    }

    @Override
    public List<CarEntity> findCarsByKeeper() {
        return null;
    }

    @Override
    public List<CarEntity> findCarsByTypeAndBrand(String type, String brand) {
        TypedQuery<CarEntity> query = entityManager.createQuery("SELECT c FROM CarEntity c WHERE UPPER(c.type) LIKE CONCAT('%', UPPER(:type), '%') " +
                "AND UPPER(c.brand) LIKE CONCAT('%', UPPER(:brand), '%')", CarEntity.class);
        query.setParameter("type", type);
        query.setParameter("brand", brand);

        return query.getResultList();
    }

    @Override
    public Integer getRentalsCount(Long carID) {
        CarEntity car = findOne(carID);
        if (car.getRentals() != null) {
            return car.getRentals().size();
        }
        return 0;
    }
}

