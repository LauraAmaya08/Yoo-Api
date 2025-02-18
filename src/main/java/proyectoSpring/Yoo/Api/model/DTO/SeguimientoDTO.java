package proyectoSpring.Yoo.Api.model.DTO;


public class SeguimientoDTO {
    private Integer id;
    private UserDTO seguido; // Usuario seguido
    private UserDTO seguidor; // Usuario que sigue

    // Constructor
    public SeguimientoDTO(Integer id, UserDTO seguido, UserDTO seguidor) {
        this.id = id;
        this.seguido = seguido;
        this.seguidor = seguidor;
    }

    public SeguimientoDTO() {
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getSeguido() {
        return seguido;
    }

    public void setSeguido(UserDTO seguido) {
        this.seguido = seguido;
    }

    public UserDTO getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(UserDTO seguidor) {
        this.seguidor = seguidor;
    }
}
