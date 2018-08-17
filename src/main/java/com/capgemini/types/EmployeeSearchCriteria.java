package com.capgemini.types;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchCriteria {
    private Long agencyId;
    private Long carUnderCare;
    private String rank;
}


