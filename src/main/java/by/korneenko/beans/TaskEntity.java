package by.korneenko.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "task", schema = "tasktrecker", catalog = "")
public class TaskEntity {
    private Long id;
    private String name;
    private String taskText;
    private String status;
    private Long idProject;


    private List<CommentEntity> commentsById;
    private ProjectEntity projectByIdProject;
    private List<UserEntity> userById;

    @Id
    @GeneratedValue
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
    @Column(name = "taskText", nullable = false, length = 255)
    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    @Basic
    @Column(name = "idProject", nullable = false)
    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(taskText, that.taskText) &&
                Objects.equals(idProject, that.idProject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, taskText, idProject);
    }

    @OneToMany(mappedBy = "task")
    @JsonIgnore
    public List<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(List<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProject", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public ProjectEntity getProjectByIdProject() {
        return projectByIdProject;
    }

    public void setProjectByIdProject(ProjectEntity projectByIdProject) {
        this.projectByIdProject = projectByIdProject;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_task",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idTask"))
    @JsonIgnore
    public List<UserEntity> getUserById() {return userById; }

    public void setUserById(List<UserEntity> userById) { this.userById = userById;}
}
