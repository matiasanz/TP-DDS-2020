package Categoria;

import Entidad.EntidadJuridica;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.DiscriminatorType.STRING;

@Entity
@DiscriminatorColumn(name = "tipo", discriminatorType = STRING, length = 3)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "validadores_de_categoria")
public abstract class Validador {

    @Id
    @GeneratedValue
    private long id;

    void validarGasto(BigDecimal montoCompra, BigDecimal montoAcumuladoEntidad){}
    void validarBloqueoDeAgregarEntidadesBase(){}
    void validarSiEntidadJuridicaEstaBloqueada(EntidadJuridica entidad){}
}
