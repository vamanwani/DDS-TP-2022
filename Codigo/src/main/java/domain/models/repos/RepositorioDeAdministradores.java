package domain.models.repos;

import domain.models.entities.miembro.Adminisitrador;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.services.dbManager.EntityManagerHelper;

public class RepositorioDeAdministradores {
    public Adminisitrador buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Adminisitrador.class, id);
    }

    public Adminisitrador buscarSegunUsuarioId(int usuario){
        String query = "from " + Adminisitrador.class.getName() +" where usuario_id=" + usuario;
        System.out.println("query: " + query);
        return (Adminisitrador) EntityManagerHelper
                .getEntityManager()
                .createQuery(query)
                .getSingleResult();
    }
}
