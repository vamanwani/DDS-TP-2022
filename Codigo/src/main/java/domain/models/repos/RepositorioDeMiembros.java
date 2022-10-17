package domain.models.repos;

import com.sendgrid.Request;
import com.sendgrid.Response;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.recorridos.Tramo;
import domain.models.entities.recorridos.Trayecto;
import domain.services.dbManager.EntityManagerHelper;

import java.util.List;

public class RepositorioDeMiembros {


    public Miembro buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Miembro.class, id);
    }

    public void modificar(Miembro servicio) {
        this.guardar(servicio);
    }

    public void guardar(Miembro miembro) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(miembro);
        EntityManagerHelper.commit();
    }

    public void eliminar(Miembro miembro) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(miembro);
        EntityManagerHelper.commit();
    }



    public List<Organizacion> buscarOrganizaciones(Integer idMiembro){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("select sector_id from " + Miembro.class.getName() + "where id = " + idMiembro)
                .getResultList();
    }
}
