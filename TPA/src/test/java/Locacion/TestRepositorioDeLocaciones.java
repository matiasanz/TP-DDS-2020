package Locacion;
import Repositorios.RepositorioDeLocaciones.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Proveedor.Pais;

public class TestRepositorioDeLocaciones {
	@Test()
    public void encuentroLocacionEnAPI() {
		RepositorioDeLocacionesMock repositorio = new RepositorioDeLocacionesMock();
		repositorio.setEstado("locacionEncontrada");
		
		Locacion locacionEsperada = new Locacion(Pais.AR, "CABA", "Caballito");
		Locacion locacionObtenida = repositorio.getLocacion(Pais.AR, "1111");
		
		boolean coincideCodigo = locacionEsperada.getCodigoPais() == locacionObtenida.getCodigoPais();
		boolean coincideEstado = locacionEsperada.getProvincia() == locacionObtenida.getProvincia();
		boolean coincideCiudad = locacionEsperada.getCiudad() == locacionObtenida.getCiudad();
		
		Assert.assertTrue(coincideCodigo && coincideEstado && coincideCiudad);
	}
	
	@Test()
	public void noEncuentroLocacionEnAPI() {
		RepositorioDeLocacionesMock repositorio = new RepositorioDeLocacionesMock();
		
		Locacion locacionObtenida = repositorio.getLocacion(Pais.AR, "1111");
		
		Assert.assertEquals(null, locacionObtenida);
	}
}