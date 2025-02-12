package proyectoSpring.Yoo.Api.Auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proyectoSpring.Yoo.Api.jwt.JwtService;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User(request.getNombre(),request.getUsername(),request.getEmail(),passwordEncoder.encode(request.getPassword()),request.getFechaNac(),request.getTelefono());
        System.out.println(user);
        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse(jwtService.getToken(user));
        return authResponse;
    }
}
