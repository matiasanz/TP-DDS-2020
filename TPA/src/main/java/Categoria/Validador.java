package Categoria;

import Entidad.EntidadJuridica;

import java.math.BigDecimal;

public abstract class Validador {
    void validarGasto(BigDecimal montoCompra, BigDecimal montoAcumuladoEntidad){}
    void validarBloqueoDeAgregarEntidadesBase(){}
    void validarSiEntidadJuridicaEstaBloqueada(EntidadJuridica entidad){}
}
