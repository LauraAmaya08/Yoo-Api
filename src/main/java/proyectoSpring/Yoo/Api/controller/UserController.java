package proyectoSpring.Yoo.Api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import proyectoSpring.Yoo.Api.model.DTO.UserDTO;
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
        UserDTO user = userService.obtenerUsuarioDTO(username);
        UserDTO userDTO = new UserDTO(user.getId(), user.getNombre(), user.getNombreUser(), user.getEmail(), user.getBiografia(), user.getFotoPerfil(), user.getFechaNac(),user.getTelefono());
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/profile/{username}")
    public ResponseEntity<?> actualizarPerfil(@PathVariable String username, @RequestBody UserDTO userData) {
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!authenticatedUsername.equals(username)) {
            System.out.println(authenticatedUsername);
            System.out.println(username);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para modificar este perfil.");
        }

        User updatedUser = userService.actualizarUser(username, userData);

        UserDTO updatedUserDTO = new UserDTO(updatedUser.getId(), updatedUser.getNombre(), updatedUser.getUsername(),
                updatedUser.getEmail(), updatedUser.getBiografia(), updatedUser.getFotoPerfil(),
                updatedUser.getFechaNac(), updatedUser.getTelefono());

        return ResponseEntity.ok(updatedUserDTO);
    }

}
