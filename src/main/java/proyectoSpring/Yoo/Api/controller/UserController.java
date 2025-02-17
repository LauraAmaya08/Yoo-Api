package proyectoSpring.Yoo.Api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.service.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getPerfil() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Usuario autenticado: " + username);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autenticado");
        }
        User user = userService.obtenerUsuario(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile/{username}")
    public ResponseEntity<User> actualizarPerfil(@PathVariable String username, @RequestBody User userData) {
        User updatedUser = userService.actualizarUser(username, userData);
        return ResponseEntity.ok(updatedUser);
    }
}
