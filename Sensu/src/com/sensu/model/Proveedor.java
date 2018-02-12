package com.sensu.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.openxava.annotations.ListProperties;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.View;
import org.openxava.model.Identifiable;

@View(
		members = 
			"Proveedor [nit; razonSocial]; " + 
			"Contacto [celular,fijo; direccion]; " +
			"cotizaciones"
)
@Entity
public class Proveedor extends Identifiable {

	@Column(length = 20)
	@Required
	/**
	 * nit del proveedor
	 */
	private String nit;

	@Column(length = 50)
	@Required
	/**
	 * razon del proveedor
	 */
	private String razonSocial;

	@Column(length = 50)
	@Required
	/**
	 * persona encargada
	 */
	private String direccion;

	@Column(length = 15)
	@Required
	/**
	 * celular contacto
	 */
	private String celular;

	@Column(length = 15)
	@Required
	/**
	 * numero fijo de contacto.
	 */
	private String fijo;

	@OneToMany(mappedBy = "proveedor")
	@ElementCollection
	@ListProperties("fecha, solicitud")
	/**
	 * cotizaciones recibidas para la solicitud de cotizacion actual.
	 */
	private Collection<Cotizacion> cotizaciones;

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFijo() {
		return fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public Collection<Cotizacion> getCotizaciones() {
		return cotizaciones;
	}

	public void setCotizaciones(Collection<Cotizacion> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}
}
