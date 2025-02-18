package proyectoSpring.Yoo.Api.service.interfaces;

import proyectoSpring.Yoo.Api.model.DTO.SeguimientoDTO;
import proyectoSpring.Yoo.Api.model.DTO.UserDTO;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;

import java.util.List;

public interface ISeguimientoInterface {
        List<SeguimientoDTO> obtenerSeguidos(Integer usuarioId);
        List<SeguimientoDTO> obtenerSeguidores(Integer usuarioId);
        Integer contarSeguidos(Integer usuarioId);
        Integer contarSeguidores(Integer usuarioId);
        boolean existeSeguimiento(Integer seguidorId, Integer seguidoId);
        void seguirUsuario(Integer seguidorId, Integer seguidoId);
        void dejarDeSeguirUsuario(Integer seguidorId, Integer seguidoId);
        List<Publicacion> obtenerPublicacionesDeSeguidos(Integer usuarioId);
        List<UserDTO> obtenerUsuariosNoSeguidos(Integer usuarioId);

}
