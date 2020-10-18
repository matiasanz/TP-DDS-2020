import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Factory.UsuariosFactory;
import Repositorios.RepositorioDeUsuarios.RepoUsuariosDB;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Organizacion.IngresoFallidoException;
import Repositorios.RepositorioDeUsuarios.UsuarioNoExisteException;
import Usuario.ErrorDeAutenticacionException;
import Usuario.Usuario;

public class MainClass{

	private static RepoUsuariosDB usuarios = new RepoUsuariosDB();
	private static EntityTransaction transaccion = entityManager().getTransaction();

    public static void main(String[] args){

		agregarDatosADB();

		Usuario usuario = iniciarSesion();

        leerBandejaDeUsuario(usuario);

        finalizarSesion();

    }

	private static void agregarDatosADB() {
		transaccion.begin();
		Usuario usuario = UsuariosFactory.usuarioStub();
		usuario.setBandejaDeMensajes(Arrays.asList("Un mensaje", "Otro mensaje"));
		usuarios.agregar(usuario);
		transaccion.commit();
	}

	private static Usuario iniciarSesion(){
		imprimirPorPantalla("\n***************************** Inicio de sesion *****************************\n");
		imprimirPorPantalla("Ingrese usuario");
		String usuarioIngresado = /*"usuario";*/leerConsola();
		imprimirPorPantalla("Ingrese contrase�a");
        String passwordIngresada = /*"Tp2020Dds";*/leerConsola();

        Usuario usuario;

        try{
        	transaccion.begin();
        	usuario = usuarios.getUsuario(usuarioIngresado);
        	usuario.autenticar(passwordIngresada);
        }

        catch(UsuarioNoExisteException | ErrorDeAutenticacionException unaExcepcion) {
        	throw new IngresoFallidoException();
        }


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