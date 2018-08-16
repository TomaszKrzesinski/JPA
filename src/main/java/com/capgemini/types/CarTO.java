package com.capgemini.types;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.RentalEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarTO {
    private Long id;
    private String type;
    private String brand;
    private Integer productionYear;
    private String colour;
    private Double engineCapacity;
    private Integer power;
    private Integer millage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarTO carTO = (CarTO) o;
        return Objects.equals(id, carTO.id) &&
                Objects.equals(type, carTO.type) &&
                Objects.equals(brand, carTO.brand) &&
                Objects.equals(productionYear, carTO.productionYear) &&
                Objects.equals(colour, carTO.colour) &&
                Objects.equals(engineCapacity, carTO.engineCapacity) &&
                Objects.equals(power, carTO.power) &&
                Objects.equals(millage, carTO.millage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, brand, productionYear, colour, engineCapacity, power, millage);
    }
}
