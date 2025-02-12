package proyectoSpring.Yoo.Api.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "nombreUser", nullable = false, unique = true, length = 100)
    private String nombreUser;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "biografia", length = 300)
    private String biografia;

    @Column(name = "fotoPerfil")
    private String fotoPerfil;

    @Column(name = "fechaNac", nullable = false)
    private LocalDate fechaNac;

    @Column(name = "telefono", length = 13)
    private String telefono;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publicacion> publicaciones;

    public User(Integer id, String nombre, String nombreUser, String email, String password, String biografia, String fotoPerfil, LocalDate fechaNac, String telefono, List<Publicacion> publicaciones) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUser = nombreUser;
        this.email = email;
        this.password = password;
        this.biografia = biografia;
        this.fotoPerfil = fotoPerfil;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.publicaciones = publicaciones;
    }

    public User() {
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nombreUser='" + nombreUser + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", biografia='" + biografia + '\'' +
                ", fotoPerfil='" + fotoPerfil + '\'' +
                ", fechaNac=" + fechaNac +
                ", telefono='" + telefono + '\'' +
                ", publicaciones=" + publicaciones +
                '}';
    }
}
