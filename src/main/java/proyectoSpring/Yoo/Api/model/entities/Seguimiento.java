package proyectoSpring.Yoo.Api.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Table(name = "seguimiento", uniqueConstraints = @UniqueConstraint(columnNames = {"idUserSeguido","idUser"}))
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idUserSeguido", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User idUserSeguido;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User idUser;

    @CreationTimestamp
    @Column(name = "fechaSeguimiento", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fechaSeguimiento;

    public Seguimiento(User idUserSeguido, User idUser) {
        this.idUserSeguido = idUserSeguido;
        this.idUser = idUser;
        this.fechaSeguimiento = getFechaSeguimiento();
    }

    public Seguimiento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getIdUserSeguido() {
        return idUserSeguido;
    }

    public void setIdUserSeguido(User idUserSeguido) {
        this.idUserSeguido = idUserSeguido;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Timestamp getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Timestamp fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    @Override
    public String toString() {
        return "Seguimiento{" +
                "id=" + id +
                ", idUserSeguido=" + idUserSeguido +
                ", idUser=" + idUser +
                ", fechaSeguimiento=" + fechaSeguimiento +
                '}';
    }
}
