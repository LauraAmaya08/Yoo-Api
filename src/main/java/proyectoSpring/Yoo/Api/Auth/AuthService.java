package proyectoSpring.Yoo.Api.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        User user = userRepository.findByNombreUser(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        AuthResponse authResponse = new AuthResponse(token);
        return authResponse;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User(request.getNombre(),request.getUsername(),request.getEmail(),passwordEncoder.encode(request.getPassword()),request.getFechaNac(),request.getTelefono());
        System.out.println(user);
        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse(jwtService.getToken(user));
        return authResponse;
    }
}
