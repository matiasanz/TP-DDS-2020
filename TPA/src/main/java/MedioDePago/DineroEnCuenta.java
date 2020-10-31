package MedioDePago;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dinero_en_cuenta")
@DiscriminatorValue("D")
public class DineroEnCuenta extends MedioDePago {
	private String titular;

	@Override
	public String getDescripcion() {
		return "Dinero en cuenta";
	}
}
