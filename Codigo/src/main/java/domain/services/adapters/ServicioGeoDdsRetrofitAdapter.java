package domain.services.adapters;

import domain.services.entities.Distancia;
import domain.services.entities.Localidad;
import domain.ubicacion.Ubicacion;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class ServicioGeoDdsRetrofitAdapter implements  GeoddsServceAdapter{
    private static ServicioGeodds instancia=null;
    private static final String urlApi ="https://ddstpa.com.ar/api/";
    private Retrofit retrofit;
    private String llaveAutorizacion;

    public void setLlaveAutorizacion(String llaveAutorizacion) {this.llaveAutorizacion = llaveAutorizacion;
    }

    public ServicioGeoDdsRetrofitAdapter(){
        this.retrofit=new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public Distancia distancia(Ubicacion origen, Ubicacion destino) throws IOException {
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<Distancia> requestDistancia=geoddsService.distancia(
                llaveAutorizacion,
                origen.idLocalidad(),
                origen.getCalle(), origen.getAltura(),
                destino.idLocalidad(),destino.getCalle(),
                destino.getAltura());
        Response<Distancia> responseDistancia=requestDistancia.execute();
        return responseDistancia.body();
    }

    @Override
    public int distanciaRespuesta(Ubicacion origen, Ubicacion destino) throws IOException {
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<Distancia> requestDistancia=geoddsService.distancia(
                llaveAutorizacion,
                origen.idLocalidad(),
                origen.getCalle(), origen.getAltura(),
                destino.idLocalidad(),destino.getCalle(),
                destino.getAltura());
        Response<Distancia> responseDistancia=requestDistancia.execute();
        return responseDistancia.code();
    }

    @Override
    public List<Localidad> localidades() throws IOException {
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<List<Localidad>> requestlocalidades=geoddsService.localidades(
                //"application/json",
                llaveAutorizacion,
                1,241);
        Response<List<Localidad>> responselocalidades=requestlocalidades.execute();
        return responselocalidades.body();
    }

    @Override
    public int localidadesRespuesta() throws IOException {
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<List<Localidad>> requestlocalidades=geoddsService.localidades(
                //"application/json",
                llaveAutorizacion,
                1,241);
        Response<List<Localidad>> responselocalidades=requestlocalidades.execute();
        return responselocalidades.code();
    }

    private void tokenDeAutorizacion() throws IOException {
        File tokenArchivo = new File("target/classes/tokenAutorizacion.txt");
        BufferedReader tokenLinea = new BufferedReader(new FileReader(tokenArchivo));
        setLlaveAutorizacion("Bearer " + tokenLinea.readLine());
    }

}
