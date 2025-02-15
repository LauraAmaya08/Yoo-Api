package proyectoSpring.Yoo.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoSpring.Yoo.Api.model.entities.Etiqueta;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer> {
    Etiqueta findByTexto(String nombre);
}
