package domain.verificadorContasenia;

import org.jetbrains.annotations.NotNull;


public class ValidarPorNumero extends Validacion{

    private int minimoNumeros = 1;

    @Override
    public boolean claveValida(String clave) {
        if (this.isHabilitado()){
            return contieneNumero(clave);
        } else return true;
    }

    public boolean contieneNumero(@NotNull String contrasenia){
        int cantNumeros = 0;
        char caracter; //Primero creo el caracter y despues le voy asignando distintos valores en la iteracion for
        for (int i = 0; i < contrasenia.length(); i++) {
            caracter = contrasenia.charAt(i); // para cargar un char a la vez
             if (Character.isDigit(caracter)) cantNumeros++;
        }
        return (cantNumeros >= minimoNumeros);
    }
}
