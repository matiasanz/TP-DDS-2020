package MedioDePago;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "medios_de_pagos")
public abstract class MedioDePago {

    public abstract String getDescripcion();

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }
}
