package domain.models.entities.sectorTerritorial;

//import com.google.gson.JsonParser;
//import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import domain.services.dbManager.EntityManagerHelper;
import org.json.*;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LocalidadesJson {

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
