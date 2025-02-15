package proyectoSpring.Yoo.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoSpring.Yoo.Api.model.entities.Etiqueta;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;
import java.util.List;
import java.util.Optional;


@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    List<Publicacion> findByUser(User user);
    List<Publicacion> findByEtiquetas(Etiqueta etiqueta);
    List<Publicacion> findAllByOrderByFechaPublicacionAsc();
    List<Publicacion> findAllByOrderByFechaPublicacionDesc();
    List<Publicacion> findAllByOrderByLikesDesc();
}
