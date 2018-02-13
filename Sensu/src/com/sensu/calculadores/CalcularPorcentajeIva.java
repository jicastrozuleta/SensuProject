package com.sensu.calculadores;

import org.openxava.calculators.ICalculator;

import com.sensu.properties.IvaProperties;

public class CalcularPorcentajeIva implements ICalculator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5611803352582807184L;

	@Override
	public Object calculate() throws Exception {
		return IvaProperties.getIVA();
	}
}
