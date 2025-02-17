package proyectoSpring.Yoo.Api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSpring.Yoo.Api.model.entities.Likes;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.service.services.LikeService;
import proyectoSpring.Yoo.Api.service.services.PublicacionService;

@RestController
@RequestMapping("api/v1/likes")
public class LikeController {
    private final LikeService likeService;
    private final PublicacionService publicacionService;

    public LikeController(LikeService likeService, PublicacionService publicacionService) {
        this.likeService = likeService;
        this.publicacionService = publicacionService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Likes> crearMeGusta(@RequestBody Likes like) {
        Likes likeNuevo = likeService.crearLike(like);
        return ResponseEntity.ok(likeNuevo);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarLike(@RequestBody Likes like) {
        likeService.eliminarLike(like);
        return ResponseEntity.ok("Like eliminado correctamente");
    }

    @GetMapping("/contar/{idPubli}")
    public ResponseEntity<Integer> contarLikes(@PathVariable Integer idPubli) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(idPubli);

        Integer totalLikes = likeService.ListarLikesPubli(publicacion);
        return ResponseEntity.ok(totalLikes);
    }



}
