package Fabrica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Compra.Compra;
import Compra.Estado;
import Entidad.Clasificacion;
import Entidad.Empresa;
import Entidad.Entidad;
import Entidad.EntidadJuridica;
import Entidad.OrganizacionSectorSocial;
import MedioDePago.DineroEnCuenta;
import Moneda.CodigoMoneda;
import Organizacion.Organizacion;
import Presupuesto.Presupuesto;
import Direccion.Direccion;
import Direccion.Pais;
import Proveedor.Proveedor;
import Repositorios.RepositorioDeCategorias.RepositorioDeCategorias;
import Repositorios.RepositorioDeEntidades.RepositorioDeEntidades;
import Repositorios.RepositorioDeEtiquetas.RepositorioDeEtiquetas;
import Repositorios.RepositorioDeUsuarios.RepositorioDeUsuarios;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;
import Repositorios.RepositorioDeMonedas.RepositorioDeMonedasMeli;
import Usuario.Usuario;

public class Fabrica {
    public static Usuario usuarioStub() {
        return new Usuario("usuario", "Tp2020Dds");
    }
    
    public static EntidadJuridica entidadStub() {
    	return new OrganizacionSectorSocial("Entidad de Prueba", "Entidad Real", "1222222224", Fabrica.direccionStub(), 845, new ArrayList<>());
    }
    
    public static Organizacion organizacionStub() {
        Organizacion unaOrganizacion = new Organizacion(new RepositorioDeUsuarios(), new RepositorioDeCategorias(), new RepositorioDeEtiquetas(), new RepositorioDeEntidades());
        Usuario unUsuario = Fabrica.usuarioStub();
        unaOrganizacion.crearUsuario(unUsuario.getUsername(), unUsuario.getContrasenia());
        unaOrganizacion.agregarEntidad(Fabrica.entidadStub());
        return unaOrganizacion;
    }

    public static Direccion direccionStub(){
    	return new Direccion(new RepositorioDeLocacionesMeli(), "Jose Hernandez",
                2600, 7, "1415", Pais.AR);
    }
    
    public static Proveedor proveedorStub() {
        Direccion unaDireccion = Fabrica.direccionStub();
        return Proveedor.PersonaFisica(1, 1, "Juan", "Salvo", unaDireccion);
    }

    public static Compra compraConNPresupuestosMinimos(int cantidadMinimaDePresupuestos) {
        EntidadJuridica unaEntidad = new Empresa("Razon SRL", "Kwik-E-Mart", "4517", Fabrica.direccionStub(), 1, new ArrayList<>(), Clasificacion.PEQUENIA);

        LocalDate fechaActual = LocalDate.now();
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(Fabrica.usuarioStub());

        Compra unaCompra = new Compra(new RepositorioDeMonedasMeli(),
                new RepositorioDeEtiquetas(),
                unaEntidad,
                Fabrica.proveedorStub(),
                fechaActual,
                new DineroEnCuenta(),
                CodigoMoneda.ARS,
                cantidadMinimaDePresupuestos, usuarios);
        unaCompra.agregarItem(FabricaDeItems.bebida(4, 120));

        return unaCompra;
    }

    public static Presupuesto presupuestoPara(Compra unaCompra) {
        return new Presupuesto(unaCompra.getItems(), Fabrica.proveedorStub());
    }

    public static Compra compraEnEstado(Estado unEstado) {
        Compra unaCompra = Fabrica.compraConNPresupuestosMinimos(1);
        unaCompra.setIndicadorDeAprobacion(unEstado);
        return unaCompra;
    }

}