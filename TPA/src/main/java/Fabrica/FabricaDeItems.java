package Fabrica;

import java.math.BigDecimal;

import Compra.Item;

public class FabricaDeItems
{
	public static Item bebida(int cantidad, double valor){
		return new Item("Pecsi",10,BigDecimal.valueOf(valor));
	}
}
