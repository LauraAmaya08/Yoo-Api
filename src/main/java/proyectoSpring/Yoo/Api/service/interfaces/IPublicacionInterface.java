package proyectoSpring.Yoo.Api.service.interfaces;

import proyectoSpring.Yoo.Api.model.entities.Etiqueta;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;
import proyectoSpring.Yoo.Api.model.entities.User;

import java.util.List;

public interface IPublicacionInterface {
    public Publicacion obtenerPublicacion(Integer id);
    public Publicacion crearPublicacion(Publicacion publicacion);
    public Publicacion actualizarPublicacion(Integer id, Publicacion publicacion);
    public void eliminarPublicacion(Integer id);
    public List<Publicacion> obtenerPublicacionesPorUser(User user);
    public List<Publicacion> obtenerPublicacionesPorEtiqueta(Etiqueta etiqueta);
    public List<Publicacion> obtenerPublicacionesOrdenadasCronologicamente(boolean ascendente);
    public List<Publicacion> obtenerPublicacionesOrdenadasPorRelevancia();

}
