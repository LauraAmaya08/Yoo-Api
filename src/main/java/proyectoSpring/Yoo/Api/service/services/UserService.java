package proyectoSpring.Yoo.Api.service.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import proyectoSpring.Yoo.Api.model.DTO.UserDTO;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.UserRepository;
import proyectoSpring.Yoo.Api.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    private UserDTO convertToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getNombre(), user.getUsername(), user.getEmail(), user.getBiografia(), user.getFotoPerfil(), user.getFechaNac(), user.getTelefono());
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User obtenerUsuario(String username) {
        User user = userRepository.findByNombreUser(username).orElseThrow(null);
        return user;
    }

    @Override
    public UserDTO obtenerUsuarioDTO(String username) {
        User user = userRepository.findByNombreUser(username).orElseThrow(null);
        UserDTO userDTO = convertToUserDTO(user);
        return userDTO;
    }

    @Override
    public User actualizarUser(String username, UserDTO datosActualizados) {
        User userExistente = userRepository.findByNombreUser(username).orElseThrow(null);
        if (userExistente == null) {
            throw new EntityNotFoundException("User no encontrado");
        }
        if (datosActualizados == null) {
            throw new IllegalArgumentException("La info proporcionada no puede ser nula");
        }

        if (datosActualizados.getNombre() != null && !datosActualizados.getNombre().equals(userExistente.getNombre())) {
            userExistente.setNombre(datosActualizados.getNombre());
        }
        if (datosActualizados.getTelefono() != null && !datosActualizados.getTelefono().equals(userExistente.getTelefono())) {
            userExistente.setTelefono(datosActualizados.getTelefono());
        }
        if (datosActualizados.getBiografia() != null && !datosActualizados.getBiografia().equals(userExistente.getBiografia())) {
            userExistente.setBiografia(datosActualizados.getBiografia());
        }
        if (datosActualizados.getFotoPerfil() != null && !datosActualizados.getFotoPerfil().equals(userExistente.getFotoPerfil())) {
            userExistente.setFotoPerfil(datosActualizados.getFotoPerfil());
        }
        return userRepository.save(userExistente);
    }
}
