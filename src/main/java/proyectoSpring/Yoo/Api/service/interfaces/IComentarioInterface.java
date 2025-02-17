package proyectoSpring.Yoo.Api.service.interfaces;

import proyectoSpring.Yoo.Api.model.entities.Comentario;
import proyectoSpring.Yoo.Api.model.entities.Publicacion;

import java.util.List;

public interface IComentarioInterface {
    public Comentario crearComentario(Comentario comentario, Integer idPubli);
    public List<Comentario> encontrarComentarios(Publicacion publicacion);
    public Comentario encontrarComentario(Integer id, Publicacion publicacion);
    public Comentario actualizarComentario(Integer userid, Integer id, Comentario nuevoComentario, Publicacion publicacion );
    public void eliminarComentario(Integer userid,Integer id, Publicacion publicacion);
    public Integer contarComentariosPubli(Publicacion publicacion);
}
