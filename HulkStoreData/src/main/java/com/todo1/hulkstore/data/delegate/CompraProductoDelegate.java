package com.todo1.hulkstore.data.delegate;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.todo1.hulkstore.data.model.DetalleCompraProductoVO;
import com.todo1.hulkstore.data.util.RecursosUtil;

/**
 * The Class CompraProductoDelegate.
 *
 * @author Cristhian Hernandez - QVision
 */
public class CompraProductoDelegate implements  Serializable {

	private static final long serialVersionUID = 4372540778425565199L;

	/** The Constant SP_COMPRAR_PRODUCTOS. */
	private static final String SP_COMPRAR_PRODUCTOS = "SP_COMPRAR_PRODUCTOS";
	

	private DetalleCompraProductoVO detalleCompraProductoVO;


	public void ejecutarData(Connection conn) throws SQLException  {
		String query =RecursosUtil.getStringStoredprocedures(SP_COMPRAR_PRODUCTOS);
		
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			Gson gson = new Gson();
			String prdctoJSON = gson.toJson(detalleCompraProductoVO);
			stmt.setString(1, prdctoJSON);			
			stmt.executeUpdate();
		}
	}


	public DetalleCompraProductoVO getDetalleCompraProductoVO() {
		return detalleCompraProductoVO;
	}


	public void setDetalleCompraProductoVO(DetalleCompraProductoVO detalleCompraProductoVO) {
		this.detalleCompraProductoVO = detalleCompraProductoVO;
	}

}
