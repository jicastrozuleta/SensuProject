package com.sensu.properties;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.util.PropertiesReader;
import org.openxava.util.XavaResources;

public class IvaProperties {
	/**
	 * nombre del archivo de propiedades.
	 */
	private final static String ARCHIVO_PROPIEDADES = "iva.properties";

	/**
	 * Propiedad solicitada del archivo.
	 */
	private static final String PROPIEDAD = "iva_default";

	/**
	 * generado de logs
	 */
	private static Log log = LogFactory.getLog(IvaProperties.class);

	private static Properties propiedades; // Almacenamos las propiedades aquí

	/**
	 * obtener el valor del iva por defecto.
	 * 
	 * @return
	 */
	public static BigDecimal getIVA() {
		return new BigDecimal(getPropiedades().getProperty(PROPIEDAD));
	}

	/**
	 * obtener el archivo de propiedades.
	 * 
	 * @return
	 */
	private static Properties getPropiedades() {
		if (propiedades == null) {
			PropertiesReader reader = new PropertiesReader(IvaProperties.class, ARCHIVO_PROPIEDADES);
			try {
				propiedades = reader.get();
			} catch (IOException ex) {
				log.error(XavaResources.getString("properties_file_error", ARCHIVO_PROPIEDADES), ex);
				propiedades = new Properties();
			}
		}
		return propiedades;
	}
}
