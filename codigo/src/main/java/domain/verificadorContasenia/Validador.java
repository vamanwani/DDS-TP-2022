package domain.verificadorContasenia;

import domain.verificadorContasenia.Validacion;
import domain.verificadorContasenia.ValidarPorDiezMilPeores;
import domain.verificadorContasenia.ValidarPorLongitud;
import domain.verificadorContasenia.ValidarPorMayuscula;
import domain.verificadorContasenia.ValidarPorMinuscula;
import domain.verificadorContasenia.ValidarPorNumero;

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

    public void setValidaciones(List<Validacion> validaciones) {
        this.validaciones = validaciones;
    }

    public Validador() throws FileNotFoundException {
        this.contraseniasNoSeguras = new ArrayList<>();
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

    public boolean contraseniaNoPerteneceALista(String contraseniaAVerificar) throws FileNotFoundException {
        return validadorPorDiezMilPeores.claveValida(contraseniaAVerificar);
    }


}