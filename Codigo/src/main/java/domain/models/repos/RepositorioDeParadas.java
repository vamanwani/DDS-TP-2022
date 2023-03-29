package domain.models.repos;

import domain.models.entities.miembro.SolicitudVinculacion;
import domain.models.entities.ubicacion.Parada;
import domain.services.dbManager.EntityManagerHelper;

public class RepositorioDeParadas {
    public Parada buscar(Integer id) {
        return (Parada) EntityManagerHelper
                .getEntityManager()
                .find(Parada.class, id);
    }

    public void guardar(Parada parada) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(parada);
        EntityManagerHelper.commit();
    }
}
