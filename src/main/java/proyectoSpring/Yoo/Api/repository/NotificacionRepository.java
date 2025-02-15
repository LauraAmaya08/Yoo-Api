package proyectoSpring.Yoo.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoSpring.Yoo.Api.model.entities.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
}
