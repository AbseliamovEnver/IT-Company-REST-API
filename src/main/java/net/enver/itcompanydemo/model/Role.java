package net.enver.itcompanydemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean object that represent role of {@link User}.
 */

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<User> users;
}
