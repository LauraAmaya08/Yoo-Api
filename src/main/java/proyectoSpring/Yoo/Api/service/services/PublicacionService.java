package proyectoSpring.Yoo.Api.service.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import proyectoSpring.Yoo.Api.model.entities.Etiqueta;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.PublicacionRepository;
import proyectoSpring.Yoo.Api.repository.UserRepository;
import proyectoSpring.Yoo.Api.service.interfaces.IPublicacionInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PublicacionService implements IPublicacionInterface {
    final private PublicacionRepository publicacionRepository;
    final private UserRepository userRepository;

    public PublicacionService(PublicacionRepository publicacionRepository, UserRepository userRepository) {
        this.publicacionRepository = publicacionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Publicacion obtenerPublicacion(Integer id) {
        return publicacionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publicación no encontrada con ID: " + id));
    }

    public Publicacion crearPublicacion(Publicacion publicacion) {
        if (publicacion.getMenciones() == null) {
            publicacion.setMenciones(new ArrayList<>());
        }
        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
        List<User> usuariosMencionados = getUsuariosMencionados(publicacion.getTexto());
        nuevaPublicacion.getMenciones().addAll(usuariosMencionados);
        return publicacionRepository.save(nuevaPublicacion);
    }

    @Override
    public Publicacion actualizarPublicacion(Integer id, Publicacion publicacion) {
        Publicacion publiExistente = obtenerPublicacion(id);
        publiExistente.setTexto(publicacion.getTexto());
        publiExistente.setImagen(publicacion.getImagen());
        publiExistente.setMenciones(publicacion.getMenciones());
        publiExistente.setEtiquetas(publicacion.getEtiquetas());
        return publicacionRepository.save(publiExistente);
    }

    @Override
    public void eliminarPublicacion(Integer id) {
        Publicacion publiExistente = obtenerPublicacion(id);
        publicacionRepository.delete(publiExistente);
    }

    @Override
    public List<Publicacion> obtenerPublicacionesPorUser(User user) {
        return publicacionRepository.findByUsuario(user);
    }

    @Override
    public List<Publicacion> obtenerPublicacionesPorEtiqueta(Etiqueta etiqueta) {
        return publicacionRepository.findByEtiquetas(etiqueta);
    }

    @Override
    public List<Publicacion> obtenerPublicacionesOrdenadasCronologicamente(boolean ascendente) {
        if (ascendente) {
            return publicacionRepository.findAllByOrderByFechaCreacionAsc();
        } else {
            return publicacionRepository.findAllByOrderByFechaCreacionDesc();
        }
    }

    @Override
    public List<Publicacion> obtenerPublicacionesOrdenadasPorRelevancia() {
        return publicacionRepository.findAllByOrderByLikesDesc();
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
