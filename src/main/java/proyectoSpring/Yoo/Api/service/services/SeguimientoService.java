package proyectoSpring.Yoo.Api.service.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import proyectoSpring.Yoo.Api.model.DTO.PublicacionDTO;
import proyectoSpring.Yoo.Api.model.DTO.SeguimientoDTO;
import proyectoSpring.Yoo.Api.model.DTO.UserDTO;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.Seguimiento;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.SeguimientoRepository;
import proyectoSpring.Yoo.Api.repository.UserRepository;
import proyectoSpring.Yoo.Api.service.interfaces.ISeguimientoInterface;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeguimientoService implements ISeguimientoInterface {
    private final SeguimientoRepository seguimientoRepository;
    private final UserRepository userRepository;

    public SeguimientoService(SeguimientoRepository seguimientoRepository, UserRepository userRepository) {
        this.seguimientoRepository = seguimientoRepository;
        this.userRepository = userRepository;
    }

    private UserDTO convertToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getNombre(), user.getUsername(), user.getEmail(), user.getBiografia(), user.getFotoPerfil(), user.getFechaNac(), user.getTelefono());
    }

    private SeguimientoDTO convertToSeguimientoDTO(Seguimiento seguimiento) {
        UserDTO seguidoDTO = convertToUserDTO(seguimiento.getIdUserSeguido());
        UserDTO seguidorDTO = convertToUserDTO(seguimiento.getIdUser());
        return new SeguimientoDTO(seguimiento.getId(), seguidoDTO, seguidorDTO);
    }

    private PublicacionDTO convertToPublicacionDTO(Publicacion publicacion) {
        return new PublicacionDTO(publicacion.getId(), publicacion.getTexto(), publicacion.getFechaCreacion(), publicacion.getImagen());
    }

    @Override
    public List<SeguimientoDTO> obtenerSeguidos(Integer usuarioId) {
        User user = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Seguimiento> seguimientos = seguimientoRepository.findByIdUser(user);
        return seguimientos.stream()
                .map(this::convertToSeguimientoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SeguimientoDTO> obtenerSeguidores(Integer usuarioId) {
        User user = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Seguimiento> seguimientos = seguimientoRepository.findByIdUserSeguido(user);
        return seguimientos.stream()
                .map(this::convertToSeguimientoDTO)
                .collect(Collectors.toList());
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
        return seguimientoRepository.existsByIdUserAndIdUserSeguido(seguidor, seguido);
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

    @Transactional
    @Override
    public void dejarDeSeguirUsuario(Integer seguidorId, Integer seguidoId) {
        seguimientoRepository.deleteByUserIds(seguidorId, seguidoId);
    }

    @Override
    public List<PublicacionDTO> obtenerPublicacionesDeSeguidos(Integer usuarioId) {
        List<Publicacion> publicaciones = seguimientoRepository.findPublicacionesByUsuarioSeguidos(usuarioId);
        return publicaciones.stream()
                .map(this::convertToPublicacionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> obtenerUsuariosNoSeguidos(Integer usuarioId) {
        List<User> users = seguimientoRepository.findUsuariosNoSeguidos(usuarioId);
        return users.stream()
                .map(user -> convertToUserDTO(user))
                .collect(Collectors.toList());
    }
}
