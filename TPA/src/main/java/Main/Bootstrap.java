package Main;

import Factory.EntidadesFactory;
import Factory.MedioDePagoFactory;
import Factory.ProveedoresFactory;
import Factory.UsuariosFactory;
import Moneda.Moneda;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	private static RepositorioDeMonedasMeli repositorioDeMonedasMeli = new RepositorioDeMonedasMeli();
	
	public static void main(String[] args) {
        //Cargamos la cache
        repositorioDeMonedasMeli.getMonedas(Moneda.codigosMoneda());
    	
        new Bootstrap().run();
    }

    public void run() {
        withTransaction(() -> {
            crearProveedores();
            crearMediosDePago();
            crearEntidades();
            mockearUsuarios();
        });
    }

    private void mockearUsuarios() {
        RepoUsuariosDB repoUsuarios = new RepoUsuariosDB();
        repoUsuarios.agregar(UsuariosFactory.usuarioStub());
        repoUsuarios.agregar(UsuariosFactory.sinValidaciones("beto", "123"));
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