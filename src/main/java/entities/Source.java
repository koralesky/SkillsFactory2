package entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sources")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column()
    private String description;
    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "sources_has_skills",
            joinColumns = {@JoinColumn(name = "sources_id")},
            inverseJoinColumns = {@JoinColumn(name = "skills_id")})
    private Set<Skill> skills = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", skills=" + skills +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(id, source.id) &&
                Objects.equals(description, source.description) &&
                name.equals(source.name) &&
                Objects.equals(skills, source.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, name, skills);
    }
}