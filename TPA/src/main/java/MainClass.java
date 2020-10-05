import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Organizacion.IngresoFallidoException;
import Organizacion.OrganizacionMock;
import Repositorios.RepositorioDeUsuarios.RepositorioDeUsuarios;
import Repositorios.RepositorioDeUsuarios.UsuarioNoExisteException;
import Usuario.ErrorDeAutenticacionException;
import Usuario.Usuario;

public class MainClass{

	private static RepositorioDeUsuarios usuarios = OrganizacionMock.getInstance().getRepoUsuarios();
//	private static RepoUsuariosDB usuarios = new RepoUsuariosDB();
//	private static EntityTransaction transaccion = 	entityManager().getTransaction();
	
    public static void main(String[] args){

    	Usuario usuario = iniciarSesion();
    	
        leerBandejaDeUsuario(usuario);
                
        finalizarSesion();
        
    }
    
	private static Usuario iniciarSesion(){
		imprimirPorPantalla("\n***************************** Inicio de sesion *****************************\n");
		imprimirPorPantalla("Ingrese usuario");
		String usuarioIngresado = /*"usuario";*/leerConsola();		
		imprimirPorPantalla("Ingrese contraseña");
        String passwordIngresada = /*"Tp2020Dds";*/leerConsola();
        
        Usuario usuario;
        
        try{
//        	transaccion.begin();    	
        	usuario = usuarios.getUsuario(usuarioIngresado);
        	usuario.autenticar(passwordIngresada);        	
        }
        
        catch(UsuarioNoExisteException | ErrorDeAutenticacionException unaExcepcion) {
//        	transaccion.rollback();
        	throw new IngresoFallidoException();
        }
        
//        transaccion.commit();
        
        return usuario;
	}
    
	private static void finalizarSesion(){
		imprimirPorPantalla("\n***************************** Fin de sesion *****************************\n");
	}
	
	private static void leerBandejaDeUsuario(Usuario usuario) {
		List<String>mensajes = usuario.getMensajes();
		
		if(mensajes.isEmpty()){
			imprimirPorPantalla("\n***** LA BANDEJA DE MENSAJES SE ENCUENTRA VACIA\n");			
			return;
		}
		
		imprimirPorPantalla("\n***** TIENE MENSAJES:\n");
		mensajes.stream().forEach(mensaje->imprimirPorPantalla(mensaje));
	}
		
//	********************* Auxiliares ***********************
	
	private static String leerConsola(){
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();        
	}
	
	private static void imprimirPorPantalla(String cadena){
		System.out.println(cadena);
	}
	
	private static EntityManager entityManager(){
		return PerThreadEntityManagers.getEntityManager();
	}
}