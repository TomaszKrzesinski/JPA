package com.capgemini.mappers;

import com.capgemini.domain.Address;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.AgencyTO;
import com.capgemini.types.EmployeeTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EmployeeMapper implements Mapper<EmployeeEntity, EmployeeTO> {

    @Override
    public EmployeeTO mapToTO(EmployeeEntity entity) {
        return EmployeeTO.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .address(Address.builder()
                        .street(entity.getAddress().getStreet())
                        .city(entity.getAddress().getCity())
                        .postalCode(entity.getAddress().getPostalCode())
                        .country(entity.getAddress().getCountry())
                        .contactNumber(entity.getAddress().getContactNumber())
                        .build())
                .agency(entity.getAgency())
                .birthDate(entity.getBirthDate())
                .rank(entity.getRank())
                .build();
}

    @Override
    public EmployeeEntity mapToEntity(EmployeeTO to) {
        return EmployeeEntity.builder()
                .firstName(to.getFirstName())
                .lastName(to.getLastName())
                .address(Address.builder()
                        .street(to.getAddress().getStreet())
                        .city(to.getAddress().getCity())
                        .postalCode(to.getAddress().getPostalCode())
                        .country(to.getAddress().getCountry())
                        .contactNumber(to.getAddress().getContactNumber())
                        .build())
                .agency(to.getAgency())
                .birthDate(to.getBirthDate())
                .rank(to.getRank())
                .build();
    }

    @Override
    public List<EmployeeTO> mapListToTO(List<EmployeeEntity> entityList) {
        List<EmployeeTO> employeeTOList = new ArrayList<>();
        for(EmployeeEntity entity : entityList) {
            employeeTOList.add(mapToTO(entity));
        }
        return employeeTOList;
    }

    @Override
    public List<EmployeeEntity> mapListToEntity(List<EmployeeTO> toList) {
        List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        for(EmployeeTO to : toList) {
            employeeEntityList.add(mapToEntity(to));
        }
        return employeeEntityList;
    }

    @Override
    public Set<EmployeeTO> mapSetToTO(Set<EmployeeEntity> entitySet) {
        return null;
    }

    @Override
    public Set<EmployeeEntity> mapSetToEntity(Set<EmployeeTO> toSet) {
        return null;
    }
}
