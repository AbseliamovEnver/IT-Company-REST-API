package net.enver.itcompanydemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean object that represent work position of {@link User}.
 */

@Entity
@Table(name = "positions")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Position extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "positions", fetch = FetchType.LAZY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Employee> employees;
}
