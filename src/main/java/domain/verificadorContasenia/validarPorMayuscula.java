package domain.verificadorContasenia;

import org.jetbrains.annotations.NotNull;

public class validarPorMayuscula extends Validacion {


    private int minimoMayuscula = 1;

    @Override
    public boolean claveValida(String clave) {
        if (this.isHabilitado()){
            return contieneMayuscula(clave);
        } else return true;
    }

    public boolean contieneMayuscula(@NotNull String contrasenia){
        int cantMayusculas = 0;
        char caracter; //Primero creo el caracter y despues le voy asignando distintos valores en la iteracion for
        for (int i = 0; i < contrasenia.length(); i++) {
            caracter = contrasenia.charAt(i); // para cargar un char a la vez
            if (Character.isUpperCase(caracter)) cantMayusculas++;
        }
        return (cantMayusculas >= minimoMayuscula);
    }
}

