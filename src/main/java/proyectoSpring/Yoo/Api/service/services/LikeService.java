package proyectoSpring.Yoo.Api.service.services;

import org.springframework.stereotype.Service;
import proyectoSpring.Yoo.Api.model.entities.Likes;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.LikesRepository;
import proyectoSpring.Yoo.Api.repository.PublicacionRepository;
import proyectoSpring.Yoo.Api.repository.UserRepository;
import proyectoSpring.Yoo.Api.service.interfaces.ILikesInterface;

import java.util.Optional;

@Service
public class LikeService implements ILikesInterface {
    private final LikesRepository likesRepository;
    private final PublicacionRepository publicacionRepository;
    private final UserRepository userRepository;

    public LikeService(LikesRepository likesRepository, PublicacionRepository publicacionRepository, UserRepository userRepository) {
        this.likesRepository = likesRepository;
        this.publicacionRepository = publicacionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Likes crearLike(Likes like) {
        Optional<User> usuarioExistente = userRepository.findById(like.getUser().getId());
        if (usuarioExistente.isPresent()) {
            Likes nuevoLike = new Likes();
            nuevoLike.setUser(usuarioExistente.get());
            nuevoLike.setPublicacion(like.getPublicacion());
            likesRepository.save(nuevoLike);
            return nuevoLike;
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }

    @Override
    public void eliminarLike(Likes like) {
        likesRepository.delete(like);
    }

    @Override
    public Integer ListarLikesPubli(Publicacion publicacion) {
        return likesRepository.countByPublicacion(publicacion);
    }
}
