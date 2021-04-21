package ej8;

public class MainReceta {

	public static void main(String[] args) {
		try {
			Receta salmorejo=new Receta("Salmorejo", 40);
			Ingrediente pimiento=new Ingrediente("PIMIENTO", 100);
			
			salmorejo.annadirIngrediente(pimiento);
			Recetario recetario=new Recetario();
			recetario.annadirReceta(salmorejo);
			
			
		} catch (RecetaException e) {
			System.out.println(e.getMessage());
		}

	}

}
