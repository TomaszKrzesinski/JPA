package com.capgemini.types;

import com.capgemini.domain.Address;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.RentalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgencyTO {
    private Long id;

    private Address address;
}
