package co.edu.uniquindio.proyecto.controlador;

import co.edu.uniquindio.proyecto.modelo.LoginUsuario;
import co.edu.uniquindio.proyecto.modelo.LoginUsuarioResultado;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioServicio.listarTodosUsuarios();
    }

    @PostMapping
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioServicio.crearUsuario(usuario);
    }

    @PostMapping("/login")
    public LoginUsuarioResultado login(LoginUsuario loginUsuario) {
        Usuario usuario = this.usuarioServicio.buscarPorCorreoYContrasena(loginUsuario.getCorreo(),
                loginUsuario.getContrasena());
        if (usuario == null){
            throw new RuntimeException("El usuario no existe");
        }
        String token = getJWTToken(String.valueOf(usuario.getId()));
        return new LoginUsuarioResultado(usuario, token);
    }



    private String getJWTToken(String userId) {
        String secretKey = "1234";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("apirest")
                .setSubject(userId)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(int id, Usuario usuario) {
        return usuarioServicio.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void borrarUsuario(int id) {
        usuarioServicio.borrarUsuario(id);
    }
}
