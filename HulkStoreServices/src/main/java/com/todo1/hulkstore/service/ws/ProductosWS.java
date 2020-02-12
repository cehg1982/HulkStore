package com.todo1.hulkstore.service.ws;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.todo1.hulkstore.data.model.DetalleCompraProductoVO;
import com.todo1.hulkstore.data.model.DetalleVetaProductoVO;
import com.todo1.hulkstore.data.model.ProductoVO;
import com.todo1.hulkstore.data.model.ReporteProductosVO;
import com.todo1.hulkstore.logic.controller.HulkStoreController;
import com.todo1.hulkstore.service.util.ConstantesWeb;
import com.todo1.hulkstore.service.util.CustomErrorRequest;

/**
 * 
 *
 * @author cristhian Hernandez- QVision
 */
@Path(ConstantesWeb.PRODUCTOS)
public class ProductosWS extends AbstractWS implements Serializable{
	
	/** The Constant JSON_RECIBIDO. */
	private static final String JSON_RECIBIDO = "###Json recibido: %s";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8580940310933808342L;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ProductosWS.class);

	/**
	 * consultar Productos.
	 *
	 * @param securityContext the security context
	 * @return the response
	 */
	@GET
	@Path(ConstantesWeb.SLASH_REPORTE_PRODUCTOS)
	@Produces(ConstantesWeb.APPLICATION_JSON_UTF_8)
	public Response reporteProductos(@PathParam("cnsctvoPrdcto") Integer cnsctvoPrdcto, @PathParam("fchaInco") Date fchaInco, @PathParam("fchaFin") Date fchaFin ) {
		HulkStoreController controller = new HulkStoreController();
		List<ReporteProductosVO> lResultado= new ArrayList<ReporteProductosVO>();
		try {
			lResultado = controller.reporteProductos(cnsctvoPrdcto, fchaInco, fchaFin);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return Response.status(new CustomErrorRequest(ConstantesWeb.CODE_API_RESPONSE_INT_500, ConstantesWeb.API_RESPONSE_500))
					.entity(ConstantesWeb.API_RESPONSE_MESSAGE)
					.build();
		}
		return Response.status(200).entity(lResultado).build();
	}
	
	/**
	 * crear Productos.
	 *
	 * @param securityContext the security context
	 * @return the response
	 */
	@POST
	@Path(ConstantesWeb.SLASH_CREAR_PRODUCTOS)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(ConstantesWeb.APPLICATION_JSON_UTF_8)
	public Response crearProductos(ProductoVO productoVO) {
		HulkStoreController controller = new HulkStoreController();
		try {
			controller.crearProductos(productoVO);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return Response.status(new CustomErrorRequest(ConstantesWeb.CODE_API_RESPONSE_INT_500, ConstantesWeb.API_RESPONSE_500))
					.entity(ConstantesWeb.API_RESPONSE_MESSAGE)
					.build();
		}
	 return Response.status(204).build();
	}
	
	
	/**
	 * comprar Productos.
	 *
	 * @param securityContext the security context
	 * @return the response
	 */
	@POST
	@Path(ConstantesWeb.SLASH_COMPRA_PRODUCTOS)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(ConstantesWeb.APPLICATION_JSON_UTF_8)
	public Response crearProductos(DetalleCompraProductoVO detalleCompraProductoVO) {
		HulkStoreController controller = new HulkStoreController();
		try {
			controller.compraProductos(detalleCompraProductoVO);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return Response.status(new CustomErrorRequest(ConstantesWeb.CODE_API_RESPONSE_INT_500, ConstantesWeb.API_RESPONSE_500))
					.entity(ConstantesWeb.API_RESPONSE_MESSAGE)
					.build();
		}
	 return Response.status(204).build();
	}
	
	/**
	 * venta Productos.
	 *
	 * @param securityContext the security context
	 * @return the response
	 */
	@POST
	@Path(ConstantesWeb.SLASH_VENTA_PRODUCTOS)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(ConstantesWeb.APPLICATION_JSON_UTF_8)
	public Response ventaProductos(DetalleVetaProductoVO detalleVetaProductoVO) {
		HulkStoreController controller = new HulkStoreController();
		try {
			controller.ventaProductos(detalleVetaProductoVO);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return Response.status(new CustomErrorRequest(ConstantesWeb.CODE_API_RESPONSE_INT_500, ConstantesWeb.API_RESPONSE_500))
					.entity(ConstantesWeb.API_RESPONSE_MESSAGE)
					.build();
		}
	 return Response.status(204).build();
	}
	
}
