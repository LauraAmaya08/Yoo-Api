package proyectoSpring.Yoo.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoSpring.Yoo.Api.model.entities.Etiqueta;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;
import java.util.List;


@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    List<Publicacion> findByUsuario(User user);
    List<Publicacion> findByEtiquetas(Etiqueta etiqueta);
    List<Publicacion> findAllByOrderByFechaCreacionAsc();
    List<Publicacion> findAllByOrderByFechaCreacionDesc();
    List<Publicacion> findAllByOrderByLikesDesc();
}
