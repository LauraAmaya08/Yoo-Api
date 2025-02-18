package proyectoSpring.Yoo.Api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSpring.Yoo.Api.model.entities.Likes;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.PublicacionRepository;
import proyectoSpring.Yoo.Api.repository.UserRepository;
import proyectoSpring.Yoo.Api.service.services.LikeService;
import proyectoSpring.Yoo.Api.service.services.PublicacionService;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/likes")
public class LikeController {
    private final LikeService likeService;
    private final PublicacionService publicacionService;
    private final UserRepository userRepository;
    private final PublicacionRepository publicacionRepository;

    public LikeController(LikeService likeService, PublicacionService publicacionService, UserRepository userRepository, PublicacionRepository publicacionRepository) {
        this.likeService = likeService;
        this.publicacionService = publicacionService;
        this.userRepository = userRepository;
        this.publicacionRepository = publicacionRepository;
    }

    @PostMapping("/crear/{usuarioId}")
    public ResponseEntity<Likes> crearMeGusta(@RequestBody Map<String, Integer> request, @PathVariable Integer usuarioId) {
        Integer publicacionId = request.get("publicacionId");
        Optional<User> usuarioOpt = userRepository.findById(usuarioId);
        Optional<Publicacion> publiOpt = publicacionRepository.findById(publicacionId);
        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!publiOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User usuario = usuarioOpt.get();
        Publicacion publicacion = publiOpt.get();
        Likes likeCrear = new Likes(publicacion, usuario);
        Likes likeNuevo = likeService.crearLike(likeCrear);
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
