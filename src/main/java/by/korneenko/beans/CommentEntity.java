package by.korneenko.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "tasktrecker", catalog = "")
public class CommentEntity {
    private Long id;
    private String text;
    private Long idUser;
    private Long idTask;

    private UserEntity userByIdUser;
    private TaskEntity taskByIdTask;

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
    @Column(name = "text", nullable = false, length = 50)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "idUser", nullable = false)
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "idTask", nullable = false)
    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(text, that.text) &&
                Objects.equals(idUser, that.idUser) &&
                Objects.equals(idTask, that.idTask);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text, idUser, idTask);
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public UserEntity getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(UserEntity userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "idTask", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public TaskEntity getTaskByIdTask() {
        return taskByIdTask;
    }

    public void setTaskByIdTask(TaskEntity taskByIdTask) {
        this.taskByIdTask = taskByIdTask;
    }
}
