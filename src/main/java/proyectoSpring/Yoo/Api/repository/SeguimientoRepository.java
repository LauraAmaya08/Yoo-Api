package proyectoSpring.Yoo.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoSpring.Yoo.Api.model.entities.Seguimiento;

public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
}
