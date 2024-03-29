package domain.models.repos;

import domain.models.entities.miembro.Usuario;
import domain.services.dbManager.EntityManagerHelper;

public class RepositorioDeUsuarios {

    public Usuario buscar(String nombreDeUsuario, String contrasenia){
        return (Usuario) EntityManagerHelper
                .getEntityManager()
                .createQuery("from "
                        + Usuario.class.getName()
                        + " WHERE nombre = '"
                        + nombreDeUsuario
                        + "' AND contrasenia='"
                        + contrasenia
                        + "'")
                .getSingleResult();
    }

    public void guardar(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();
    }

    public Usuario buscarSegunUsuario(Integer nombreUsuario) {
        return (Usuario) EntityManagerHelper
                .getEntityManager()
                .createQuery("from "
                        + Usuario.class.getName()
                        + " WHERE nombre = '"
                        + nombreUsuario
                        + "'")
                .getSingleResult();
    }

    public Usuario buscarSegunId(Object id) {
        return (Usuario) EntityManagerHelper
                .getEntityManager()
                .createQuery("from "
                        + Usuario.class.getName()
                        + " WHERE usuario_id = '"
                        + id
                        + "'")
                .getSingleResult();
    }
}
