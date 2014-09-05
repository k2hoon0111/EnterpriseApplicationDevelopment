package lv.javaguru.ee.bookstore.core.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: MumboJumbo
 * Date: 05/09/14
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "permissions", uniqueConstraints = {@UniqueConstraint(columnNames = {"permission"})})
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String permission;

    Permission() {
        // Form ORM
    }

    public Permission(String permission) {
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
