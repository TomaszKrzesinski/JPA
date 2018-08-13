package com.capgemini.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Rank")
public class RankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    private String rank;

    @OneToMany(mappedBy = "rank")
    Set<EmployeeEntity> employees;

    public RankEntity() {
    }

    public RankEntity(String rank) {
        this.rank = rank;
        employees = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public boolean addEmployee(EmployeeEntity employee) {
        return employees.add(employee);
    }

    public boolean removeEmployee(EmployeeEntity employee) {
        return employees.remove(employee);
    }
}
