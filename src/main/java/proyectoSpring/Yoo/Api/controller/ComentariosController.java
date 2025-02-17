package proyectoSpring.Yoo.Api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSpring.Yoo.Api.model.entities.Comentario;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.service.services.ComentarioService;
import proyectoSpring.Yoo.Api.service.services.UserService;

import java.util.List;


@RestController
@RequestMapping("api/v1/comentarios")
public class ComentariosController {

    private final ComentarioService comentarioService;
    private final UserService userService;

    public ComentariosController(ComentarioService comentarioService, UserService userService) {
        this.comentarioService = comentarioService;
        this.userService = userService;
    }

    @PostMapping("/{idPubli}")
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario, @PathVariable Integer idPubli) {
        Comentario nuevoComentario = comentarioService.crearComentario(comentario, idPubli);
        return ResponseEntity.ok(nuevoComentario);
    }

    @GetMapping("/{Publi}")
    public ResponseEntity<?> obtenerComentarios(@PathVariable Publicacion Publi) {
        Publicacion publicacion = new Publicacion();
        List<Comentario> comentarios = comentarioService.encontrarComentarios(publicacion);
        return ResponseEntity.ok(comentarios);
    }

    @PutMapping("/{userid}/{id}/{idPubli}")
    public ResponseEntity<Comentario> actualizarComentario(
            @PathVariable Integer userid, @PathVariable Integer id, @PathVariable Integer idPubli,
            @RequestBody Comentario nuevoComentario) {

        Publicacion publicacion = new Publicacion();
        publicacion.setId(idPubli);

        Comentario comentarioActualizado = comentarioService.actualizarComentario(userid, id, nuevoComentario, publicacion);
        return ResponseEntity.ok(comentarioActualizado);
    }


    @DeleteMapping("/{userid}/{id}/{idPubli}")
    public ResponseEntity<String> eliminarComentario(@PathVariable Integer userid, @PathVariable Integer id, @PathVariable Integer idPubli) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(idPubli);

        comentarioService.eliminarComentario(userid, id, publicacion);
        return ResponseEntity.ok("Comentario eliminado correctamente");
    }


    @GetMapping("/contar/{idPubli}")
    public ResponseEntity<Integer> contarComentarios(@PathVariable Integer idPubli) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(idPubli);

        Integer totalComentarios = comentarioService.contarComentariosPubli(publicacion);
        return ResponseEntity.ok(totalComentarios);
    }

}
