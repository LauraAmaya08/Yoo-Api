package proyectoSpring.Yoo.Api.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Table(name = "likes",  uniqueConstraints = @UniqueConstraint(columnNames = {"publicacion","user"}))
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "publicacion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @CreationTimestamp
    @Column(name = "fechaInteraccion", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fechaInteraccion;

    public Likes(Integer id, Publicacion publicacion, User user, Timestamp fechaInteraccion) {
        this.id = id;
        this.publicacion = publicacion;
        this.user = user;
        this.fechaInteraccion = fechaInteraccion;
    }

    public Likes() {
    }

    public Timestamp getFechaInteraccion() {
        return fechaInteraccion;
    }

    public void setFechaInteraccion(Timestamp fechaInteraccion) {
        this.fechaInteraccion = fechaInteraccion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", publicacion=" + publicacion +
                ", user=" + user +
                ", fechaInteraccion=" + fechaInteraccion +
                '}';
    }
}
