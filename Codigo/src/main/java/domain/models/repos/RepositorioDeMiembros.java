package domain.models.repos;

import com.sendgrid.Request;
import com.sendgrid.Response;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.recorridos.Tramo;
import domain.models.entities.recorridos.Trayecto;
import domain.services.dbManager.EntityManagerHelper;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeMiembros {

    public Miembro buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Miembro.class, id);
    }

    public Miembro buscarSegunUsuarioId(int usuario){
        String queryMiembro = "from "+ Miembro.class.getName() +" where usuario_id=" + usuario;
        System.out.println("query: " + queryMiembro);
        return (Miembro) EntityManagerHelper
                .getEntityManager()
                .createQuery(queryMiembro)
                .getSingleResult();
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
        Miembro miembro =  EntityManagerHelper.getEntityManager().find(Miembro.class, Long.valueOf(idMiembro));
        List<Organizacion> organizaciones = miembro.getTrabajos().stream().map(s -> s.getOrganizacion()).collect(Collectors.toList());
        return organizaciones;
    }

    public List<Organizacion> mostrarOrganizaciones(){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from " + Organizacion.class.getName())
                .getResultList();
    }
}
