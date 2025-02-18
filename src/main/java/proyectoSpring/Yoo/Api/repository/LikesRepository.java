package proyectoSpring.Yoo.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoSpring.Yoo.Api.model.entities.Likes;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
    Integer countByPublicacion(Publicacion publicacion);
    Optional<Likes> findByUserAndPublicacion(User usuario, Publicacion publicacion);
}
