package com.sensu.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;
import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.View;
import org.openxava.calculators.CurrentDateCalculator;
import org.openxava.model.Identifiable;

@View(
	members = 
		"Cotizacion [fecha, solicitud, proveedor]; " + 
		"detalles; observaciones"
)
@Entity
public class Cotizacion extends Identifiable {

	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	/**
	 * fecha de creacion de la cotizacion.
	 */
	private Date fecha;

	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList (
			descriptionProperties = "numero", 
			order  = "numero"
	)
	
	/**
	 * relacion con solicitud. identifica a que solicitud pertenece la cotizacion
	 * actual.
	 */
	private Solicitud solicitud;

	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList (
		descriptionProperties = "razonSocial", 
		orderByKey   = true
	)
	/**
	 * relacion con proveedor. identifica el proveedor que envio la cotizacion.
	 */
	private Proveedor proveedor;
	
	
	@ElementCollection
	@ListProperties("producto.codigo, producto.descripcion, marca,cantidad, precioUnitario, base, IVA,"
			+ "total[cotizacion.importeBase, cotizacion.totalIva, cotizacion.totalCotizacion]")
	private Collection<DetalleCotizacion> detalles;
	
	@Length(max = 255)
	@Stereotype("MEMO")
	private String observaciones;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Collection<DetalleCotizacion> getDetalles() {
		return detalles;
	}

	public void setDetalles(Collection<DetalleCotizacion> detalles) {
		this.detalles = detalles;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	/**
	 * calcular el subtotal de la cotizacion
	 * @return
	 */
	public BigDecimal getImporteBase() {
		BigDecimal resultado = new BigDecimal("0.00");
		for (DetalleCotizacion detalle : getDetalles()) {
			// Iteramos por todas las líneas de detalle
			resultado = resultado.add(detalle.getBase());
			// Acumulamos el importe
		}
		return resultado;
	}
	
	/**
	 * calcular el valor del iva de la cotizacion
	 * @return
	 */
	public BigDecimal getTotalIva() {
		BigDecimal resultado = new BigDecimal("0.00");
		for (DetalleCotizacion detalle : getDetalles()) {
			// Iteramos por todas las líneas de detalle
			resultado = resultado.add(detalle.getBase().multiply(detalle.getIVA().divide(new BigDecimal(100))));
			// Acumulamos el importe
		}
		return resultado;
	}
	
	/**
	 * calcular el total de la cotizacion
	 * @return
	 */
	public BigDecimal getTotalCotizacion() {
		return getImporteBase().add(getTotalIva());
	}
}
