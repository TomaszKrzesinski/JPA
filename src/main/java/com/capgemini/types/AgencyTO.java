package com.capgemini.types;

import com.capgemini.domain.Address;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.RentalEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgencyTO {
    private Long id;

    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgencyTO agencyTO = (AgencyTO) o;
        return Objects.equals(id, agencyTO.id) &&
                Objects.equals(address, agencyTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address);
    }
}
