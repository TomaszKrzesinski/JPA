package com.capgemini.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Car")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, length = 50)
    private String brand;

    @Column(name = "production_year", nullable = false, length = 4)
    private Integer productionYear;

    @Column(nullable = false, length = 50)
    private String colour;

    @Column(name = "engine_capacity", nullable = false, precision = 1)
    private Double engineCapacity;

    @Column(nullable = false, length = 3)
    private Integer power;

    @Column(nullable = false, length = 7)
    private Integer millage;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @ManyToMany(mappedBy = "carsUnderKeep")
    private Set<EmployeeEntity> keepers = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "car")
    private Set<RentalEntity> rentals = new HashSet<>();

    @Version
    private Long version;

    public CarEntity(String type, String brand, Integer productionYear, String colour, Double engineCapacity, Integer power, Integer millage) {
        this.type = type;
        this.brand = brand;
        this.productionYear = productionYear;
        this.colour = colour;
        this.engineCapacity = engineCapacity;
        this.power = power;
        this.millage = millage;
    }

    public boolean addKeeper(EmployeeEntity employee) {
        employee.addCarUnderKeep(this);
        return keepers.add(employee);
    }

    public boolean removeKeeper(EmployeeEntity employee) {
        return keepers.remove(employee);
    }

    public boolean addCarRental(RentalEntity rental) {
        return rentals.add(rental);
    }

    public boolean removeRental(RentalEntity rental) {
        return rentals.remove(rental);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity entity = (CarEntity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


//    List<BookEntity> findBookByTitle(@Param("title") String title);
//
//    @org.springframework.data.jpa.repository.Query("select book from BookEntity book where upper(book.authors) like concat('%', upper(:author), '%')")
//    List<BookEntity> findBookByAuthor(@Param("author") String author);
//
//    @org.springframework.data.jpa.repository.Query("select book from BookEntity book where upper(book.authors) like concat('%', upper(:author), '%') " +
//            "AND upper(book.title) like concat('%', upper(:title), '%') AND status=:status")
//    List<BookEntity> findBookByAuthorTitleStatus(@Param("title") String title, @Param("author") String author,
//                                                 @Param("status")BookStatus status);
//
//    @Query("select book from BookEntity book where upper(book.authors) like concat('%', upper(:author), '%') " +
//            "AND upper(book.title) like concat('%', upper(:title), '%')")
//    List<BookEntity> findBookByAuthorTitle(@Param("title") String title, @Param("author") String author);
