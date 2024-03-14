package co.edu.uniquindio.proyecto.repositorio;

import co.edu.uniquindio.proyecto.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    Usuario findByCorreoAndContrasena(String correo,  String contrasena);
}
