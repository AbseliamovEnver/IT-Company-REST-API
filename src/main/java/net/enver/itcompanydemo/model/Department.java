package net.enver.itcompanydemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean domain object that represent Department
 */

@Entity
@Table(name = "departments")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.LAZY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<User> users;
}
