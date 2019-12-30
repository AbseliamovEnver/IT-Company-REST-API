package net.enver.itcompanydemo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Simple JavaBean domain object that represent Employee
 */

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
public class Employee {

    @Id
    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "hired_day")
    private Date hiredDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status")
    private EmployeeStatus employeeStatus;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "department_employees",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "department_id", referencedColumnName = "id")})
    @EqualsAndHashCode.Exclude
    private Set<Department> departments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_positions",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "position_id", referencedColumnName = "id")})
    @EqualsAndHashCode.Exclude
    private Set<Position> positions;
}
