package proyectoSpring.Yoo.Api.service.interfaces;

import proyectoSpring.Yoo.Api.model.entities.User;

public interface IUserService {
        public User obtenerUsuario(String username);
        public User actualizarUser(String username, User user);

}
