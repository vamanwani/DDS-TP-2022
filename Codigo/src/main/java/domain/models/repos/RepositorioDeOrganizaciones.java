package domain.models.repos;

import domain.models.entities.consumo.Consumo;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.organizacion.ClasificaciónDeOrg;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.services.dbManager.EntityManagerHelper;

import java.util.List;

public class RepositorioDeOrganizaciones {
    public List<Miembro> buscarTodasLosSolicitantes(int idOrganizacion) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("select miembro_miembro_id " + "from solicitud_vinculacion where organizacion_id_organizacion =" + idOrganizacion)
                .getResultList();
    }
    public List<Consumo> buscarTodosLosConsumos(int idOrganizacion){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from "+ Consumo.class.getName() +" where organizacion_id = "+ idOrganizacion)
                .getResultList();
    }

    public Organizacion buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, id);
    }

    public Organizacion buscarSegunUsuarioId(int usuario){
        String queryMiembro = "from "+ Organizacion.class.getName() +" where usuario_id=" + usuario;
        return (Organizacion) EntityManagerHelper
                .getEntityManager()
                .createQuery(queryMiembro)
                .getSingleResult();
    }

    public void modificar(Organizacion servicio) {
        this.guardar(servicio);
    }

    public void guardar(Organizacion organizacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.commit();
    }


    public void guardarSector(Sector organizacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.commit();
    }

    public void guardarClasificacion(ClasificaciónDeOrg clasificaciónDeOrg){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(clasificaciónDeOrg);
        EntityManagerHelper.commit();
    }


    public void eliminar(Organizacion organizacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(organizacion);
        EntityManagerHelper.commit();
    }

    /*public List<Tramo> buscarTodosLosTramos(Integer idTrayecto) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("select tramos_id from " + Trayecto.class.getName() + "where id = " + idTrayecto)
                .getResultList();
    }

    public List<Organizacion> buscarOrganizaciones(Integer idMiembro){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("select sector_id from " + Miembro.class.getName() + "where id = " + idMiembro)
                .getResultList();
    }*/
}
