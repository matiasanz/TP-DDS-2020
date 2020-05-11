package Organizacion;
import java.util.List;
import java.util.ArrayList;

import Compra.Compra;
import Entidad.Entidad;
import Entidad.EntidadJuridica;
import Usuario.Usuario;

public class Organizacion {
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Entidad> listaEntidades =  new ArrayList<>();
	private List<Compra> listaCompras = new ArrayList<>();
	
	public void registroAutenticacionUsuario(String usuario, String contrasenia) {
		// Logica para acceder al sistema? no me acuerdo que era esto
	}
}
