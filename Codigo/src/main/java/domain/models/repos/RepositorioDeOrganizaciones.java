package domain.models.repos;

import com.sendgrid.Request;
import com.sendgrid.Response;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.SolicitudVinculacion;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.recorridos.Tramo;
import domain.models.entities.recorridos.Trayecto;
import domain.services.dbManager.EntityManagerHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RepositorioDeOrganizaciones {
    public List<Miembro> buscarTodasLosSolicitantes(int idOrganizacion) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("select miembro_miembro_id " + "from " + SolicitudVinculacion.class.getName() + " where organizacion_id_organizacion =" + idOrganizacion)
                .getResultList();
    }


    public Organizacion buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, id);
    }

    public Organizacion buscarSegunUsuario(Usuario usuario){
        String query = "from "+ Organizacion.class.getName() +" where usuario_id=" + usuario.getId();
        return (Organizacion) EntityManagerHelper
                .getEntityManager()
                .createQuery(query)
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
