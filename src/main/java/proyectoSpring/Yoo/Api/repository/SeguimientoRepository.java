package proyectoSpring.Yoo.Api.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.Seguimiento;
import proyectoSpring.Yoo.Api.model.entities.User;

import java.util.List;
@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
    List<Seguimiento> findByIdUser(User user);
    List<Seguimiento> findByIdUserSeguido(User user);
    boolean existsByIdUserAndIdUserSeguido(User seguidorId, User seguidoId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Seguimiento s WHERE s.idUser.id = :seguidorId AND s.idUserSeguido.id = :seguidoId")
    void deleteByUserIds(@Param("seguidorId") Integer seguidorId, @Param("seguidoId") Integer seguidoId);
    @Query("SELECT p FROM Publicacion p WHERE p.usuario.id IN (SELECT s.idUserSeguido.id FROM Seguimiento s WHERE s.idUser.id = :usuarioId)")
    List<Publicacion> findPublicacionesByUsuarioSeguidos(@Param("usuarioId") Integer usuarioId);
    Integer countByIdUserSeguido(User user);
    Integer countByIdUser(User user);
    @Query("SELECT u FROM User u WHERE u.id NOT IN " +
            "(SELECT s.idUserSeguido.id FROM Seguimiento s WHERE s.idUser.id = :usuarioId) " +
            "AND u.id <> :usuarioId")
    List<User> findUsuariosNoSeguidos(@Param("usuarioId") Integer usuarioId);

}
