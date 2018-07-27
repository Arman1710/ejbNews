package epam.news.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "users")
@XmlRootElement
public class User extends Basic {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "userId", nullable = false, updatable = false)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column (nullable = false)
    private Long roleId;

    @Transient
    private String confirmPassword;

    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Role role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
