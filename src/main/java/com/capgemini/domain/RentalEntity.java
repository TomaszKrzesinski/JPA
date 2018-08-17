package com.capgemini.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Rental")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    CarEntity car;

    @ManyToOne
    ClientEntity client;

    @Column(name = "date_from", nullable = false)
    Date dateFrom;

    @Column(name = "date_to")
    Date dateTo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @ManyToOne
    AgencyEntity agencyFrom;

    @ManyToOne
    AgencyEntity agencyTo;

    @Column(nullable = true, precision = 2)
    private Double cost;

}
