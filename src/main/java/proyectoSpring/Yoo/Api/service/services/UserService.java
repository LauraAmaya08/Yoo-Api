package proyectoSpring.Yoo.Api.service.services;

import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.UserRepository;
import proyectoSpring.Yoo.Api.service.interfaces.IUserService;

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
    public void actualizarUser(User user) {
        if(userRepository.findByNombreUser(user.getUsername()).isPresent()){
            userRepository.save(user);
        }
    }
}
