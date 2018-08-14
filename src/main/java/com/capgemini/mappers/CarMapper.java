package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CarMapper implements Mapper<CarEntity, CarTO> {

    @Override
    public CarTO mapToTO(CarEntity entity) {
        CarTO to = CarTO.builder()
                .id(entity.getId())
                .type(entity.getType())
                .brand(entity.getBrand())
                .colour(entity.getColour())
                .engineCapacity(entity.getEngineCapacity())
                .millage(entity.getMillage())
                .power(entity.getPower())
                .productionYear(entity.getProductionYear())
                .build();
        return to;
    }

    @Override
    public CarEntity mapToEntity(CarTO to) {
        CarEntity entity = CarEntity.builder()
                .id(to.getId())
                .type(to.getType())
                .brand(to.getBrand())
                .colour(to.getColour())
                .engineCapacity(to.getEngineCapacity())
                .millage(to.getMillage())
                .power(to.getPower())
                .productionYear(to.getProductionYear())
                .build();
        return entity;
    }

    @Override
    public List<CarTO> mapListToTO(List<CarEntity> entityList) {
        List<CarTO> carTOList = new ArrayList<>();
        for(CarEntity entity : entityList) {
            carTOList.add(mapToTO(entity));
        }
        return carTOList;
    }

    @Override
    public List<CarEntity> mapListToEntity(List<CarTO> toList) {
        List<CarEntity> carEntityList = new ArrayList<>();
        for(CarTO to : toList) {
            carEntityList.add(mapToEntity(to));
        }
        return carEntityList;
    }

    @Override
    public Set<CarTO> mapSetToTO(Set<CarEntity> entitySet) {
        return null;
    }

    @Override
    public Set<CarEntity> mapSetToEntity(Set<CarTO> toSet) {
        return null;
    }
}
