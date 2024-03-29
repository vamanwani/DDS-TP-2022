package domain.models.entities.miembro;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue
    @Column(name = "usuario_id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "mail")
    private String mail;

    @Column(name = "telefono")
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario() {
    }

    public Usuario(String nombre, String contrasenia, String mail, String telefono, TipoUsuario tipoUsuario) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.mail = mail;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }
}
