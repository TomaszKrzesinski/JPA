package com.capgemini.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Rank")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    private String rank;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @OneToMany(mappedBy = "rank")
    Set<EmployeeEntity> employees = new HashSet<>();

    public boolean addEmployee(EmployeeEntity employee) {
        return employees.add(employee);
    }

    public boolean removeEmployee(EmployeeEntity employee) {
        return employees.remove(employee);
    }
}
