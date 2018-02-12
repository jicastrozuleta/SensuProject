package com.sensu.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.openxava.annotations.Depends;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.Stereotype;

@Embeddable
public class DetalleCotizacion {

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@ReferenceView("producto_cotizacion")
	private Producto producto;

	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList
	private Marca marca;

	private int cantidad;

	@Stereotype("DINERO")
	private BigDecimal precioUnitario;
	
	private int IVA;

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario == null ? BigDecimal.ZERO : this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * permite calcular el total de la linea actual de detalle.
	 * 
	 * @return
	 */
	@Depends("producto.codigo, precioUnitario, cantidad")
	@Stereotype("DINERO")
	public BigDecimal getTotal() {
		return new BigDecimal(getCantidad()).multiply(getPrecioUnitario());
	}
}
