package lv.javaguru.ee.bookshop.core.domain;

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
    @Column(name = "roleId")
    private Long roleId;

    private String role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "roles_permissions", joinColumns = {
            @JoinColumn(name = "roleId", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "permissionId",
                    nullable = false, updatable = false)})
    private transient List<Permission> permissions = new ArrayList<Permission>(0);

    Role() {
        // For ORM
    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
