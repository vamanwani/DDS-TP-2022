package domain.models.repos;

import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.services.dbManager.EntityManagerHelper;

public class RepositorioDeAgentesSectoriales {

    public AgenteSectorial buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(AgenteSectorial.class, id);
    }

    public AgenteSectorial buscarSegunUsuarioId(int usuario){
        String query = "from " + AgenteSectorial.class.getName() +" where usuario_id=" + usuario;
        System.out.println("query: " + query);
        return (AgenteSectorial) EntityManagerHelper
                .getEntityManager()
                .createQuery(query)
                .getSingleResult();
    }


    public void guardar(AgenteSectorial agenteSectorial) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(agenteSectorial);
        EntityManagerHelper.commit();
    }
}
