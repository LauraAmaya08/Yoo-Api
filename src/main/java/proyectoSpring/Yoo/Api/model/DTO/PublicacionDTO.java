package proyectoSpring.Yoo.Api.model.DTO;

import java.sql.Timestamp;
import java.time.LocalDate;

public class PublicacionDTO {
    private Integer id;
    private String contenido;
    private Timestamp fecha;

    // Constructor
    public PublicacionDTO(Integer id, String contenido, Timestamp fecha, String imagen) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public PublicacionDTO() {
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
