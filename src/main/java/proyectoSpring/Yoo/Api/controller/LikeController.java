package proyectoSpring.Yoo.Api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoSpring.Yoo.Api.model.entities.Likes;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.LikesRepository;
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
    private final UserRepository userRepository;
    private final PublicacionRepository publicacionRepository;
    private final LikesRepository likesRepository;

    public LikeController(LikeService likeService, PublicacionService publicacionService, UserRepository userRepository, PublicacionRepository publicacionRepository, LikesRepository likesRepository) {
        this.likeService = likeService;
        this.userRepository = userRepository;
        this.publicacionRepository = publicacionRepository;
        this.likesRepository = likesRepository;
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

    @DeleteMapping("/eliminar/{usuarioId}")
    public ResponseEntity<String> eliminarLike(@PathVariable Integer usuarioId,
                                               @RequestParam Integer publicacionId) {
        Optional<User> usuarioOpt = userRepository.findById(usuarioId);
        Optional<Publicacion> publiOpt = publicacionRepository.findById(publicacionId);

        if (!usuarioOpt.isPresent() || !publiOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User usuario = usuarioOpt.get();
        Publicacion publicacion = publiOpt.get();

        Optional<Likes> likeOpt = likesRepository.findByUserAndPublicacion(usuario, publicacion);
        if (!likeOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El like no existe");
        }
        likeService.eliminarLike(likeOpt.get());
        return ResponseEntity.ok("Like eliminado correctamente");
    }

    @GetMapping("/contar/{idPubli}")
    public ResponseEntity<Integer> contarLikes(@PathVariable Integer idPubli) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(idPubli);

        Integer totalLikes = likeService.ListarLikesPubli(publicacion);
        return ResponseEntity.ok(totalLikes);
    }

    @GetMapping("/status/{usuarioId}")
    public Boolean getLikeStatus(@PathVariable Integer usuarioId,
                                 @RequestParam Integer publicacionId) {

        User usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        return likesRepository.findByUserAndPublicacion(usuario, publicacion).isPresent();
    }
}
