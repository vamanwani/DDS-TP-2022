package domain.models.entities.sectorTerritorial;

//import com.google.gson.JsonParser;
//import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import domain.models.repos.RepositorioDeProvincias;
import domain.models.repos.RepositorioDeUbicaciones;
import domain.services.dbManager.EntityManagerHelper;
import org.json.*;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LocalidadesJson {

    RepositorioDeProvincias repositorioDeProvinicias = new RepositorioDeProvincias();

    private List<Localidad> localidades;
    private JsonParser parser;
    private Object archivoJsonObjeto;
    private JsonArray archivoJson;

    public LocalidadesJson(String ruta) throws FileNotFoundException {
        parser = new JsonParser();
        this.localidades = new ArrayList<>();
        archivoJsonObjeto = parser.parse(new FileReader(ruta));
        archivoJson = (JsonArray) archivoJsonObjeto;
    }

    public void instanciarLocalidad(JsonObject unArchivoJson)
    {
        Localidad unaLocalidad = new Localidad((unArchivoJson.get("nombre")).getAsString());
        unaLocalidad.setId((Integer) unArchivoJson.get("id").getAsInt());
        JsonObject jsonObject = unArchivoJson.get("municipio").getAsJsonObject();
        System.out.println(jsonObject);
        System.out.println(jsonObject.get("id").getAsInt());
        Provincia provincia = repositorioDeProvinicias.buscarSegunNombre(jsonObject.get("nombre").getAsString());
        System.out.println(unaLocalidad.getNombre());
        System.out.println(provincia.getNombre());
        provincia.agregarLocalidad(unaLocalidad);
//        Provincia provincia = repositorioDeProvinicias.buscar(jsonObject);
//        provincia.agregarLocalidad(unaLocalidad);
//        EntityManagerHelper.persist(provincia);
        localidades.add(unaLocalidad);
    }


    public void instanciarLocalidades() {
        this.archivoJson.forEach(objetoLocalidad -> instanciarLocalidad((JsonObject) objetoLocalidad));
    }

    public void persistirLocalidades()
    {
        EntityManagerHelper.beginTransaction();
        this.localidades.forEach(unaLocalidad -> EntityManagerHelper.persist(unaLocalidad));
        EntityManagerHelper.commit();
    }

}
