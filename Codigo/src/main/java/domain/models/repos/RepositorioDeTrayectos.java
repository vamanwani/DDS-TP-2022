package domain.models.repos;

import domain.models.entities.recorridos.Trayecto;
import domain.services.dbManager.EntityManagerHelper;

import java.util.List;

public class RepositorioDeTrayectos {
    public List<Trayecto> buscarTodos(int idMiembro) {
        //BUSCAR LOS TRAMOS DEL IDMIEMBRO, BUSCAR LOS TRAYECTOS A LOS QUE PERTENECEN

        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Trayecto.class.getName() + " where miembro_id =" + idMiembro)
                .getResultList();
    }
    public Trayecto buscar(Integer idTrayecto) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Trayecto.class, idTrayecto);
    }
    public void guardar(Trayecto trayecto) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(trayecto);
        EntityManagerHelper.commit();
    }

}
