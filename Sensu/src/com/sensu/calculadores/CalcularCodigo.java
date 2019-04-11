package com.sensu.calculadores;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.calculators.NextIntegerCalculator;
import org.openxava.jpa.XPersistence;
import org.openxava.util.XavaResources;

/**
 * clase que permite calcular un consecutivo de codigos.
 * 
 * @author JICZ
 *
 */
public class CalcularCodigo {

	private static Log log = LogFactory.getLog(NextIntegerCalculator.class);
	private static final String SELECT = "SELECT MAX( ";
	private static final String FROM = " ) + 1 FROM ";

	/**
	 * tabla, representa la entidad
	 */
	private String tabla;

	/**
	 * campo de la tabla que conserva la numeracion
	 */
	private String campo;

	/**
	 * constructor. default
	 * 
	 * @param tabla
	 * @param campo
	 */
	public CalcularCodigo(String tabla, String campo) {
		this.tabla = tabla;
		this.campo = campo;
	}

	public int calcular() {
		try {
			if (tabla == null || tabla.isEmpty())
				throw new Exception("Objetivo Tabla es requerido.");
			if (campo == null || tabla.isEmpty())
				throw new Exception("Objetivo Campo es requerido.");
			Query query = XPersistence.getManager().createQuery(createQuery());
			return (Integer) query.getSingleResult();
		} catch (Exception e) {
			log.error(XavaResources.getString("siguiente codigo error " + this.tabla), e);
			return -1;
		}
	}

	/**
	 * create query. MAX num.
	 * 
	 * @return
	 */
	private String createQuery() {
		StringBuffer sb = new StringBuffer();
		sb.append(SELECT);
		sb.append(this.campo);
		sb.append(FROM);
		sb.append(this.tabla);
		return sb.toString();
	}

}
