package proyectoSpring.Yoo.Api.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "publicacion")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "texto", nullable = false, length = 500)
    private String texto;

    @Column(name = "imagen", length = 100)
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User usuario;

    @CreationTimestamp
    @Column(name = "fechaCreacion", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fechaCreacion;

    @ManyToMany
    @JoinTable( name = "menciones_publicacion", joinColumns = @JoinColumn(name = "publicacion_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<User> menciones;

    @ManyToMany
    @JoinTable( name = "etiqueta_publicacion", joinColumns = @JoinColumn(name = "publicacion_id"), inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
    private List<Etiqueta> etiquetas;

    public Publicacion(List<Etiqueta> etiquetas, List<User> menciones, Timestamp fechaCreacion, User usuario, String imagen, String texto, Integer id) {
        this.etiquetas = etiquetas;
        this.menciones = menciones;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.imagen = imagen;
        this.texto = texto;
        this.id = id;
    }

    public Publicacion() {
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public List<User> getMenciones() {
        return menciones;
    }

    public void setMenciones(List<User> menciones) {
        this.menciones = menciones;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", imagen='" + imagen + '\'' +
                ", usuario=" + usuario +
                ", fechaCreacion=" + fechaCreacion +
                ", menciones=" + menciones +
                ", etiquetas=" + etiquetas +
                '}';
    }
}
