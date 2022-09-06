package domain.miembro;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    private String nombre;
    private String contrasenia;
    private String mail;
    private String telefono;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasenia, String mail, String telefono) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.mail = mail;
        this.telefono = telefono;
    }
    public Usuario(String nombre, String contrasenia){
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public String getMail(){
        return mail;
    }
    public String getTelefono(){
        return telefono;
    }
}
