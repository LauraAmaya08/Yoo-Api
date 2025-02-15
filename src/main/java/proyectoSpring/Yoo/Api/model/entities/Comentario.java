package proyectoSpring.Yoo.Api.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "publicacion", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToMany
    @JoinTable(name = "comentario_menciones", joinColumns = @JoinColumn(name = "comentario_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<User> usuariosMencionados;


    @CreationTimestamp
    @Column(name = "fecha", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fecha;

    @Column(name = "texto", nullable = false, length = 200)
    private String texto;

    public Comentario(Integer id, Publicacion publicacion, User user, List<User> usuariosMencionados, Timestamp fecha, String texto) {
        this.id = id;
        this.publicacion = publicacion;
        this.user = user;
        this.usuariosMencionados = usuariosMencionados;
        this.fecha = fecha;
        this.texto = texto;
    }

    public Comentario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsuariosMencionados() {
        return usuariosMencionados;
    }

    public void setUsuariosMencionados(List<User> usuariosMencionados) {
        this.usuariosMencionados = usuariosMencionados;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", publicacion=" + publicacion +
                ", user=" + user +
                ", usuariosMencionados=" + usuariosMencionados +
                ", fecha=" + fecha +
                ", texto='" + texto + '\'' +
                '}';
    }
}
