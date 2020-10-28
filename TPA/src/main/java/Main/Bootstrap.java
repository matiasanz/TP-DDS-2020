package Main;

import Categoria.Categoria;
import Direccion.Direccion;
import Direccion.Pais;
import Entidad.Entidad;
import Entidad.*;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;
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
            persist(ong);
            persist(corporacion);
            persist(industriaAlimenticia);
            persist(entidad1);
            persist(entidad2);
        });
    }

}