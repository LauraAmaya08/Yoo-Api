package proyectoSpring.Yoo.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoSpring.Yoo.Api.model.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNombreUser(String nombreUser);
}
