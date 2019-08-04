package entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @ManyToMany
    @JoinTable(name = "user_has_sources",
    joinColumns = {@JoinColumn(name="users_id")},
    inverseJoinColumns = {@JoinColumn(name="sources_id")})
    private Set<Source> sourceSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Source> getSourceSet() {
        return sourceSet;
    }

    public void setSourceSet(Set<Source> sourceSet) {
        this.sourceSet = sourceSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                password.equals(user.password) &&
                username.equals(user.username) &&
                Objects.equals(sourceSet, user.sourceSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, password, username, sourceSet);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", sourceSet=" + sourceSet +
                '}';
    }
}
