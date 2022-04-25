package domain.verificadorContasenia;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Verificador {

    public int largoContrasenia = 8; // puede variar
    Scanner input = new Scanner(System.in);
    boolean laContraseniaEsValida;

    public Verificador() throws FileNotFoundException { //hay que aniadir un constructor para la clase
        System.out.println("Ingrese una contrasenia: ");
        String contraseniaIngresada = input.next();
        this.laContraseniaEsValida = contraseniaValida(contraseniaIngresada);
        if (laContraseniaEsValida) {
            System.out.println("Es valida");
        } else {
            System.out.println("Es invalida");
        }
    }

    public boolean contraseniaValida(@NotNull String contrasenia) throws FileNotFoundException {
        if (contrasenia.length() < largoContrasenia) return false; // ya de por si, si es menor a 8, no es valida

        int mayusculas = 0;
        int minusculas = 0;
        int numeros = 0;

        for (int i = 0; i < contrasenia.length(); i++) {
            char caracter = contrasenia.charAt(i); // para cargar un char a la vez

            if (Character.isUpperCase(caracter)) mayusculas++;
            else if (Character.isLowerCase(caracter)) minusculas++;
            else if (Character.isDigit(caracter)) numeros++;
        }
        //if (mayusculas == 0 || minusculas == 0 || numeros == 0) return false; // si alguno esta en cero, no paso el al menos 1 para c/u

        return (mayusculas > 0 && minusculas > 0 && numeros > 0 && !formaParteDeLasPeoresContrasenias(contrasenia));
    }

    public boolean formaParteDeLasPeoresContrasenias (String contrasenia) throws FileNotFoundException {
        File file = new File("src/main/resources/top10000passwords.txt"); // abrir archivo
        Scanner inputFile = new Scanner (file); // crear scanner
        inputFile.useDelimiter("\n"); // usar barra n para separar

        while(inputFile.hasNext()){
            String contraseniaDebil = inputFile.next();
            if(contrasenia.equals(contraseniaDebil)) {
                return true;
            } else {
                return false;
            }
        }
        inputFile.close();
        return false;
    }
}

