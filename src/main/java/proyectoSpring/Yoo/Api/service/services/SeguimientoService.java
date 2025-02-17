package proyectoSpring.Yoo.Api.service.services;

import org.springframework.stereotype.Service;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.Seguimiento;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.SeguimientoRepository;
import proyectoSpring.Yoo.Api.repository.UserRepository;
import proyectoSpring.Yoo.Api.service.interfaces.ISeguimientoInterface;
import java.util.List;

@Service
public class SeguimientoService implements ISeguimientoInterface {
    private final SeguimientoRepository seguimientoRepository;
    private final UserRepository userRepository;

    public SeguimientoService(SeguimientoRepository seguimientoRepository, UserRepository userRepository) {
        this.seguimientoRepository = seguimientoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Seguimiento> obtenerSeguidos(Integer usuarioId) {
        User user = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return seguimientoRepository.findByIdUser(user);
    }

    @Override
    public List<Seguimiento> obtenerSeguidores(Integer usuarioId) {
        User user = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return seguimientoRepository.findByIdUserSeguido(user);
    }

    @Override
    public Integer contarSeguidos(Integer usuarioId) {
        User user = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return seguimientoRepository.countByIdUser(user);
    }

    @Override
    public Integer contarSeguidores(Integer usuarioId) {
        User user = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return seguimientoRepository.countByIdUserSeguido(user);
    }

    @Override
    public boolean existeSeguimiento(Integer seguidorId, Integer seguidoId) {
        User seguidor = userRepository.findById(seguidorId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        User seguido = userRepository.findById(seguidoId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return seguimientoRepository.existsByIdUserAndIdUserSeguido(seguidor,seguido);
    }

    @Override
    public void seguirUsuario(Integer seguidorId, Integer seguidoId) {
        if (!existeSeguimiento(seguidorId, seguidoId)) {
            User seguidor = userRepository.findById(seguidorId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            User seguido = userRepository.findById(seguidoId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Seguimiento newSeguimiento = new Seguimiento(seguido, seguidor);
            seguimientoRepository.save(newSeguimiento);
        }
    }

    @Override
    public void dejarDeSeguirUsuario(Integer seguidorId, Integer seguidoId) {
        if (!existeSeguimiento(seguidorId, seguidoId)) {
            User seguidor = userRepository.findById(seguidorId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            User seguido = userRepository.findById(seguidoId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            seguimientoRepository.deleteByIdUserAndIdUserSeguido(seguidor,seguido);
        }
    }

    @Override
    public List<Publicacion> obtenerPublicacionesDeSeguidos(Integer usuarioId) {
        return seguimientoRepository.findPublicacionesByUsuarioSeguidos(usuarioId);
    }

    @Override
    public List<User> obtenerUsuariosNoSeguidos(Integer usuarioId) {
        return seguimientoRepository.findUsuariosNoSeguidos(usuarioId);
    }
}
