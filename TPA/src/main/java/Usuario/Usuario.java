package Usuario;

import Repositorios.RepositorioDeCompras.RepositorioDeCompras;

public class Usuario {
    private Tipo tipo;
    private String username;
    private String contrasenia;

    public String getContrasenia() {
        return contrasenia;
    }

    public String getUsername() {
        return username;
    }

    public Usuario(String username, String contrasenia) {

        ValidadorUsuario validacion = new ValidadorUsuario();

        this.username = username;

        validacion.validarContrasenia(contrasenia, username);
        this.contrasenia = contrasenia;

    }

    public void cambiarContrasenia(String contrasenia) {

        ValidadorUsuario validacion = new ValidadorUsuario();

        validacion.validarContrasenia(contrasenia, username);
        this.contrasenia = contrasenia;

    }

    public boolean equals(Usuario otroUsuario){
        String username = otroUsuario.getUsername();
        String password = otroUsuario.getContrasenia();
        return this.autentica(username,password);
    }

    public boolean autentica(String username, String password){
        return this.username.equals(username) && this.contrasenia.equals(password);
    }

    public boolean esValidadorDeAlgunaCompra(){
    	RepositorioDeCompras compras = new RepositorioDeCompras();
    	
    	return compras.getComprasConPresupuestoElegido().stream().anyMatch(unaCompra -> unaCompra.puedeSerValidadaPor(this));
    }
    
    // FALTAN MÉTODOS DE ACCESO A LA BANDEJA DE MENSAJES
}
