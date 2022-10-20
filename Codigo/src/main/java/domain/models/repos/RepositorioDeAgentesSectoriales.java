package domain.models.repos;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.models.entities.sectorTerritorial.SectorTerritorial;
import domain.services.dbManager.EntityManagerHelper;

public class RepositorioDeAgentesSectoriales {
    public SectorTerritorial buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(SectorTerritorial.class, id);
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
