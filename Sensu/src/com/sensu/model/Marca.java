package com.sensu.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.PropertyValue;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Required;
import org.openxava.calculators.NextIntegerCalculator;
import org.openxava.model.Identifiable;

@Entity
public class Marca extends Identifiable {

	@DefaultValueCalculator(value = NextIntegerCalculator.class, 
		properties = {
			@PropertyValue(name = "model", value = "Marca"),
			@PropertyValue(name = "property", value = "codigo") 
		}
	)
	@Column(length = 10)
	@ReadOnly
	/**
	 * Codigo identificador. Autogenerado.
	 */
	private int codigo;

	@Column(length = 50)
	@Required
	private String nombre;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
