package proyectoSpring.Yoo.Api.Auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proyectoSpring.Yoo.Api.jwt.JwtService;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.UserRepository;

import java.time.LocalDate;
import java.time.Period;

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

    public AuthResponse login(LoginRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        User user = userRepository.findByNombreUser(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(86400)
                .sameSite("Strict")
                .build();

        response.addHeader("Set-Cookie", jwtCookie.toString());
        AuthResponse authResponse = new AuthResponse(token);
        return authResponse;
    }

    public AuthResponse register(RegisterRequest request,  HttpServletResponse response) {
        int edad = Period.between(request.getFechaNac(), LocalDate.now()).getYears();
        if (edad < 14){
            throw new IllegalArgumentException("Usuario menor de 14 años");
        }
        User user = new User(request.getNombre(),request.getUsername(),request.getEmail(),passwordEncoder.encode(request.getPassword()),request.getFechaNac(),request.getTelefono());
        userRepository.save(user);
        String token = jwtService.getToken(user);
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(86400)
                .sameSite("Strict")
                .build();
        response.addHeader("Set-Cookie", jwtCookie.toString());
        AuthResponse authResponse = new AuthResponse(token);
        return authResponse;


    }


}
