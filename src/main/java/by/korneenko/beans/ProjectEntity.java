package by.korneenko.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project", schema = "tasktrecker", catalog = "")
public class ProjectEntity {
    private Long id;
    private String name;
    private String description;
    private Long idUserCreate;

    private UserEntity userByIdUserCreate;
    private List<TaskEntity> tasksById;
    private List<UserEntity> usersById;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "idUserCreate", nullable = false)
    public Long getIdUserCreate() {
        return idUserCreate;
    }

    public void setIdUserCreate(Long idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(idUserCreate, that.idUserCreate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, idUserCreate);
    }

    @ManyToOne
    @JoinColumn(name = "idUserCreate", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public UserEntity getUserByIdUserCreate() {
        return userByIdUserCreate;
    }

    public void setUserByIdUserCreate(UserEntity userByIdUserCreate) {
        this.userByIdUserCreate = userByIdUserCreate;
    }

    @OneToMany(mappedBy = "projectByIdProject", fetch = FetchType.LAZY)
    @JsonIgnore
    public List<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(List<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "idProject"),
            inverseJoinColumns = @JoinColumn(name = "idUser"))
    @JsonIgnore
    public List<UserEntity> getUsersById() { return usersById;}

    public void setUsersById(List<UserEntity> usersById) { this.usersById = usersById; }
}
