package proyectoSpring.Yoo.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoSpring.Yoo.Api.model.entities.Likes;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
    Integer countByPublicacion(Publicacion publicacion);
}
