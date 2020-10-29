package Main;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Factory.UsuariosFactory;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {	
    public static void main(String[] args) {
        new Bootstrap().run();
    }

    public void run() {
        withTransaction(() -> {
        	mockearUsuarios();
//            persist(new Consultora("dblandit", 10));
        });
    }
    
    private void mockearUsuarios(){
    	RepoUsuariosDB repoUsuarios = new RepoUsuariosDB();    	
    	repoUsuarios.agregar(UsuariosFactory.usuarioStub());
    	repoUsuarios.agregar(UsuariosFactory.sinValidaciones("beto", "123"));
    }
}