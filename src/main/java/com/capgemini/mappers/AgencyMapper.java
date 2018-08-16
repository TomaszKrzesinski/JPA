package com.capgemini.mappers;

import com.capgemini.domain.Address;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.types.AgencyTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AgencyMapper implements Mapper<AgencyEntity, AgencyTO> {
    @Override
    public AgencyTO mapToTO(AgencyEntity entity) {
        return AgencyTO.builder()
                .id(entity.getId())
                .address(Address.builder()
                        .street(entity.getAddress().getStreet())
                        .city(entity.getAddress().getCity())
                        .postalCode(entity.getAddress().getPostalCode())
                        .country(entity.getAddress().getCountry())
                        .contactNumber(entity.getAddress().getContactNumber())
                        .build())
                .build();
    }

    @Override
    public AgencyEntity mapToEntity(AgencyTO to) {
        return AgencyEntity.builder()
                .id(to.getId())
                .address(Address.builder()
                        .street(to.getAddress().getStreet())
                        .city(to.getAddress().getCity())
                        .postalCode(to.getAddress().getPostalCode())
                        .country(to.getAddress().getCountry())
                        .contactNumber(to.getAddress().getContactNumber())
                        .build())
                .build();
    }

    @Override
    public List<AgencyTO> mapListToTO(List<AgencyEntity> entityList) {
        List<AgencyTO> agencyTOList = new ArrayList<>();
        for(AgencyEntity entity : entityList) {
            agencyTOList.add(mapToTO(entity));
        }
        return agencyTOList;
    }

    @Override
    public List<AgencyEntity> mapListToEntity(List<AgencyTO> toList) {
        List<AgencyEntity> agencyEntityList = new ArrayList<>();
        for(AgencyTO to : toList) {
            agencyEntityList.add(mapToEntity(to));
        }
        return agencyEntityList;
    }

    @Override
    public Set<AgencyTO> mapSetToTO(Set<AgencyEntity> entitySet) {
        Set<AgencyTO> agencyToSet = new HashSet<>();
        for(AgencyEntity entity : entitySet) {
            agencyToSet.add(mapToTO(entity));
        }
        return agencyToSet;
    }

    @Override
    public Set<AgencyEntity> mapSetToEntity(Set<AgencyTO> toSet) {
        Set<AgencyEntity> agencyEntitySet = new HashSet<>();
        for(AgencyTO to : toSet) {
            agencyEntitySet.add(mapToEntity(to));
        }
        return agencyEntitySet;
    }
}
