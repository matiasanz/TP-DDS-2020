package Factory;

import Categoria.Categoria;

public class CategoriasFactory
{
	public static Categoria ong(){
		return new Categoria("ONG");
	}
}
