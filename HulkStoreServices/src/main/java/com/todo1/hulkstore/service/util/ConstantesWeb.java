package com.todo1.hulkstore.service.util;

import javax.ws.rs.core.MediaType;

public class ConstantesWeb {

	public static final String APPLICATION_JSON_UTF_8 = MediaType.APPLICATION_JSON + ";charset=utf-8";
	public static final String SLASH = "/";
	public static final String PRODUCTOS = SLASH + "ProductosWS";
	
	public static final String SLASH_REPORTE_PRODUCTOS = "/ReporteProductos/{cnsctvoPrdcto}/{fchaInco}/{fchaFin}";
	public static final String SLASH_CREAR_PRODUCTOS = "/CrearProductos";
	public static final String SLASH_COMPRA_PRODUCTOS = "/CompraProductos";
	public static final String SLASH_VENTA_PRODUCTOS = "/VentaProductos";
	
	/** The Constant API_RESPONSE_403. */
	public static final String CODE_API_RESPONSE_403 = "403";
	public static final int CODE_API_RESPONSE_INT_403 = 403;
	public static final String API_RESPONSE_403 = "Usuario no autenticado/autorizado";

	/** The Constant API_RESPONSE_404. */
	public static final String CODE_API_RESPONSE_404 = "404";
	public static final int CODE_API_RESPONSE_INT_404 = 404;
	public static final String API_RESPONSE_404 = "Registros no encontrados / procesados";
	
	/** The Constant API_RESPONSE_422. */
	public static final String CODE_API_RESPONSE_422 = "422";
	public static final int CODE_API_RESPONSE_INT_422 = 422;
	public static final String API_RESPONSE_422 = "Errores de validacion";
	
	public static final String CODE_API_RESPONSE_500 = "500";
	public static final int CODE_API_RESPONSE_INT_500 = 500;
	public static final String API_RESPONSE_500 = "Error interno del sistema";
	public static final String API_RESPONSE_MESSAGE = "La operación no pudo ser ejecutada, intente de nuevo y de continuar recibiendo este mensaje comuniquelo enviando la descripcion detallada de los parametros de entrada y servicio ejecutado";
	
	public static final String LA_CONSULTA_NO_ARROJA_RESULTADOS = "La consulta no arroja resultados";

	private ConstantesWeb() {
		super();
	}
	
}
