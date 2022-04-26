package domain.verificadorContasenia;

import org.jetbrains.annotations.NotNull;

public class validarPorMinuscula extends Validacion{

    private int minimoMinuscula = 1;

    @Override
    public boolean claveValida(String clave) {
        if (this.isHabilitado()){
            return contieneMinuscula(clave);
        } else return true;
    }

    public boolean contieneMinuscula(@NotNull String contrasenia){
        int cantMinuscula = 0;
        char caracter; //Primero creo el caracter y despues le voy asignando distintos valores en la iteracion for
        for (int i = 0; i < contrasenia.length(); i++) {
            caracter = contrasenia.charAt(i); // para cargar un char a la vez
            if (Character.isLowerCase(caracter)) cantMinuscula++;
        }
        return (cantMinuscula >= minimoMinuscula);
    }
}
}
