package domain.verificadorContasenia;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class validarPorDiezMilPeores extends Validacion{

    @Override
    public boolean claveValida(String clave) throws FileNotFoundException {
        File file = new File("src/main/resources/top10000passwords.txt"); // abrir archivo
        Scanner inputFile = new Scanner (file); // crear scanner
        inputFile.useDelimiter("\n"); // usar barra n para separar
        while(inputFile.hasNext()){
            String contraseniaDebil = inputFile.next();
            if(clave.equals(contraseniaDebil)) {

                inputFile.close();
                return false;
            }
        }
        inputFile.close();
        return true; //es decir, no es parte de las diezmil peores
    }
}
