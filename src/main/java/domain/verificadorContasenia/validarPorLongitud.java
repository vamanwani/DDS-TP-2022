package domain.verificadorContasenia;

public class validarPorLongitud extends Validacion{

    private int largoContrasenia = 8; //puede variar

    @Override
    public boolean claveValida(String clave) {
        return (clave.length() >= largoContrasenia);
    }
}
