package proyectoSpring.Yoo.Api.service.services;

import jakarta.persistence.EntityNotFoundException;
import proyectoSpring.Yoo.Api.model.entities.Etiqueta;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;
import proyectoSpring.Yoo.Api.repository.PublicacionRepository;
import proyectoSpring.Yoo.Api.service.interfaces.IPublicacionInterface;

import java.util.List;

public class PublicacionService implements IPublicacionInterface {
    final private PublicacionRepository publicacionRepository;

    public PublicacionService(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    @Override
    public Publicacion obtenerPublicacion(Integer id) {
        return publicacionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publicación no encontrada con ID: " + id));
    }

    @Override
    public Publicacion crearPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
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
        return publicacionRepository.findByUser(user);
    }

    @Override
    public List<Publicacion> obtenerPublicacionesPorEtiqueta(Etiqueta etiqueta) {
        return publicacionRepository.findByEtiquetas(etiqueta);
    }

    @Override
    public List<Publicacion> obtenerPublicacionesOrdenadasCronologicamente(boolean ascendente) {
        if (ascendente) {
            return publicacionRepository.findAllByOrderByFechaPublicacionAsc();
        } else {
            return publicacionRepository.findAllByOrderByFechaPublicacionDesc();
        }
    }

    @Override
    public List<Publicacion> obtenerPublicacionesOrdenadasPorRelevancia() {
        return publicacionRepository.findAllByOrderByLikesDesc();
    }
}
