package domain.models.repos;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.recorridos.Tramo;
import domain.services.dbManager.EntityManagerHelper;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeTramos {
    public Tramo buscar(Integer id){
        return EntityManagerHelper
                .getEntityManager()
                .find(Tramo.class, id);
    }

    public List<Tramo> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Tramo.class.getName())
                .getResultList();
    }

    public List<Tramo> buscarTodosLosTramosDelMiembro(int id){
        Miembro miembro = (Miembro) EntityManagerHelper.getEntityManager().createQuery("from " + Miembro.class.getName() + " where id = " + id).getSingleResult();
        List<Tramo> todosLosTramos = this.buscarTodos();
        List<Tramo> todosLosTramosDelMiembro = todosLosTramos.stream().filter(t -> t.getMiembrosMismoTransporte().contains(miembro)).collect(Collectors.toList());
        return todosLosTramosDelMiembro;
    }

//    public List<Tramo> buscarTramosMiembro(Miembro miembro){
//        Miembro miembro1 = EntityManagerHelper.getEntityManager().find(miembro);
//
//    }

    public void guardar(Tramo tramo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tramo);
        EntityManagerHelper.commit();
    }

    public void guardarSiNoExiste(Tramo tramo){
        try {
            Tramo tramoBuscado = this.buscar(tramo.getId());
            System.out.println("printear algo");
        } catch (Exception e){
            this.guardar(tramo);
        }
    }

}
