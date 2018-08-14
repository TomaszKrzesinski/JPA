package com.capgemini.types;

import com.capgemini.domain.Address;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.RankEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    Date birthDate;
    AgencyEntity agency;
    RankEntity rank;
}
