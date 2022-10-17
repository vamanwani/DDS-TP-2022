package domain.models.repos;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.recorridos.Tramo;
import domain.models.entities.recorridos.Trayecto;
import domain.services.dbManager.EntityManagerHelper;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeTrayectos {
    public List<Trayecto> buscarTodos(int idMiembro) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("select trayecto_id " + "from " + Miembro.class.getName() + " where id =" + idMiembro)
                .getResultList();
    }
    public List<Tramo> buscar(Integer idTrayecto) {
        Trayecto trayecto = EntityManagerHelper.getEntityManager().find(Trayecto.class, idTrayecto);
        List<Tramo> tramos = new ArrayList<>();
        for (Tramo tramo : trayecto.getTramos()){
            tramos.add(tramo);
        }
        return tramos;
    }



}
