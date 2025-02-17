package proyectoSpring.Yoo.Api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.service.services.PublicacionService;
import proyectoSpring.Yoo.Api.service.services.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/publicaciones")
public class PublicacionController {

    private final PublicacionService publicacionService;
    private final UserService userService;

    public PublicacionController(PublicacionService publicacionService, UserService userService) {
        this.publicacionService = publicacionService;
        this.userService = userService;
    }

    @GetMapping("/publicaciones/cronologico")
    public ResponseEntity<List<Publicacion>> obtenerPublicacionesOrdenadasCronologicamente( @RequestParam(defaultValue = "true") boolean ascendente) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionesOrdenadasCronologicamente(ascendente));
    }

    @GetMapping("/publicaciones/trendy")
    public ResponseEntity<List<Publicacion>> obtenerPublicacionesOrdenadasPorRelevancia() {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionesOrdenadasPorRelevancia());
    }

    @PostMapping
    public ResponseEntity<Publicacion> crearPublicacion(@RequestBody Publicacion publicacion) {
        User usuarioActual = obtenerUsuarioAutenticado();
        if (usuarioActual == null) {
            return ResponseEntity.status(403).build();
        }
        publicacion.setUsuario(usuarioActual);
        return ResponseEntity.ok(publicacionService.crearPublicacion(publicacion));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPublicacion(@PathVariable Integer id) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacion(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Publicacion> actualizarPublicacion(@PathVariable Integer id, @RequestBody Publicacion publicacion) {
        User usuarioActual = obtenerUsuarioAutenticado();
        Publicacion publiExistente = publicacionService.obtenerPublicacion(id);

        if (publiExistente == null || !publiExistente.getUsuario().equals(usuarioActual)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(publicacionService.actualizarPublicacion(id, publicacion));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Integer id) {
        User usuarioActual = obtenerUsuarioAutenticado();
        Publicacion publiExistente = publicacionService.obtenerPublicacion(id);

        if (publiExistente == null || !publiExistente.getUsuario().equals(usuarioActual)) {
            return ResponseEntity.status(403).build();
        }

        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.noContent().build();
    }

    private User obtenerUsuarioAutenticado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userService.obtenerUsuario(username);
        }
        return null;
    }
}
