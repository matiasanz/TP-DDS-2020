package Locacion;

import Compra.PresupuestoElegidoNoSeEncuentraEntreLosPresupuestosException;
import Repositorios.RepositorioDeLocaciones.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Proveedor.Pais;

public class TestRepositorioDeLocacionesMeli {
    @Test()
    public void encuentroLocacionEnAPI() {
        RepositorioDeLocacionesMeli repositorio = new RepositorioDeLocacionesMeli();

        Locacion locacion = repositorio.getLocacion(Pais.AR, "1407");

        Assert.assertEquals(Pais.AR, locacion.getCodigoPais());
        Assert.assertEquals("Capital Federal",locacion.getProvincia());
    }

    @Test(expected = LocacionNoEncontradaException.class)
    public void noEncuentroLocacionEnAPI() {
        RepositorioDeLocacionesMeli repositorio = new RepositorioDeLocacionesMeli();
        repositorio.getLocacion(Pais.AR, "0");
    }
}