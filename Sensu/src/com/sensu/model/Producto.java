package com.sensu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.validator.constraints.Length;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.model.Identifiable;

import com.sensu.calculadores.CalcularCodigo;
@Views({
	@View(
			members = 
			
				"Producto [descripcion, categoria];" +
				"Agregar Fotografias [imagenes]; " + 
				"observaciones"
		),

		@View(
				name = "producto_cotizacion",
				members = "codigo, descripcion"
			)
})

@Entity
public class Producto extends Identifiable {
	
	/**
	 * Constante para definir el valor inicial por el cual se va a generar el codigo
	 * de produto.
	 */
	private static final int INIT_VALUE = 1000;
	
	@Column(length = 4)
	@ReadOnly
	@Hidden
	private int codigo;

	@Column(length = 50)
	@Required
	private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@Required
	@DescriptionsList
	private Categoria categoria;

	@Stereotype("GALERIA_IMAGENES")
	@Column(length = 32)
	private String imagenes;

	@Length(max = 255)
	@Stereotype("MEMO")
	private String observaciones;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getImagenes() {
		return imagenes;
	}

	public void setImagenes(String imagenes) {
		this.imagenes = imagenes;
	}


	public int getCodigo() {
		return codigo;
	}

	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Metodo que permite calcular el siguiente codigo.
	 * @return
	 */
	@PrePersist
	public void calcularCodigo() {
		int siguienteCodigo = new CalcularCodigo(getClass().getSimpleName(), "codigo").calcular();
		setCodigo(siguienteCodigo < INIT_VALUE ? INIT_VALUE : siguienteCodigo);
	}
}
