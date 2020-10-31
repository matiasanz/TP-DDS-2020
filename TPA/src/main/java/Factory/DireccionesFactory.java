package Factory;

import Direccion.Direccion;
import Direccion.Pais;
import Repositorios.RepositorioDeLocaciones.*;

public class DireccionesFactory {

    private static RepositorioDeLocaciones repositorioDeLocaciones = new RepositorioDeLocacionesMock();

    public static Direccion direccionStub9DeJulio() {
        return new Direccion(repositorioDeLocaciones, "9 de Julio", 15, 1, "1212", Pais.AR);
    }

    public static Direccion direccionStubCordoba() {
        return new Direccion(repositorioDeLocaciones, "Cordoba", 1577,0, "1412", Pais.AR);
    }

    public static Direccion direccionStubCervantes() {
        return new Direccion(repositorioDeLocaciones, "Cervantes", 607,5, "1407", Pais.BR);
    }
}
