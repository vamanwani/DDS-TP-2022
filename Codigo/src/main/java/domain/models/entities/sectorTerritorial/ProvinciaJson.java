package domain.models.entities.sectorTerritorial;
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
public class ProvinciaJson {
    private List<Provincia> provincias;
    private JsonParser parser;
    private Object archivoJsonObjeto;
    private JsonArray archivoJson;

    public ProvinciaJson(String ruta) throws FileNotFoundException
    {
        this.provincias = new ArrayList<>();
        parser = new JsonParser();
        archivoJsonObjeto = parser.parse(new FileReader(ruta));
        archivoJson = (JsonArray) archivoJsonObjeto;
    }

    public void instanciarUnaProvincia(JsonObject unArchivo)
    {
        Provincia unaProvincia = new Provincia(unArchivo.get("nombre").getAsString());
        unaProvincia.setId(unArchivo.get("id").getAsInt());
        provincias.add(unaProvincia);
    }

    public void instanciarProvincias()
    {
        this.archivoJson.forEach(objetoProvincia -> instanciarUnaProvincia((JsonObject) objetoProvincia));
    }

    public void persistirProvincias()
    {
        EntityManagerHelper.beginTransaction();
        this.provincias.forEach(unaProvincia -> EntityManagerHelper.persist(unaProvincia));
        EntityManagerHelper.commit();
    }
}
