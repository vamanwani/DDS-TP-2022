package domain.models.entities.miembro;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "administrador")
@NoArgsConstructor
public class Adminisitrador {

    @Id
    @GeneratedValue
    @Column(name = "administrador_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Adminisitrador(Usuario usuario){
        this.usuario = usuario;
    }

}
