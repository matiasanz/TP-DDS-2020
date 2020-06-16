package Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Compra.Compra;

public class Usuario {
    private Tipo tipo;
    private String username;
    private String contrasenia;

    public Usuario(String username, String contrasenia) {

        this.username = username;

        validarContrasenia(contrasenia);
        this.contrasenia = contrasenia;

    }
    
    public void validarCompra(Compra unaCompra){
//    	unaCompra.validarUsuario(this); //TODO
    	unaCompra.imprimirDatos();
    	
		try{
			unaCompra.validar();
			unaCompra.aprobar();
			System.out.println("-----------<La Compra ha sido aprobada>-----------");
		} catch(RuntimeException unaExcepcion){
			unaCompra.rechazar();
			System.out.println("-----------<La Compra ha sido rechazada>----------\n [Motivo: "+unaExcepcion.getMessage() + "]");
		}
    }
    
    public boolean equals(Usuario otroUsuario){
    	String username = otroUsuario.getUsername();
    	String password = otroUsuario.getContrasenia();
    	return this.autentica(username,password);
    }
    
    public boolean autentica(String username, String password){
    	return this.username.equals(username) && this.contrasenia.equals(password);
    }
    
    public String getContrasenia() {
        return contrasenia;
    }
    
    public String getUsername() {
    	return username;
    }

    public void cambiarContrasenia(String contrasenia) {
    	
        validarContrasenia(contrasenia);
        this.contrasenia = contrasenia;
        
    }

    void validarContrasenia(String contrasenia) {
    	
        validarContraseniaEntreLasDiezMilMasConocidas(contrasenia);
        validarLongitudMinima(contrasenia);
        validarCaracteresRepetidos(contrasenia);
		validarUsuarioSeaDistintoALaContrasenia(contrasenia);
		
    }

    private boolean evaluarRegex(String regEx, String contrasenia) {

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(contrasenia);

        return matcher.find();
        
    }
    
    private void validarUsuarioSeaDistintoALaContrasenia(String contrasenia) {
    	
        if (contrasenia.toLowerCase().contains(this.username.toLowerCase())){
            throw new ContraseniaTieneNombreDeUsuarioIncluidoException();
        }
        
    }

    private void validarCaracteresRepetidos(String contrasenia) {

        // Lanza excepcion si tiene al menos 2 caracteres repetidos
        if(!evaluarRegex("^(?!.*(.)\\1)", contrasenia))
        {
            throw new ContraseniaTieneCaracteresRepetidosException();
        }

    }

    private void validarLongitudMinima(String contrasenia) {

        int LONGITUD_MINIMA_NIST = 8;

        if (contrasenia.length() < LONGITUD_MINIMA_NIST)
            throw new ContraseniaNoCumpleLongitudMinimaException(LONGITUD_MINIMA_NIST);


    }
    

    void validarContraseniaEntreLasDiezMilMasConocidas(String contrasenia) {

        File file = new File("10000WorstPasswords.txt");
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals(contrasenia)) {
                    throw new ContraseniaEntreLasDiezMilException();
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new NoEncontroArchivoExceptionException();
        }
    }
}

