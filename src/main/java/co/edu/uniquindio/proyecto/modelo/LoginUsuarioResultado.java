package co.edu.uniquindio.proyecto.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginUsuarioResultado {
        private Usuario usuario;
        private String token;

}
