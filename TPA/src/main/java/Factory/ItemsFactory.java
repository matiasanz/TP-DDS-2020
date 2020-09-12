package Factory;

import Categoria.Categoria;
import Compra.Item;

import java.math.BigDecimal;

public class ItemsFactory {
	public static Item bebida(int cantidad, double valor){
		return new Item("Pecsi",10, BigDecimal.valueOf(valor));
	}
}
