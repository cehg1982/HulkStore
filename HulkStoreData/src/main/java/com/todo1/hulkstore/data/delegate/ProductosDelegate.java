package com.todo1.hulkstore.data.delegate;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.todo1.hulkstore.data.model.ProductoVO;
import com.todo1.hulkstore.data.model.ReporteProductosVO;
import com.todo1.hulkstore.data.util.RecursosUtil;

/**
 * The Class ProductosDelegate.
 *
 * @author Cristhian Hernandez - QVision
 */
public class ProductosDelegate implements  Serializable {

	private static final long serialVersionUID = 4372540778425565199L;

	/** The Constant SP_CREAR_PRODUCTOS. */
	private static final String SP_CREAR_PRODUCTOS = "SP_CREAR_PRODUCTOS";
	
	/** The Constant SP_CREAR_PRODUCTOS. */
	private static final String SP_CONSULTAR_PRODUCTOS = "SP_CONSULTAR_PRODUCTOS";
	
	private static final String CNSCTVO_PRDCTO = "cnsctvo_prdcto";

	private static final String NMBRE_PRDCTO = "nmbre_prdcto";

	private static final String DSCRPCON_PRDCTO = "dscrpcon_prdcto";
	

	private ProductoVO productoVO;
	/** The resultados. */
	private List<ProductoVO> lResultado;


	public void crearproducto(Connection conn) throws SQLException  {
		String query =RecursosUtil.getStringStoredprocedures(SP_CREAR_PRODUCTOS);
		
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			Gson gson = new Gson();
			String prdctoJSON = gson.toJson(productoVO);
			stmt.setString(1, prdctoJSON);
			
			stmt.executeUpdate();
		}
	}
	
	public void consultarproducto(Connection conn) throws SQLException  {
		String query =RecursosUtil.getStringStoredprocedures(SP_CONSULTAR_PRODUCTOS);
		lResultado=new ArrayList<ProductoVO>();
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					ProductoVO resultado = new ProductoVO();
					resultado.setCnsctvoPrdcto(rs.getInt(CNSCTVO_PRDCTO));
					resultado.setNmbrePrdcto(rs.getString(NMBRE_PRDCTO));
					resultado.setDscrpconPrdcto(rs.getString(DSCRPCON_PRDCTO));
					
					lResultado.add(resultado);
				}
			}
		}
	}


	public ProductoVO getProductoVO() {
		return productoVO;
	}


	public void setProductoVO(ProductoVO productoVO) {
		this.productoVO = productoVO;
	}

	public List<ProductoVO> getlResultado() {
		return lResultado;
	}

	public void setlResultado(List<ProductoVO> lResultado) {
		this.lResultado = lResultado;
	}

}
