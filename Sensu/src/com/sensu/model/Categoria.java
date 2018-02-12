package com.sensu.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.PropertyValue;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.View;
import org.openxava.calculators.NextIntegerCalculator;
import org.openxava.model.Identifiable;

/**
 * permite agrupar productos por categoria.
 * 
 * @author JICZ
 *
 */
@View(
		name = "categoria_busqueda" ,
		members = "descripcion;"
)
@Entity
public class Categoria extends Identifiable {

	@DefaultValueCalculator(value = NextIntegerCalculator.class, 
		properties = {
			@PropertyValue(name = "model", value = "Categoria"), 
			@PropertyValue(name = "property", value = "codigo") 
		}
	)
	@Column(length = 10)
	@ReadOnly
	/**
	 * Codigo identificador. Autogenerado.
	 */
	private int codigo;

	@Column
	private String descripcion;
	
	@OneToMany(mappedBy = "categoria")
	@ListProperties("descripcion, observaciones")
	private Collection<Producto> productos;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Collection<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Collection<Producto> productos) {
		this.productos = productos;
	}

}
