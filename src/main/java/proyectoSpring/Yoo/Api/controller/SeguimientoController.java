package proyectoSpring.Yoo.Api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.Seguimiento;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.service.interfaces.ISeguimientoInterface;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seguimiento")
public class SeguimientoController {

    private final ISeguimientoInterface seguimientoService;

    public SeguimientoController(ISeguimientoInterface seguimientoService) {
        this.seguimientoService = seguimientoService;
    }


    @GetMapping
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{usuarioId}/seguidos")
    public ResponseEntity<List<Seguimiento>> obtenerSeguidos(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(seguimientoService.obtenerSeguidos(usuarioId));
    }

    @GetMapping("/{usuarioId}/seguidores")
    public ResponseEntity<List<Seguimiento>> obtenerSeguidores(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(seguimientoService.obtenerSeguidores(usuarioId));
    }

    @GetMapping("/{usuarioId}/contarSeguidos")
    public ResponseEntity<Integer> contarSeguidos(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(seguimientoService.contarSeguidos(usuarioId));
    }

    @GetMapping("/{usuarioId}/contarSeguidores")
    public ResponseEntity<Integer> contarSeguidores(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(seguimientoService.contarSeguidores(usuarioId));
    }

    @GetMapping("/{seguidorId}/sigue/{seguidoId}")
    public ResponseEntity<Boolean> existeSeguimiento(@PathVariable Integer seguidorId, @PathVariable Integer seguidoId) {
        return ResponseEntity.ok(seguimientoService.existeSeguimiento(seguidorId, seguidoId));
    }

    @PostMapping("/{seguidorId}/seguir/{seguidoId}")
    public ResponseEntity<String> seguirUsuario(@PathVariable Integer seguidorId, @PathVariable Integer seguidoId) {
        seguimientoService.seguirUsuario(seguidorId, seguidoId);
        return ResponseEntity.ok("Usuario seguido correctamente");
    }

    @DeleteMapping("/{seguidorId}/dejarSeguir/{seguidoId}")
    public ResponseEntity<String> dejarDeSeguirUsuario(@PathVariable Integer seguidorId, @PathVariable Integer seguidoId) {
        seguimientoService.dejarDeSeguirUsuario(seguidorId, seguidoId);
        return ResponseEntity.ok("Usuario dejado de seguir correctamente");
    }

    @GetMapping("/{usuarioId}/publicaciones")
    public ResponseEntity<List<Publicacion>> obtenerPublicacionesDeSeguidos(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(seguimientoService.obtenerPublicacionesDeSeguidos(usuarioId));
    }

    @GetMapping("/{usuarioId}/noSeguidos")
    public ResponseEntity<List<User>> obtenerUsuariosNoSeguidos(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(seguimientoService.obtenerUsuariosNoSeguidos(usuarioId));
    }
}