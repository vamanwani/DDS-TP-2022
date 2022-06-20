package domain.services.adapters;

import domain.services.entities.Distancia;
import domain.services.entities.Localidad;
import domain.ubicacion.Ubicacion;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.*;
import java.util.List;

public class ServicioGeodds {
    private static ServicioGeodds instancia=null;
    private static GeoddsServceAdapter adapter;
    private static String llaveAutorizacion;
    private static final String urlApi ="https://ddstpa.com.ar/api/";
    private Retrofit retrofit;
    //private OkHttpClient cliente;

    public static void setLlaveAutorizacion(String llaveAutorizacion) {
        ServicioGeodds.llaveAutorizacion = llaveAutorizacion;
    }

    public static String getLlaveAutorizacion() {
        return llaveAutorizacion;
    }

    public ServicioGeodds() {
        //this.adapter = new ServicioGeoDdsRetrofitAdapter();
    }

    public static void setAdapter(GeoddsServceAdapter adapter) {
        ServicioGeodds.adapter = adapter;
    }

    public static ServicioGeodds getInstance() throws IOException {
        if(instancia == null){
            instancia = new ServicioGeodds();
        }
        return instancia;
    }
    public Distancia distancia(Ubicacion origen, Ubicacion destino) throws IOException {
        return adapter.distancia(origen,destino);
    }
    public int distanciaRespuesta(Ubicacion origen, Ubicacion destino) throws IOException {
        return adapter.distanciaRespuesta(origen, destino);
    }


    public List<Localidad> localidades() throws IOException {
        return adapter.localidades();
    }

    public int localidadesRespuesta() throws IOException{
        return adapter.localidadesRespuesta();
    }


}
