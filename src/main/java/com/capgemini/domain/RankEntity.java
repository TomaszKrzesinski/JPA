package com.capgemini.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Rank")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    private String rank;

    @OneToMany(mappedBy = "rank")
    Set<EmployeeEntity> employees = new HashSet<>();

    public boolean addEmployee(EmployeeEntity employee) {
        return employees.add(employee);
    }

    public boolean removeEmployee(EmployeeEntity employee) {
        return employees.remove(employee);
    }
}
