import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Exceptions.ErrorDeAutenticacionException;
import Exceptions.UsuarioNoExisteException;
import Organizacion.IngresoFallidoException;
import Usuario.TipoUsuario;
import Usuario.Usuario;

public class MainClass{

	private static RepoUsuariosDB usuarios = new RepoUsuariosDB();
	private static EntityTransaction transaccion = entityManager().getTransaction();
    
    public static void main(String[] args){
    	imprimirPorPantalla("****************** GeSoc: Gestion de Proyectos Sociales *************************"
    	+ "\n >> Desea registrarse? (y/n)");
    	
    	if(leerConsola().equals("y")) {
    		registrarUsuario();
    	}

		Usuario usuario = iniciarSesion();

        leerBandejaDeUsuario(usuario);

        finalizarSesion();
        
        main(null);
    }

	private static Usuario iniciarSesion(){
		imprimirPorPantalla("\n***************************** Inicio de sesion *****************************\n");
		imprimirPorPantalla("Ingrese usuario");
		String usuarioIngresado = /*"usuario";*/leerConsola();
		imprimirPorPantalla("Ingrese contraseña");
        String passwordIngresada = /*"Tp2020Dds";*/leerConsola();

        Usuario usuario;

        try{
        	usuario = usuarios.getUsuario(usuarioIngresado);
        	usuario.autenticar(passwordIngresada);
        }

        catch(UsuarioNoExisteException | ErrorDeAutenticacionException unaExcepcion) {
        	throw new IngresoFallidoException();
        }


        return usuario;
	}

	private static void registrarUsuario() {
		imprimirPorPantalla("\n***************************** Registrarse gratis *****************************\n");
		imprimirPorPantalla("Ingrese nuevo usuario");
		String usuarioIngresado = /*"usuario";*/ leerConsola();
		imprimirPorPantalla("Ingrese nueva contraseña");
        String passwordIngresada = /*"Tp2020Dds";*/leerConsola();
        
        Usuario usuario;
        
        try{
        	usuario = new Usuario(usuarioIngresado, passwordIngresada, TipoUsuario.ESTANDAR);
    		transaccion.begin();
    		usuario.setBandejaDeMensajes(Arrays.asList("Le damos la bienvenida a nuestro sistema", "Otro mensaje"));
    		usuarios.agregar(usuario);
    		transaccion.commit();
    		imprimirPorPantalla(" >> Ha sido registrado correctamente\n");
        
        } catch(RuntimeException e){
        	imprimirPorPantalla("Error: " + e.getMessage() + "\n");
        	registrarUsuario();
        }
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

	// Auxiliares **********************
	
	private static String leerConsola(){
		Scanner sc = new Scanner(System.in);
		String lectura = sc.nextLine();

		return lectura;
	}

	private static void imprimirPorPantalla(String cadena){
		System.out.println(cadena);
	}

	private static EntityManager entityManager(){
		return PerThreadEntityManagers.getEntityManager();
	}
}