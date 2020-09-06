package Repositorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Usuario.NoEncontroArchivoException;
import Usuario.Usuario;

public class RepositorioDeMensajes
{
	public List<String> getMensajes(Usuario usuario){
		
		List<String> mensajes = new LinkedList<>();
		
		File file = new File(this.getRepoPath(usuario));
		
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                mensajes.add(scanner.nextLine());
            }
            
            scanner.close();
        } 
        
        catch (FileNotFoundException e) {
            throw new NoEncontroArchivoException();
        }

        return mensajes;
	}
	
	public void logMensaje(Usuario usuario, String mensaje){
		FileWriter fw;
		try{
			fw=new FileWriter(this.getRepoPath(usuario));
			fw.write(mensaje + "");
			fw.close();
		}
		catch(Exception e1){
			throw new NoEncontroArchivoException();
		}
	}
	
	private String getRepoPath(Usuario usuario){
		return "bandeja_" + usuario.getUsername() + ".txt";
	}
}
