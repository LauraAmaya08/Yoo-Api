package proyectoSpring.Yoo.Api.service.interfaces;

import proyectoSpring.Yoo.Api.model.entities.Comentario;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;

public interface IComentarioInterface {
    public Comentario crearComentario(Comentario comentario, Integer idPubli);
    public Comentario encontrarComentario(Integer id, Publicacion publicacion);
    public Comentario actualizarComentario(Integer id, Comentario nuevoComentario, Publicacion publicacion );
    public void eliminarComentario(Integer id, Publicacion publicacion);
    public Integer contarComentariosPubli(Publicacion publicacion);
}
