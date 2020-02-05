package com.todo1.hulkstore.service.ws;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;
import com.todo1.hulkstore.service.util.ConstantesWeb;
import com.todo1.hulkstore.service.util.CustomErrorRequest;

/**
 * The Class AbstractWS.
 *
 * @author Cristhian Hernandez - QVision
 */
public class AbstractWS implements Serializable{
	
	/** The Constant PARAMETRO_OBLIGATORIO. */
	private static final String PARAMETRO_OBLIGATORIO = "El parámetro '%s' es obligatorio";
	
	/** The Constant PARAMETROS_REQUERIDOS. */
	private static final String PARAMETROS_REQUERIDOS = "No se recibieron los parametros requeridos";
	
	public static final String FORMATO_FECHA = "dd/MM/yyyy";
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5099361110409752769L;
	
	private static final Logger LOGGER = Logger.getLogger(AbstractWS.class);
	
	public static final Gson GSON = new GsonBuilder().setDateFormat(FORMATO_FECHA).create();

	protected void  setStatusCode(HttpServletResponse response, int statusCode){
		response.setStatus(statusCode);
	}
	
	protected void escribirObjeto(HttpServletResponse response, JsonElement objeto, Integer statusCode) {
		try {
			this.setStatusCode(response, statusCode);
			JsonWriter writer = new JsonWriter(response.getWriter());
			GSON.toJson(objeto, writer);
			writer.flush();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
