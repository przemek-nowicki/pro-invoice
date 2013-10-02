package pl.miwu.invoice.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 16.09.13
 * Time: 23:02
 */

@NamedQueries({@NamedQuery(name = UserRole.GET_BY_NAME, query="FROM UserRole WHERE name = :name")})

@Table(name = "roles")
@Entity
public class UserRole {

    public static final String GET_BY_NAME = "UserRole.GET_BY_NAME";

    private Integer id;
    private String name;
    private Set<User> users;

    @Id
    @Column(name="id")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="name",unique=true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy="userRole")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
