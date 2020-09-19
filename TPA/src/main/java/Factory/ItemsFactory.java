package Factory;

import Compra.Item;

import java.math.BigDecimal;

public class ItemsFactory {
	public static Item bebida(int cantidad, double valor){
		return new Item("Pecsi",10, BigDecimal.valueOf(valor));
	}
	
	public static Item itemValuadoEn(double precio){
		return new Item("Item 1", 1, BigDecimal.valueOf(precio));
	}
	public static Item itemNValuadoEn(int cantidad, double precio) {
		return new Item("Heladera", cantidad, BigDecimal.valueOf(precio));
	}
}
