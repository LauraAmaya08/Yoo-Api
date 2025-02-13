package proyectoSpring.Yoo.Api.service.services;

import org.springframework.stereotype.Service;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.UserRepository;
import proyectoSpring.Yoo.Api.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User obtenerUsuario(String username) {
        return userRepository.findByNombreUser(username).orElseThrow(null);
    }

    @Override
    public User actualizarUser(String username, User datosActualizados) {
        User usuarioExistente = obtenerUsuario(username);
        assert usuarioExistente != null;
        usuarioExistente.setNombre(datosActualizados.getNombre());
        usuarioExistente.setBiografia(datosActualizados.getBiografia());
        usuarioExistente.setFotoPerfil(datosActualizados.getFotoPerfil());
        usuarioExistente.setTelefono(datosActualizados.getTelefono());
        return userRepository.save(usuarioExistente);
    }
}
