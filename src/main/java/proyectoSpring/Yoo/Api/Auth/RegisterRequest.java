package proyectoSpring.Yoo.Api.Auth;

import java.time.LocalDate;

public class RegisterRequest {
    String nombreUser;
    String nombre;
    String password;
    String email;
    LocalDate fechaNac;
    String telefono;


    public RegisterRequest(String username, String nombre, String password, String email, LocalDate fechaNac, String telefono) {
        this.nombreUser = username;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
    }

    public RegisterRequest() {
    }

    public String getUsername() {
        return nombreUser;
    }

    public void setUsername(String username) {
        this.nombreUser = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
