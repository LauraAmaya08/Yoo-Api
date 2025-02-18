package proyectoSpring.Yoo.Api.model.DTO;

import proyectoSpring.Yoo.Api.model.entities.Publicacion;

import java.time.LocalDate;
import java.util.List;

public class UserDTO {

    private Integer id;
    private String nombre;
    private String nombreUser;
    private String email;
    private String biografia;
    private String fotoPerfil;
    private LocalDate fechaNac;
    private String telefono;
    private List<Publicacion> publicaciones;

    public UserDTO(Integer id, String nombre, String nombreUser, String email, String biografia,
                   String fotoPerfil, LocalDate fechaNac, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUser = nombreUser;
        this.email = email;
        this.biografia = biografia;
        this.fotoPerfil = fotoPerfil;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
    }

    public UserDTO(Integer id, String nombre, String nombreUser, String email, String biografia, String fotoPerfil, LocalDate fechaNac, String telefono, List<Publicacion> publicaciones) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUser = nombreUser;
        this.email = email;
        this.biografia = biografia;
        this.fotoPerfil = fotoPerfil;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.publicaciones = publicaciones;
    }

    public UserDTO() {
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nombreUser='" + nombreUser + '\'' +
                ", email='" + email + '\'' +
                ", biografia='" + biografia + '\'' +
                ", fotoPerfil='" + fotoPerfil + '\'' +
                ", fechaNac=" + fechaNac +
                ", telefono='" + telefono + '\'' +
                ", publicaciones=" + publicaciones +
                '}';
    }
}
