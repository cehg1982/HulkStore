package com.todo1.hulkstore.logic.util;

import java.util.ResourceBundle;

public class ConsultaProperties {
	
	/** Constante BUNDLE_NAME. */
	private static final String BUNDLE_NAME = "properties.consultaPropiedadesService";//$NON-NLS-1$

	/** Constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * Instancia nueva de sql.
	 */
	private ConsultaProperties() {}

	/**
	 * Obtiene string.
	 * 
	 * @param key
	 *            the key
	 * @return string
	 */
	public static String getString(String key) {
		return RESOURCE_BUNDLE.getString(key);

	}
}