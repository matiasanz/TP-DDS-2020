package Main;

import Categoria.Categoria;
import Direccion.Direccion;
import Direccion.Pais;
import Entidad.Entidad;
import Entidad.*;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;
import Factory.EntidadesFactory;
import Factory.MedioDePagoFactory;
import Factory.ProveedoresFactory;
import Factory.UsuariosFactory;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

    public static void main(String[] args) {
        new Bootstrap().run();
    }

    public void run() {

        Categoria ong = new Categoria("ONG");
        Categoria corporacion = new Categoria("Corporacion");
        Categoria industriaAlimenticia = new Categoria("Industria Alimenticia");

        Direccion direccion = new Direccion(new RepositorioDeLocacionesMeli(), "Altolaguirre", 57, 0, "1874", Pais.AR);

        Entidad entidad1 = new Empresa("Arcos Dorados S.A.", "McDonalds", "20404040401",
                direccion, 10, null, Clasificacion.MEDIANATRAMO2);
        entidad1.agregarCategoria(corporacion);

        Entidad entidad2 = new OrganizacionSectorSocial("Comedero Juan Carlos", "Comedero Juan Carlos", "20402545401",
                direccion, 124, null);
        entidad2.agregarCategoria(ong);
        entidad2.agregarCategoria(industriaAlimenticia);


        withTransaction(() -> {
            crearProveedores();
            crearMediosDePago();
            crearEntidades();
            mockearUsuarios();
            persist(ong);
            persist(corporacion);
            persist(industriaAlimenticia);
            persist(entidad1);
            persist(entidad2);
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