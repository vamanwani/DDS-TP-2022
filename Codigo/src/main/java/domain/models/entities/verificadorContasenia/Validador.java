package domain.models.entities.verificadorContasenia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Validador {

    private List<Validacion> validaciones;
    private List<String> contraseniasNoSeguras;

    public ValidarPorNumero validadorPorNumero;
    public ValidarPorMinuscula validadorPorMinuscula;
    public ValidarPorMayuscula validadorPorMayuscula;
    public ValidarPorLongitud validadorPorLongitud;
    public ValidarPorDiezMilPeores validadorPorDiezMilPeores;

    public List<Validacion> getValidaciones() {
        return validaciones;
    }

    public List<String> getContraseniasNoSeguras() {
        return contraseniasNoSeguras;
    }

    public void setValidaciones(List<Validacion> validaciones) {
        this.validaciones = validaciones;
    }

    public Validador() throws FileNotFoundException {
        this.contraseniasNoSeguras = new ArrayList<>();
        this.validaciones = new ArrayList<>();
        guardarListaNoSegura();
        this.validadorPorNumero = new ValidarPorNumero();
        this.validadorPorMinuscula = new ValidarPorMinuscula();
        this.validadorPorMayuscula = new ValidarPorMayuscula();
        this.validadorPorLongitud = new ValidarPorLongitud();
        this.validadorPorDiezMilPeores = new ValidarPorDiezMilPeores(this.contraseniasNoSeguras);
    }



    public void guardarListaNoSegura() throws FileNotFoundException {
        File file = new File("target/classes/top10000passwords.txt"); // abrir archivo
        Scanner inputFile = new Scanner (file); // crear scanner
        inputFile.useDelimiter("\n"); // usar barra n para separar
        while (inputFile.hasNext()) {
            contraseniasNoSeguras.add(inputFile.next());
        }
        inputFile.close();
    }

    /*public boolean contraseniaNoPerteneceALista(String contraseniaAVerificar) throws FileNotFoundException {
        return validadorPorDiezMilPeores.claveValida(contraseniaAVerificar);
    }*/

    public void agregarValidador(Validacion tipoValidador)
    {
        if (!this.validaciones.contains(tipoValidador))
        {
            this.validaciones.add(tipoValidador);
        }
    }

    public void removerValidador(Validacion tipoValidador)
    {
        if (this.validaciones.contains(tipoValidador))
        {
            this.validaciones.remove(tipoValidador);
        }
    }

    public boolean validadorPertenece(Validacion tipoValidador)
    {
        return this.validaciones.contains(tipoValidador);
    }

    public boolean todosLosValidadores(String claveAValidar) throws FileNotFoundException {
        boolean esValido = true;
        for (Validacion validado: validaciones) {
            esValido = esValido && validado.claveValida(claveAValidar);
        }
        return esValido;
    }
}