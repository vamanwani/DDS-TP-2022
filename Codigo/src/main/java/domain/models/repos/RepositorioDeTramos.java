package domain.models.repos;

import domain.models.entities.miembro.Usuario;
import domain.models.entities.recorridos.Tramo;
import domain.services.dbManager.EntityManagerHelper;

public class RepositorioDeTramos {
//    public Usuario buscar(String nombreDeUsuario, String contrasenia){
//        return (Usuario) EntityManagerHelper
//                .getEntityManager()
//                .createQuery("from "
//                        + Usuario.class.getName()
//                        + " WHERE nombre = '"
//                        + nombreDeUsuario
//                        + "' AND contrasenia='"
//                        + contrasenia
//                        + "'")
//                .getSingleResult();
//    }

    public void guardar(Tramo tramo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tramo);
        EntityManagerHelper.commit();
    }
}
