package Validador;

import domain.verificadorContasenia.Validador;
import domain.verificadorContasenia.Validacion;
import domain.verificadorContasenia.validarPorDiezMilPeores;
import domain.verificadorContasenia.validarPorLongitud;
import domain.verificadorContasenia.validarPorMayuscula;
import domain.verificadorContasenia.validarPorMinuscula;
import domain.verificadorContasenia.validarPorNumero;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

public class validadorTest {

    public validarPorNumero validadorPorNumero;
    public validarPorMinuscula validadorPorMinuscula;
    public validarPorMayuscula validadorPorMayuscula;
    public validarPorLongitud validadorPorLongitud;
    public validarPorDiezMilPeores validadorPorDiezMilPeores;

    @Before
    public void init(){
        this.validadorPorNumero = new validarPorNumero();
        this.validadorPorDiezMilPeores = new validarPorDiezMilPeores();
        this.validadorPorLongitud = new validarPorLongitud();
        this.validadorPorMayuscula = new validarPorMayuscula();
        this.validadorPorMinuscula = new validarPorMinuscula();
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

    @Test
    public void unaContraseniaQueNoEsValidaPorDiezMil() throws FileNotFoundException {
        String claveNoValida = "password";
        Assert.assertFalse(validadorPorDiezMilPeores.claveValida(claveNoValida));
    }

}
