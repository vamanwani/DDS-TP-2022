package Validador;

import domain.verificadorContasenia.ValidarPorDiezMilPeores;
import domain.verificadorContasenia.ValidarPorLongitud;
import domain.verificadorContasenia.ValidarPorMayuscula;
import domain.verificadorContasenia.ValidarPorMinuscula;
import domain.verificadorContasenia.ValidarPorNumero;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidadorTest {

    public ValidarPorNumero validadorPorNumero;
    public ValidarPorMinuscula validadorPorMinuscula;
    public ValidarPorMayuscula validadorPorMayuscula;
    public ValidarPorLongitud validadorPorLongitud;
    public ValidarPorDiezMilPeores validadorPorDiezMilPeores;

    @Before
    public void init(){
        this.validadorPorNumero = new ValidarPorNumero();
        this.validadorPorDiezMilPeores = new ValidarPorDiezMilPeores();
        this.validadorPorLongitud = new ValidarPorLongitud();
        this.validadorPorMayuscula = new ValidarPorMayuscula();
        this.validadorPorMinuscula = new ValidarPorMinuscula();
    }

    @Test
    public void validezSegunNumero(){
        String claveNoValida = "password";
        String claveValida = "12345678";
        Assert.assertFalse(validadorPorNumero.claveValida(claveNoValida));
        Assert.assertTrue(validadorPorNumero.claveValida(claveValida));
    }

    @Test
    public void validezSegunMinusculas(){
        String claveNoValida = "PASSWORD";
        String claveValida = "PASSWord";
        Assert.assertFalse(validadorPorMinuscula.claveValida(claveNoValida));
        Assert.assertTrue(validadorPorMinuscula.claveValida(claveValida));
    }

    @Test
    public void validezSegunMayusculas(){
        String claveNoValida = "password";
        String claveValida = "Password";
        Assert.assertFalse(validadorPorMayuscula.claveValida(claveNoValida));
        Assert.assertTrue(validadorPorMayuscula.claveValida(claveValida));
    }

    @Test
    public void validezSegunLongitud(){
        String claveNoValida = "pass";
        String claveValida = "12345678";
        Assert.assertFalse(validadorPorLongitud.claveValida(claveNoValida));
        Assert.assertTrue(validadorPorLongitud.claveValida(claveValida));
    }

}
