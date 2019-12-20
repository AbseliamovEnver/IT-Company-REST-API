package net.enver.itcompanydemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean domain object that represent Department
 *
 * @author Enver on 12.12.2019 14:47.
 * @project ITCompanyDemo
 */

@Entity
@Table(name = "departments")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.LAZY)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<User> users;

    public Department() {
    }
}
