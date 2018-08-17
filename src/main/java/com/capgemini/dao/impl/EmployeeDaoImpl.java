package com.capgemini.dao.impl;

import com.capgemini.dao.AgencyDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeSearchCriteria;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {
    @Override
    public List<EmployeeEntity> searchEmployee(EmployeeSearchCriteria employeeSearchCriteria) {
        String query = "SELECT emp " +
                "FROM EmployeeEntity emp " +
                "JOIN emp.carsUnderKeep crs " +
                "JOIN emp.rank rnk where";
  //             "where emp.agency.id = :agencyID and crs.id = :carID " +
   //             "and UPPER(rnk.rank) LIKE CONCAT('%', UPPER(:rankName), '%') ";
        String agencyCondition = "";
        boolean agencyExist = false;
        if(employeeSearchCriteria.getAgencyId() != null) {
            agencyCondition = " emp.agency.id = :agencyID";
            agencyExist = true;
        }
        String carCondition = "";
        boolean carExist = false;
        if(employeeSearchCriteria.getCarUnderCare() != null) {
            carCondition = " crs.id = :carID";
            carExist = true;
            if(agencyExist) carCondition = " AND" + carCondition;
        }
        String rankCondition = "";
        boolean rankExist = false;
        if(employeeSearchCriteria.getRank() != null) {
            rankCondition = " UPPER(rnk.rank) LIKE CONCAT('%', UPPER(:rankName), '%') ";
            rankExist = true;
            if(agencyExist || carExist) rankCondition = " AND" + rankCondition;
        }
        query = query + agencyCondition + carCondition + rankCondition;
        System.out.println(query);
        TypedQuery<EmployeeEntity> typedQuery = entityManager.createQuery(query, EmployeeEntity.class);
        if(agencyExist) typedQuery.setParameter("agencyID", employeeSearchCriteria.getAgencyId());
        if(carExist)typedQuery.setParameter("carID", employeeSearchCriteria.getCarUnderCare());
        if(rankExist)typedQuery.setParameter("rankName", employeeSearchCriteria.getRank());
        return typedQuery.getResultList();
    }
}
