package Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exceptions.ContraseniaEntreLasDiezMilException;
import Exceptions.ContraseniaNoCumpleLongitudMinimaException;
import Exceptions.ContraseniaTieneCaracteresRepetidosException;
import Exceptions.ContraseniaTieneNombreDeUsuarioIncluidoException;
import Exceptions.NoEncontroArchivoException;

public class ValidadorUsuario {

    void validarContrasenia(String contrasenia,String username) {

        validarContraseniaEntreLasDiezMilMasConocidas(contrasenia);
        validarContraseniaConLongitudMinima(contrasenia);
        validarCaracteresRepetidos(contrasenia);
        validarUsuarioNoContenidoEnContrasenia(username, contrasenia);

    }

    public void validarUsuarioNoContenidoEnContrasenia(String username, String contrasenia) {

        if (contrasenia.toLowerCase().contains(username.toLowerCase())){
            throw new ContraseniaTieneNombreDeUsuarioIncluidoException();
        }

    }

    public void validarCaracteresRepetidos(String contrasenia) {

        // Lanza excepcion si tiene al menos 2 caracteres repetidos
        if(!evaluarRegex("^(?!.*(.)\\1)", contrasenia))
        {
            throw new ContraseniaTieneCaracteresRepetidosException();
        }

    }

    public void validarContraseniaConLongitudMinima(String contrasenia) {

        int LONGITUD_MINIMA_NIST = 8;

        if (contrasenia.length() < LONGITUD_MINIMA_NIST)
            throw new ContraseniaNoCumpleLongitudMinimaException(LONGITUD_MINIMA_NIST);


    }

    public void validarContraseniaEntreLasDiezMilMasConocidas(String contrasenia) {

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
            throw new NoEncontroArchivoException();
        }
    }
    
    private boolean evaluarRegex(String regEx, String contrasenia) {

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(contrasenia);

        return matcher.find();

    }
}