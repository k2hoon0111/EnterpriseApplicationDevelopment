package lv.javaguru.ee.bookstore.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"role"})})
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String role;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> permissions = new ArrayList<Permission>();

    Role() {
        // For ORM
    }

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
