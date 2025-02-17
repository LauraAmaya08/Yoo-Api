package proyectoSpring.Yoo.Api.service.interfaces;

import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.Seguimiento;
import proyectoSpring.Yoo.Api.model.entities.User;

import java.util.List;

public interface ISeguimientoInterface {
        List<Seguimiento> obtenerSeguidos(Integer usuarioId);
        List<Seguimiento> obtenerSeguidores(Integer usuarioId);
        Integer contarSeguidos(Integer usuarioId);
        Integer contarSeguidores(Integer usuarioId);
        boolean existeSeguimiento(Integer seguidorId, Integer seguidoId);
        void seguirUsuario(Integer seguidorId, Integer seguidoId);
        void dejarDeSeguirUsuario(Integer seguidorId, Integer seguidoId);
        List<Publicacion> obtenerPublicacionesDeSeguidos(Integer usuarioId);
        List<User> obtenerUsuariosNoSeguidos(Integer usuarioId);

}
