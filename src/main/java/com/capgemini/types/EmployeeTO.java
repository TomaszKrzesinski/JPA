package com.capgemini.types;

import com.capgemini.domain.Address;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.RankEntity;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"agencyID"})
public class EmployeeTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    Date birthDate;
    Long agencyID;


    RankEntity rank;
}
