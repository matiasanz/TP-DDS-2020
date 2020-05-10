package Organizacion;
import java.util.ArrayList;

import Compra.Compra;
import Entidad.Entidad;
import Entidad.EntidadJuridica;
import Usuario.Usuario;

public class Organizacion {
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<Entidad> listaEntidades =  new ArrayList<>();
	private ArrayList<Compra> listaCompras = new ArrayList<>();
	
	public void registroAutenticacionUsuario(String usuario, String contrasenia) {
		// Logica para acceder al sistema? no me acuerdo que era esto
	}
}
