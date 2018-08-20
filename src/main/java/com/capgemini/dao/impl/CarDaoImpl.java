package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
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

    @Override
    public List<CarEntity> findCarsRentedToDistinctClientsMoreThan(Long distinctClientsNumber) {

        String query = "SELECT crs " +
                "FROM CarEntity crs " +
                "INNER JOIN crs.rentals rnt " +
                "group by crs.id " +
                "having COUNT(distinct rnt.client.id) > :disClNumb";


        List<CarEntity> resultList = entityManager.createQuery(query, CarEntity.class)
            .setParameter("disClNumb", distinctClientsNumber)
            .getResultList();

        return resultList;
    }

    @Override
    public Long countCarsRentedBetweenTimePeriod(Date searchDateFrom, Date searchDateTo) {
        String query = "SELECT COUNT(distinct crs.id) " +
                "FROM CarEntity crs " +
                "LEFT JOIN crs.rentals rnt " +
                "where rnt.dateFrom <= :searchDateTo AND rnt.dateTo >= :searchDateFrom";

        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("searchDateTo", searchDateTo)
                .setParameter("searchDateFrom", searchDateFrom)
                .getSingleResult();

        return count;
    }
}

