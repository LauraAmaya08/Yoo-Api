package proyectoSpring.Yoo.Api.service.interfaces;

import proyectoSpring.Yoo.Api.model.DTO.UserDTO;
import proyectoSpring.Yoo.Api.model.entities.User;

public interface IUserService {
        public User obtenerUsuario(String username);
        public UserDTO obtenerUsuarioDTO(String username);
        public User actualizarUser(String username, UserDTO user);

}
