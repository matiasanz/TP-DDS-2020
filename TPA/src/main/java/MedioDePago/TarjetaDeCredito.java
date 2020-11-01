package MedioDePago;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tarjetas_de_credito")
@DiscriminatorValue("T")
public class TarjetaDeCredito extends MedioDePago {
	private String titular;
	private String numero; //12 caracteres
	private String origen;

	public TarjetaDeCredito() {
	}

	public TarjetaDeCredito(String titular, String numero, String origen) {
		this.titular = titular;
		this.numero = numero;
		this.origen = origen;
	}

	private String safeGetNumero() {
		return "XXXX-XXXX-XXXX-" + this.numero.substring(7, 11);
	}

	@Override
	public String getDescripcion() {
		return "Tarjeta de Credito "+ this.origen + " " + this.safeGetNumero();
	}
}
