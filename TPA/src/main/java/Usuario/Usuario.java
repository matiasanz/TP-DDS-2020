package Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Usuario {
	private Tipo tipo;
	String username;
	String contrasenia; // Esto obvio no quedaría como string
	
	public Usuario (String username, String contrasenia) {
		
		validarPassword(contrasenia);

		this.username = username;
		this.contrasenia = contrasenia;
	
	}

	public void cambiarContrasenia(String contrasenia) {
		validarPassword(contrasenia);
		this.contrasenia = contrasenia;
	}
	
	void validarPassword(String contrasenia) {
		
		entreLasDiezMil(contrasenia);
//		validacion1(contrasenia);
//		validacion2(contrasenia);
//		validacion3(contrasenia);
	}

	private void validacion3(String contrasenia2) {
		// TODO Auto-generated method stub
		
	}

	private void validacion2(String contrasenia2) {
		// TODO Auto-generated method stub
		
	}

	private void validacion1(String contrasenia2) {
		// TODO Auto-generated method stub
		
	}

	void entreLasDiezMil(String contrasenia) {
		
		File file = new File("10000WorstPasswords.txt");
		try {
		    Scanner scanner = new Scanner(file);
	
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.equals(contrasenia)) { 
		           throw new contraseniaEntreLasDiezMilException();
		        }
		    }
			scanner.close();
			
		} catch(FileNotFoundException e) { 
			throw new noEncontroArchivo();
		}
	}
	
}

