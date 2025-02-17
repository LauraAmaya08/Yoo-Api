package proyectoSpring.Yoo.Api.Auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSpring.Yoo.Api.jwt.JwtService;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request, HttpServletResponse response){
        return ResponseEntity.ok(authService.login(request, response));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request, HttpServletResponse response){
        return ResponseEntity.ok(authService.register(request, response));
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        Optional<String> token = getTokenFromRequest(request);

        if (token.isPresent() && jwtService.isTokenValid(token.get())) {
            return ResponseEntity.ok("Token válido");
        } else {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        authService.logout(response);
        return ResponseEntity.ok("Logout exitoso, cookie eliminada.");
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> "jwt".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }
}
