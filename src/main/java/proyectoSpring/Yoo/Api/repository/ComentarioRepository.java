package proyectoSpring.Yoo.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoSpring.Yoo.Api.model.entities.Comentario;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    public void deleteById(Integer idComent);
    List<Comentario> findByPublicacion(Publicacion publicacion);
    Integer countByPublicacion(Publicacion publicacion);
}
