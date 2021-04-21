package ej8;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Receta implements Comparable<Receta>{
	private String nombreReceta;
	private int minutosDePreparacion;
	private HashSet<Ingrediente> ingredientes;
	private LinkedList<String> pasos;
	
	public Receta(String nombreReceta, int minutosPreparacion) throws RecetaException {
		this.nombreReceta = nombreReceta.toUpperCase();
		setMinutosDePreparacion(minutosPreparacion);
		ingredientes=new HashSet<Ingrediente>();
		pasos=new LinkedList<String>();
	}

	public int getMinutosDePreparacion() {
		return minutosDePreparacion;
	}

	public void setMinutosDePreparacion(int minutosDePreparacion) throws RecetaException {
		if (minutosDePreparacion <=0) {
			throw new RecetaException("Minutos incorrectos");
		}
		this.minutosDePreparacion = minutosDePreparacion;
	}

	public String getNombreReceta() {
		return nombreReceta;
	}
	
	public void annadirPasoAlFinal(String paso) {
		pasos.add(paso.toUpperCase());
	}

	public void annadirIngrediente(Ingrediente ingredienteNuevo) {
		int cantidadNueva=ingredienteNuevo.getCantidad();
		boolean annadido=false;

//      FORMA 1 DE RECORRER (MAS INEFICIENTE YA QUE RECORRE TODA LA COLECCION)		
//		for (Ingrediente o:ingredientes) {
//			if(o.getNombreIngrediente().equals(ingredienteNuevo.getNombreIngrediente())) {
//				o.setCantidad(cantidadNueva+o.getCantidad());
//			}
//			
//		}
//		ingredientes.add(ingredienteNuevo);
		
//      FORMA 2 DE RECORRER (MAS EFICIENTE YA QUE RECORRE LO QUE SEA NECESARIO)	
		Iterator<Ingrediente>it=ingredientes.iterator();
		
		while (it.hasNext()&& !annadido) {
			Ingrediente ingrediente = (Ingrediente) it.next();
			
			if(ingrediente.getNombreIngrediente().equals(ingredienteNuevo.getNombreIngrediente())) {
				ingrediente.setCantidad(cantidadNueva+ingrediente.getCantidad());
				annadido=true;
			}
		}
		
		ingredientes.add(ingredienteNuevo);
		
	}
	
	public boolean necesitaIngrediente(String nombreIngrediente) {
		boolean necesario=false;
		Ingrediente patron= new Ingrediente(nombreIngrediente);
		
		if(ingredientes.contains(patron)) {
			necesario=true;
		}
		
		return necesario;
	}
	//SI TENGO QUE BORRAR MUCHOS ELEMENTOS RECORRIENDO LA COLECCION DEBO USAR EL ITERADOR
	public void borrarIngrediente(Ingrediente ingredienteABorrar) throws RecetaException{
		boolean borrado=false;
		
		if(!ingredientes.remove(ingredienteABorrar)) {
			throw new RecetaException("Error, ingrediente no borrado ");
		}
	
		Iterator<String>it=pasos.iterator();
		
		while (it.hasNext() && !borrado) {
			if(pasos.contains(ingredienteABorrar.getNombreIngrediente())) {
				it.remove(); //BORRA EL ULTIMO QUE SE LE HA PASADO
				borrado=true;
			}
			
		}
	}
	
	public void annadirPasoDetrasDe(String pasoNuevo, String pasoExistente) throws RecetaException{
			int pos=pasos.indexOf(pasoExistente);
			
			if(pos==-1) {
				throw new RecetaException("Error, no se encuentra el paso existente");
			}
				// pos+1 YA QUE AL ENCONTRAR LA POSICION ANTIGUA DEBE DESPLAZARSE UNO++  
				pasos.add(pos+1, pasoNuevo);
		}	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreReceta == null) ? 0 : nombreReceta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receta other = (Receta) obj;
		if (nombreReceta == null) {
			if (other.nombreReceta != null)
				return false;
		} else if (!nombreReceta.equals(other.nombreReceta))
			return false;
		return true;
	}

	@Override
	public int compareTo(Receta o) {
		
		return this.nombreReceta.compareTo(o.getNombreReceta());
	}
	
	
	
}