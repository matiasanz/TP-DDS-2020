package Main;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Organizacion.IngresoFallidoException;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import Repositorios.RepositorioDeUsuarios.UsuarioNoExisteException;
import Usuario.ErrorDeAutenticacionException;
import Usuario.Usuario;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

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
    	repoUsuarios.agregar(new Usuario("usuario", "Tp2020Dds"));
    }
}