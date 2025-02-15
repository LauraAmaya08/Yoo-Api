package proyectoSpring.Yoo.Api.service.interfaces;

import proyectoSpring.Yoo.Api.model.entities.Likes;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;

public interface ILikesInterface {
    public Likes crearLike(Likes like);
    public void eliminarLike(Likes like);
    public Integer ListarLikesPubli(Publicacion publicacion);
}
