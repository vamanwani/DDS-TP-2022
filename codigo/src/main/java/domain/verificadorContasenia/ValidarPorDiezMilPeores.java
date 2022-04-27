package domain.verificadorContasenia;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class ValidarPorDiezMilPeores extends Validacion{

    @Override
    public boolean claveValida(String clave) throws FileNotFoundException {

        File file = new File("src/main/resources/top10000passwords.txt"); // abrir archivo
        boolean valido = true;
        Scanner inputFile = new Scanner (file); // crear scanner
        inputFile.useDelimiter("\n"); // usar barra n para separar

        while (inputFile.hasNext()) {
            if (clave.equals(inputFile.next())) {
                valido = false;
            }
        }
        return valido; //es decir, no es parte de las diezmil peores
    }
}

