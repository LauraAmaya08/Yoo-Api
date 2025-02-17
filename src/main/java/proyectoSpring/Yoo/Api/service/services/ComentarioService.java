package proyectoSpring.Yoo.Api.service.services;

import org.springframework.stereotype.Service;
import proyectoSpring.Yoo.Api.model.entities.Comentario;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.ComentarioRepository;
import proyectoSpring.Yoo.Api.repository.PublicacionRepository;
import proyectoSpring.Yoo.Api.repository.UserRepository;
import proyectoSpring.Yoo.Api.service.interfaces.IComentarioInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ComentarioService implements IComentarioInterface {
    private final ComentarioRepository comentarioRepository;
    private final PublicacionRepository publicacionRepository;
    private final UserRepository userRepository;

    public ComentarioService(ComentarioRepository comentarioRepository, PublicacionRepository publicacionRepository, UserRepository userRepository) {
        this.comentarioRepository = comentarioRepository;
        this.publicacionRepository = publicacionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Comentario crearComentario(Comentario comentario, Integer idPubli) {
        Publicacion publi = publicacionRepository.findById(idPubli).orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
        comentario.setPublicacion(publi);
        List<User> usuariosMencionados = getUsuariosMencionados(comentario.getTexto());
        comentario.getUsuariosMencionados().addAll(usuariosMencionados);
        return comentarioRepository.save(comentario);
    }

    @Override
    public List<Comentario> encontrarComentarios(Publicacion publicacion) {
        return comentarioRepository.findByPublicacion(publicacion);
    }

    @Override
    public Comentario encontrarComentario(Integer id, Publicacion publicacion) {
        return comentarioRepository.findById(id)
                .filter(comentario -> comentario.getPublicacion().equals(publicacion))
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado en la publicación dada"));
    }


    @Override
    public Comentario actualizarComentario(Integer userid,Integer id, Comentario nuevoComentario, Publicacion publicacion) {
        Comentario comentarioExistente = encontrarComentario(id, publicacion);
        if (Objects.equals(comentarioExistente.getUser().getId(), userid)){
            comentarioExistente.setTexto(nuevoComentario.getTexto());
            return comentarioRepository.save(comentarioExistente);
        } return null;
    }


    @Override
    public void eliminarComentario(Integer userid,Integer id, Publicacion publicacion) {
        Comentario comentarioExistente = encontrarComentario(id, publicacion);
        if (Objects.equals(comentarioExistente.getUser().getId(), userid)){
            comentarioRepository.deleteById(comentarioExistente.getId());
        }
    }

    @Override
    public Integer contarComentariosPubli(Publicacion publicacion) {
        return comentarioRepository.countByPublicacion(publicacion);
    }

    private List<User> getUsuariosMencionados(String texto) {
        List<User> usuariosMencionados = new ArrayList<>();
        Pattern pattern = Pattern.compile("@(\\w+)");
        Matcher matcher = pattern.matcher(texto);

        while (matcher.find()) {
            String username = matcher.group(1);
            User usuario = userRepository.findByNombreUser(username).orElseThrow(null);
            if (usuario != null) {
                usuariosMencionados.add(usuario);
            }
        }

        return usuariosMencionados;
    }
}
