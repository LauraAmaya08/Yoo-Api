package proyectoSpring.Yoo.Api.service.services;

import org.springframework.stereotype.Service;
import proyectoSpring.Yoo.Api.model.entities.Likes;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.repository.LikesRepository;
import proyectoSpring.Yoo.Api.repository.PublicacionRepository;
import proyectoSpring.Yoo.Api.service.interfaces.ILikesInterface;

@Service
public class LikeService implements ILikesInterface {
    private final LikesRepository likesRepository;
    private final PublicacionRepository publicacionRepository;

    public LikeService(LikesRepository likesRepository, PublicacionRepository publicacionRepository) {
        this.likesRepository = likesRepository;
        this.publicacionRepository = publicacionRepository;
    }

    @Override
    public Likes crearLike(Likes like) {
        return likesRepository.save(like);
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
