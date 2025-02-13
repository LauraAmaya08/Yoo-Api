package proyectoSpring.Yoo.Api.service.interfaces;

import proyectoSpring.Yoo.Api.model.entities.User;

public interface IUserService {
        public User obtenerUsuario(String username);
        public void actualizarUser(User user);

}
