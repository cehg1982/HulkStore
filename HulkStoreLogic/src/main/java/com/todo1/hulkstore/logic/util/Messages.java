package com.todo1.hulkstore.logic.util;


import java.text.MessageFormat;
import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc

public class Messages {


	/** The Constant EXCEPCIONES. */
	public static final String EXCEPCIONES = "properties.excepciones";

	/** The Constant MENSAJES. */
	public static final String MENSAJES = "properties.mensajes";

	/** The Constant ETIQUETAS. */
	public static final String ETIQUETAS = "properties.etiquetas";
	
	/** The Constant ETIQUETAS. */
	public static final String CONFIG = "properties.config";
		
	/** The Constant Errores. */
	public static final String ERRORES = "properties.messageErrors";
	
	/** The Constant propiedades. */
	public static final String PARAMETROS = "properties.parametros";
	
	/**
	 * Gets the valor etiqueta.
	 * 
	 * @param clave
	 *            the clave
	 * @return the valor excepcion
	 */
	public static String getValorEtiquetas(String clave) {
		String mensaje = ResourceBundle.getBundle(ETIQUETAS).getString(clave);
		return mensaje;
	}

	
	/**
	 * Gets the valor CONFIGURACI�N.
	 * 
	 * @param clave
	 *            the clave
	 * @return the valor excepcion
	 */
	public static String getValorConfig(String clave) {
		String mensaje = ResourceBundle.getBundle(CONFIG).getString(clave);
		return mensaje;
	}

	/**
	 * Gets the valor excepcion.
	 * 
	 * @param clave
	 *            the clave
	 * @return the valor excepcion
	 */
	public static String getValorExcepcion(String clave) {
		String mensaje = ResourceBundle.getBundle(EXCEPCIONES).getString(clave);
		return mensaje;
	}
	
	/**
	 * Gets the valor excepcion, con paso de parametros
	 * 
	 * @param clave
	 *            the clave
	 * @return the valor mensaje
	 */
	public static String getValorExcepcion(String key, Object... params  ) {
    	String mensaje =  MessageFormat.format(ResourceBundle.getBundle(EXCEPCIONES).getString(key), params);
        return mensaje;
    }

	/**
	 * Gets the valor mensaje.
	 * 
	 * @param clave
	 *            the clave
	 * @return the valor mensaje
	 */
	public static String getValorMensaje(String clave) {
		String mensaje = ResourceBundle.getBundle(MENSAJES).getString(clave);
		return mensaje;
	}	
	
	/**
	 * Gets the valor excepcion, con paso de parametros
	 * 
	 * @param clave
	 *            the clave
	 * @return the valor mensaje
	 */
	public static String getValorMensaje(String key, Object... params  ) {
    	String mensaje =  MessageFormat.format(ResourceBundle.getBundle(MENSAJES).getString(key), params);
        return mensaje;
    }
	
	
	/**
	 * Obtiene el parametro solicitado
	 * @param clave
	 * @return
	 */
	public static String getValorError(String clave) {
		String parametro = ResourceBundle.getBundle(ERRORES).getString(clave);
		return parametro;
	}	
	
	/**
	 * Gets the valor CONFIGURACI�N.
	 * 
	 * @param clave
	 *            the clave
	 * @return the valor excepcion
	 */
	public static String getValorError(String key, Object... params) {
		String mensaje = MessageFormat.format(ResourceBundle.getBundle(ERRORES).getString(key), params);
		return mensaje;
	}	
	
	
	/**
	 * Obtiene el parametro solicitado
	 * @param clave
	 * @return
	 */
	public static String getValorParametro(String clave) {
		String parametro = ResourceBundle.getBundle(PARAMETROS).getString(clave);
		return parametro;
	}
	
	/**
	 * Gets the valor PARAMETROS.
	 * 
	 * @param clave
	 *            the clave
	 * @return the valor excepcion
	 */
	public static String getValorParametro(String key, Object... params) {
		String mensaje = MessageFormat.format(ResourceBundle.getBundle(PARAMETROS).getString(key), params);
		return mensaje;
	}
}