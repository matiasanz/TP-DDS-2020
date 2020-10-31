package Main;

import Factory.DireccionesFactory;
import Factory.EntidadesFactory;
import Factory.MedioDePagoFactory;
import Factory.ProveedoresFactory;
import Proveedor.Proveedor;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

    public static void main(String[] args) {
        new Bootstrap().run();
    }

    public void run() {
        withTransaction(() -> {
            crearProveedores();
            crearMediosDePago();
            crearEntidades();
        });
    }

    private void crearEntidades() {
        persist(EntidadesFactory.empresaMedianaTramo2());
        persist(EntidadesFactory.getEntidadJuridica());
    }

    private void crearProveedores() {
        persist(ProveedoresFactory.ProveedorJuanPerez());
        persist(ProveedoresFactory.ProveedorMaster());
        persist(ProveedoresFactory.ProveedorOne());
    }

    private void crearMediosDePago() {
        persist(MedioDePagoFactory.effectivo());
        persist(MedioDePagoFactory.tarjetaDeCreditoVisa());
        persist(MedioDePagoFactory.tarjetaDeDebito());
    }

}