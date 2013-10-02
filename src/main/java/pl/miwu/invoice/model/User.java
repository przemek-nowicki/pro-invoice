package pl.miwu.invoice.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 11.09.13
 * Time: 15:45
 */

@NamedQueries({@NamedQuery(name = User.GET_BY_USERNAME, query = "FROM User WHERE username = :username"),
               @NamedQuery(name=User.GET_BY_EMAIL,query="FROM User WHERE email = :email")})


@Table(name = "users")
@Entity
public class User {

    public static final String GET_BY_USERNAME = "User.GET_BY_USERNAME";
    public static final String GET_BY_EMAIL = "User.GET_BY_EMAIL";

    private Integer id;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
    private boolean enabled;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="email",unique=true)
    @Email
    @NotBlank
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="username",unique=true)
    @NotBlank
    @Length(min=3,max=64)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="password")
    @Length(min=3,max=64)
    @NotBlank
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="enabled")
    public boolean isEnabled(){
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ManyToOne
    @JoinColumn(name="id_role")
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
