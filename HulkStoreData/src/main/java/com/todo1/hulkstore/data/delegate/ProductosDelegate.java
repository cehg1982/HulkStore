package com.todo1.hulkstore.data.delegate;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	/** The Constant SP_REPORTE_PRODUCTOS. */
	private static final String SP_CREAR_PRODUCTOS = "SP_CREAR_PRODUCTOS";
	

	private ProductoVO productoVO;


	public void ejecutarData(Connection conn) throws SQLException  {
		String query =RecursosUtil.getStringStoredprocedures(SP_CREAR_PRODUCTOS);
		
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			Gson gson = new Gson();
			String prdctoJSON = gson.toJson(productoVO);
			stmt.setString(1, prdctoJSON);
			
			stmt.executeQuery();
		}
	}


	public ProductoVO getProductoVO() {
		return productoVO;
	}


	public void setProductoVO(ProductoVO productoVO) {
		this.productoVO = productoVO;
	}

}
