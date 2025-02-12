package proyectoSpring.Yoo.Api.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Table(name = "notificacion")
public class Notificacion {
    public enum TipoNotificacion {
        like,
        comentario,
        seguimiento,
        mencion
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoNotificacion tipo;

    @Column(name = "idReferencia", nullable = false)
    private Integer idReferencia;

    @CreationTimestamp
    @Column(name = "fecha", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fecha;

    @Column(name = "leido", nullable = false)
    private Boolean leido;

    public Notificacion(Integer id, User user, TipoNotificacion tipo, Integer idReferencia, Timestamp fecha, Boolean leido) {
        this.id = id;
        this.user = user;
        this.tipo = tipo;
        this.idReferencia = idReferencia;
        this.fecha = fecha;
        this.leido = leido;
    }

    public Notificacion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TipoNotificacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoNotificacion tipo) {
        this.tipo = tipo;
    }

    public Integer getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Integer idReferencia) {
        this.idReferencia = idReferencia;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "id=" + id +
                ", user=" + user +
                ", tipo=" + tipo +
                ", idReferencia=" + idReferencia +
                ", fecha=" + fecha +
                ", leido=" + leido +
                '}';
    }
}
