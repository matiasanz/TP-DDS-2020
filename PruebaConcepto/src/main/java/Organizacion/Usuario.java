package Organizacion;

public class Usuario {
	private Tipo tipo;
	String username;
	String contrasenia; // Esto obvio no quedaría como string
	
	public Usuario (String username, String contrasenia) {
		
		if (validarPassword(contrasenia)) {
			this.username = username;
			this.contrasenia = contrasenia;
		}
		
		else {
			throw new RuntimeException(); //hay que modelar una excepcion
		}	
	}

	private boolean validarPassword(String contrasenia) {
		boolean ok = false;
		
		//Logica para validar una contraseña
		
		return ok;
	}
	
	public void cambiarContrasenia(String contrasenia) {
		if (validarPassword(contrasenia)) {
			this.contrasenia = contrasenia;
		}
		else {
			throw new RuntimeException(); //hay que modelar una excepcion
		}	
	}
}
