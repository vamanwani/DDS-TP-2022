package domain.models.repos;

import domain.models.entities.organizacion.Sector;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.models.entities.sectorTerritorial.SectorTerritorial;
import domain.services.dbManager.EntityManagerHelper;

import java.util.List;
import java.util.Set;

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

    public SectorTerritorial buscarSectorSegunAgente(int id){
        return (SectorTerritorial) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + SectorTerritorial.class.getName() + " where agente_sectorial_id = " + id)
                .getSingleResult();
    }


    public void guardar(AgenteSectorial agenteSectorial) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(agenteSectorial);
        EntityManagerHelper.commit();
    }

    public void guardarSector(SectorTerritorial sectorTerritorial) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(sectorTerritorial);
        EntityManagerHelper.commit();
    }

    public Set<SectorTerritorial> buscarSectoresSegunPais(int id) {
        return (Set<SectorTerritorial>) EntityManagerHelper
                .getEntityManager()
                .createQuery(" from " + SectorTerritorial.class.getName() + " where pais_id = " + id)
                .getResultList();
    }

    public List<SectorTerritorial> buscarTodosLosSectores(){
        return EntityManagerHelper.getEntityManager().createQuery("from " + SectorTerritorial.class.getName())
                .getResultList();
    }

    public SectorTerritorial buscarSector(Integer sector_territorial_id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(SectorTerritorial.class, sector_territorial_id);
    }
}
