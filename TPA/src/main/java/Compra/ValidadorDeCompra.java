package Compra;

import Presupuesto.Presupuesto;

import java.util.Comparator;

public class ValidadorDeCompra {

    public void validar(Compra unaCompra){
        validarSuficientesPresupuestos(unaCompra);
        validarCompraEnBaseDePresupuesto(unaCompra);
        validarSeleccionDeProveedorMinimoPrecio(unaCompra);
    }

    private void validarSuficientesPresupuestos(Compra unaCompra){
        if (unaCompra.getCantidadMinimaDePresupuestos() > unaCompra.getPresupuestosAsociados().size()){
            throw new NoHayPresupuestosSuficientesException();
        }
    }

    private void validarCompraEnBaseDePresupuesto(Compra unaCompra){
        if (!(unaCompra.getPresupuestosAsociados().contains(unaCompra.getPresupuestoElegido()))){
            throw new PresupuestoElegidoNoSeEncuentraEntreLosPresupuestosException();
        }
    }

    private void validarSeleccionDeProveedorMinimoPrecio(Compra unaCompra){
        Presupuesto presupuestoElegido = unaCompra.getPresupuestosAsociados().stream().min(Comparator.naturalOrder()).orElseThrow(NoHayPresupuestosException::new);
        if (unaCompra.getPresupuestoElegido() != presupuestoElegido){
            throw new PresupuestoElegidoNoCumpleCriterioException();
        }
    }
}
