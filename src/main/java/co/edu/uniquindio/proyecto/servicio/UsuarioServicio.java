package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Signature;
import java.util.List;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepository usuarioRepo;

    public Usuario buscarPorCorreoYContrasena(String correo, String contrasena){
        return this.usuarioRepo.findByCorreoAndContrasena(correo, contrasena);
    }

    public List<Usuario> listarTodosUsuarios(){
        return usuarioRepo.findAll();
    }

    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }

    public Usuario actualizarUsuario(int id, Usuario usuario){
        usuario.setId(id);
        return usuarioRepo.save(usuario);
    }

    public void borrarUsuario(int id){
        usuarioRepo.deleteById(id);
    }

}
