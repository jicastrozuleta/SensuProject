package com.sensu.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.PropertyValue;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.View;
import org.openxava.calculators.CurrentDateCalculator;
import org.openxava.calculators.NextIntegerCalculator;
import org.openxava.model.Identifiable;

@View(
		members = 
			"Solicitud [numero, fecha; solicita]; " + 
			"observaciones;cotizaciones"
)
@Entity
public class Solicitud extends Identifiable {

	@DefaultValueCalculator(value = NextIntegerCalculator.class, properties = {
			@PropertyValue(name = "model", value = "Solicitud"), @PropertyValue(name = "property", value = "numero") })
	@Column(length = 10)
	@ReadOnly
	/**
	 * numero consecutivo de solicitudes. Autogenerado.
	 */
	private int numero;

	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	/**
	 * fecha de creacion de la solicitud.
	 */
	private Date fecha;

	@Column(length = 50)
	@Required
	/**
	 * persona que solicita las cotizaciones.
	 */
	private String solicita;

	@Length(max = 255)
	@Stereotype("TEXTO_GRANDE")
	private String observaciones;

	@OneToMany(mappedBy = "solicitud")
	@ElementCollection
	@ListProperties("fecha, proveedor.nit, proveedor.razonSocial, totalCotizacion")
	/**
	 * cotizaciones recibidas para la solicitud actual.
	 */
	private Collection<Cotizacion> cotizaciones;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getSolicita() {
		return solicita;
	}

	public void setSolicita(String solicita) {
		this.solicita = solicita;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Collection<Cotizacion> getCotizaciones() {
		return cotizaciones;
	}

	public void setCotizaciones(Collection<Cotizacion> cotizaciones) {
		this.cotizaciones = cotizaciones;
	}

}
