package Main;

import Categoria.Categoria;
import Entidad.EntidadJuridica;
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

	public static void main(String[] args) {
        new Bootstrap().run();
    }

    public void run() {
    	//Cargamos la cache
    	RepositorioDeMonedasMeli.getInstance().getMonedas(Moneda.codigosMoneda());

        withTransaction(() -> {
            crearProveedores();
            crearMediosDePago();
            crearEntidadesYCategorias();
            mockearUsuarios();
        });
    }

    private void mockearUsuarios() {
        RepoUsuariosDB repoUsuarios = new RepoUsuariosDB();
        repoUsuarios.salvar(UsuariosFactory.usuarioStub());
        repoUsuarios.salvar(UsuariosFactory.sinValidaciones("beto", "123"));
    }

    private void crearEntidadesYCategorias() {
	    Categoria corpo = new Categoria("Corporacion");
	    Categoria alimentos = new Categoria("Industria Alimenticia");

        persist(new Categoria("ONG"));
        persist(corpo);
        persist(alimentos);

	    EntidadJuridica mc = EntidadesFactory.empresaMedianaTramo2();
	    mc.agregarCategoria(corpo);
	    mc.agregarCategoria(alimentos);

        persist(mc);
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