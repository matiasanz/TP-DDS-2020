import java.util.List;
import Compra.Compra;
import Compra.Estado;
import Organizacion.Organizacion;
import Presupuesto.Presupuesto;
import Usuario.Usuario;

public class MainClass {
		
	public static void main(String[] args) {
    		
    	Organizacion unaOrganizacion = Fabrica.organizacionStub();
 
//      GENERAR COMPRAS
  	Compra compraQueAprueba = Fabrica.compraConNPresupuestosMinimos(1);
  	Compra compraQueDesaprueba = Fabrica.compraConNPresupuestosMinimos(76);
  	Compra compraNoValidable = Fabrica.compraEnEstado(Estado.NUEVA);
  	
  	unaOrganizacion.agregarCompra(compraQueAprueba);
  	unaOrganizacion.agregarCompra(compraQueDesaprueba);
  	unaOrganizacion.agregarCompra(compraNoValidable);
    	
//        AGREGAR PRESUPUESTOS
    Presupuesto unPresupuesto = Fabrica.presupuestoPara(compraQueAprueba);
    compraQueAprueba.generarPresupuesto(unPresupuesto);
    	
//        DEJAR COMPRAS CON PRESUPUESTOS SUFICIENTES Y OTRAS NO
//    	unaOrganizacion.validarPresupuestos(); 
    //Duda: La idea no es que despues tire la excepcion?
//
//        ELEGIR PRESUPUESTOS
        compraQueAprueba.elegirPresupuesto(unPresupuesto);
        compraQueDesaprueba.elegirPresupuesto(unPresupuesto);

//        PEDIRLE A USUARIO USER Y CONTRASEnia y VALIDAR EL LOGEO
        Usuario miUsuario = unaOrganizacion.ingresarUsuario();    	
        
//        FILTER DE COMPRAS A LAS QUE MI USUARIO PUEDE VALIDAR
        List<Compra> comprasQuePuedoValidar = unaOrganizacion.comprasQuePuedeValidar(miUsuario);
        
//        IMPRIMIR POR PANTALLA QUE SE COMENZARa A VALIDAR COMPRAS Y CUALES
        System.out.println("\n***************************** COMPRAS A VALIDAR *****************************\n");
        comprasQuePuedoValidar.stream().forEach(Compra::imprimirDatos);
        
        System.out.println("\n***************************** VALIDACIONES SOBRE LA MARCHA *****************************\n");
//        FALTA DEFINIR EL TOSTRING EN COMPRA
        comprasQuePuedoValidar.stream().forEach(compra->miUsuario.validarCompra(compra));
        
        System.out.println("\n***************************** COMPRAS APROBADAS *****************************\n");
        unaOrganizacion.getComprasAprobadas().stream().forEach(Compra::imprimirDatos);
	}
}