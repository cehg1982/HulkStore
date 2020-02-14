package com.todo1.hulkstore.data.delegate;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.todo1.hulkstore.data.model.DetalleVetaProductoVO;
import com.todo1.hulkstore.data.util.RecursosUtil;

/**
 * The Class VentaProductoDelegate.
 *
 * @author Cristhian Hernandez - QVision
 */
public class VentaProductoDelegate implements  Serializable {

	private static final long serialVersionUID = 4372540778425565199L;

	/** The Constant SP_VENTAS_PRODUCTOS. */
	private static final String SP_VENTAS_PRODUCTOS = "SP_VENTAS_PRODUCTOS";
	

	private DetalleVetaProductoVO detalleVetaProductoVO;


	public void ejecutarData(Connection conn) throws SQLException  {
		String query =RecursosUtil.getStringStoredprocedures(SP_VENTAS_PRODUCTOS);
		
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			Gson gson = new Gson();
			String prdctoJSON = gson.toJson(detalleVetaProductoVO);
			stmt.setString(1, prdctoJSON);			
			stmt.executeUpdate();
		}
	}


	public DetalleVetaProductoVO getDetalleVetaProductoVO() {
		return detalleVetaProductoVO;
	}


	public void setDetalleVetaProductoVO(DetalleVetaProductoVO detalleVetaProductoVO) {
		this.detalleVetaProductoVO = detalleVetaProductoVO;
	}

}
