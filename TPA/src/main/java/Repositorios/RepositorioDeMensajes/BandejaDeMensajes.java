package Repositorios.RepositorioDeMensajes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Usuario.NoEncontroArchivoException;


public class BandejaDeMensajes {
    private final File bandejaDeMensajes = new File("BandejaDeMensajes.txt");
    
    public BandejaDeMensajes() { }
    
    public void leerBandeja() {
    	try {
            Scanner scanner = new Scanner(bandejaDeMensajes);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //IMPRIMIR CADA LINEA
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new NoEncontroArchivoException();
        }
    }
    
    // PENDIENTE DE VER COMO IMPLEMENTARLO
}