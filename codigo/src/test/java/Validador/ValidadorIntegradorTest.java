package Validador;

import domain.verificadorContasenia.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;


public class ValidadorIntegradorTest {
    public ValidarPorNumero validadorPorNumero;
    public ValidarPorMinuscula validadorPorMinuscula;
    public ValidarPorMayuscula validadorPorMayuscula;
    public ValidarPorLongitud validadorPorLongitud;
    public ValidarPorDiezMilPeores validadorPorDiezMilPeores;
    public Validador validadorIntegrador;

    @Before
    public void init() throws FileNotFoundException {
        this.validadorIntegrador = new Validador();
        this.validadorPorNumero = new ValidarPorNumero();
        this.validadorPorDiezMilPeores = new ValidarPorDiezMilPeores(this.validadorIntegrador.getContraseniasNoSeguras());
        this.validadorPorLongitud = new ValidarPorLongitud();
        this.validadorPorMayuscula = new ValidarPorMayuscula();
        this.validadorPorMinuscula = new ValidarPorMinuscula();
    }
    @Test
    public void agregarValidador() throws FileNotFoundException
    {
        validadorIntegrador.agregarValidador(validadorPorNumero);
        validadorIntegrador.agregarValidador(validadorPorLongitud);
        Assert.assertTrue(validadorIntegrador.validadorPertenece(validadorPorNumero));
        Assert.assertTrue(validadorIntegrador.validadorPertenece(validadorPorLongitud));
        Assert.assertFalse(validadorIntegrador.validadorPertenece(validadorPorMayuscula));
    }

    @Test
    public void removerValidador() throws FileNotFoundException
    {
        validadorIntegrador.agregarValidador(validadorPorNumero);
        validadorIntegrador.agregarValidador(validadorPorLongitud);
        validadorIntegrador.removerValidador(validadorPorNumero);
        Assert.assertFalse(validadorIntegrador.validadorPertenece(validadorPorNumero));
        Assert.assertTrue(validadorIntegrador.validadorPertenece(validadorPorLongitud));
        Assert.assertFalse(validadorIntegrador.validadorPertenece(validadorPorMayuscula));
    }

    @Test
    public void testValidadorIntegrador() throws FileNotFoundException
    {
        validadorIntegrador.agregarValidador(validadorPorNumero);
        validadorIntegrador.agregarValidador(validadorPorLongitud);
        validadorIntegrador.agregarValidador(validadorPorMayuscula);
        validadorIntegrador.agregarValidador(validadorPorMinuscula);
        validadorIntegrador.agregarValidador(validadorPorDiezMilPeores);
        String claveValidaMenosNumero = "PPSLDWMDASDWDASDASDasdw1";
        Assert.assertTrue(validadorIntegrador.todosLosValidadores(claveValidaMenosNumero));
    }

    @Test
    public void testValidadorIntegradorIncumplido() throws FileNotFoundException
    {
        validadorIntegrador.agregarValidador(validadorPorNumero);
        validadorIntegrador.agregarValidador(validadorPorLongitud);
        validadorIntegrador.agregarValidador(validadorPorMayuscula);
        validadorIntegrador.agregarValidador(validadorPorMinuscula);
        validadorIntegrador.agregarValidador(validadorPorDiezMilPeores);
        String claveValidaMenosNumero = "PPSLDWMDASDWDASDASD1";
        Assert.assertFalse(validadorIntegrador.todosLosValidadores(claveValidaMenosNumero));
    }
}
