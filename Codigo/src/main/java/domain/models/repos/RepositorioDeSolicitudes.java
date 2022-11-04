package domain.models.repos;

import domain.models.entities.miembro.SolicitudVinculacion;
import domain.models.entities.organizacion.Organizacion;
import domain.services.dbManager.EntityManagerHelper;

public class RepositorioDeSolicitudes {

    public SolicitudVinculacion buscar(Integer id) {
        return (SolicitudVinculacion) EntityManagerHelper
                .getEntityManager()
                .find(SolicitudVinculacion.class, id);
    }

    public void guardar(SolicitudVinculacion solicitudVinculacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(solicitudVinculacion);
        EntityManagerHelper.commit();
    }

}
