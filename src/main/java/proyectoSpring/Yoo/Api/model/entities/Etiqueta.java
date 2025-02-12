package proyectoSpring.Yoo.Api.model.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "etiqueta")
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "texto", unique = true, length = 50, nullable = false)
    private String texto;

    @ManyToMany(mappedBy = "etiquetas")
    private List<Publicacion> publicaciones;

    public Etiqueta(Integer id, String texto, List<Publicacion> publicaciones) {
        this.id = id;
        this.texto = texto;
        this.publicaciones = publicaciones;
    }

    public Etiqueta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    @Override
    public String toString() {
        return "Etiqueta{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", publicaciones=" + publicaciones +
                '}';
    }
}
