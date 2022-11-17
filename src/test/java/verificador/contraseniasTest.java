package verificador;

import domain.verificadorContasenia.Verificador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

public class contraseniasTest {

    public Verificador contrasenia = new Verificador();

    public contraseniasTest() throws FileNotFoundException {
    }

    @Test
    public void noEsValida() throws FileNotFoundException {
        Assert.assertFalse(contrasenia.contraseniaValida("password"));
    }

    @Test
    public void esValida() throws FileNotFoundException {
        Assert.assertTrue(contrasenia.contraseniaValida("unaContraVal1da"));
    }
}
