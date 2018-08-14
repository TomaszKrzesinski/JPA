package com.capgemini.types;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.RentalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
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
}
