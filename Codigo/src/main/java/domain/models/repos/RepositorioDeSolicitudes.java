package domain.models.repos;

import domain.models.entities.miembro.EstadoSolicitud;
import domain.models.entities.miembro.SolicitudVinculacion;
import domain.services.dbManager.EntityManagerHelper;

import java.util.List;

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

    public List<SolicitudVinculacion> buscarSolicitudesPendientesDeOrg(Integer idOrg) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from "+ SolicitudVinculacion.class.getName() +" where organizacion_id_organizacion = " + idOrg + " and estado_solicitud = 'PENDIENTE'")
                .getResultList();
    }

    public List<SolicitudVinculacion> buscarSolicitudesDeOrg(Integer idOrg) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from "+ SolicitudVinculacion.class.getName() +" where organizacion_id_organizacion = " + idOrg)
                .getResultList();
    }
}
