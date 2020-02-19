package com.todo1.hulkstore.logic.controller;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.todo1.hulkstore.data.delegate.CompraProductoDelegate;
import com.todo1.hulkstore.data.delegate.ProductosDelegate;
import com.todo1.hulkstore.data.delegate.ReporteProductosDelegate;
import com.todo1.hulkstore.data.delegate.VentaProductoDelegate;
import com.todo1.hulkstore.data.model.DetalleCompraProductoVO;
import com.todo1.hulkstore.data.model.DetalleVetaProductoVO;
import com.todo1.hulkstore.data.model.ProductoVO;
import com.todo1.hulkstore.data.model.ReporteProductosVO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
/**
 * The Class HulkStoreController.
 *
 * @author Cristhian Hernandez - QVision
 */
public class HulkStoreController {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(HulkStoreController.class);
	
	/** The Constant JDBC_STRGN. */
	private static final String JDBC_STRGN = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
	
	/** The Constant CNXN_STRGN. */
	private static final String CNXN_STRGN = "jdbc:sqlserver://localhost;databaseName=bdHulkStore;"; 

	/** The Constant USER_BD. */
	private static final String USER_BD = "user_HulkStore";
	
	/** The Constant PASS_BD. */
	private static final String PASS_BD = "user_HulkStore";
	
	private Connection connProvider = null;

	/**
	 * Consultar productos.
	 *
	 * @param 
	 * @return the list
	 */
	
	public List<ReporteProductosVO> reporteProductos(Integer cnsctvoPrdcto, Date fchaInco, Date fchaFin)  {
		try {
			ReporteProductosDelegate delegate = new ReporteProductosDelegate();
			delegate.setCnsctvoPrdcto(cnsctvoPrdcto);
			delegate.setFchaInco(fchaInco);
			delegate.setFchaFin(fchaFin); 
			conexionBD();
			delegate.ejecutarData(connProvider);
			return delegate.getlResultado();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}finally {
            try {
                if (connProvider != null && !connProvider.isClosed()) {
                	connProvider.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		return null;
	}
	
	/**
	 * crear productos.
	 *
	 * @param 
	 * 
	 */
	public void crearProductos(ProductoVO productoVO)  {
		try {
			ProductosDelegate delegate = new ProductosDelegate();
			delegate.setProductoVO(productoVO); 
			conexionBD();
			delegate.crearproducto(connProvider);		
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}finally {
            try {
                if (connProvider != null && !connProvider.isClosed()) {
                	connProvider.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	/**
	 * consultar productos.
	 *
	 * @param 
	 * @return the list
	 */
	public List<ProductoVO> consultarProductos()  {
		try {
			ProductosDelegate delegate = new ProductosDelegate();
			conexionBD();
			delegate.consultarproducto(connProvider);		
			return delegate.getlResultado();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}finally {
            try {
                if (connProvider != null && !connProvider.isClosed()) {
                	connProvider.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		return null;
	}
	
	/**
	 * comprar productos.
	 *
	 * @param 
	 * @return the list
	 */
	public void compraProductos(DetalleCompraProductoVO detalleCompraProductoVO)  {
		try {
			CompraProductoDelegate delegate = new CompraProductoDelegate();
			delegate.setDetalleCompraProductoVO(detalleCompraProductoVO);
			conexionBD();
			delegate.ejecutarData(connProvider);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}finally {
            try {
                if (connProvider != null && !connProvider.isClosed()) {
                	connProvider.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	/**
	 * venta productos.
	 *
	 * @param 
	 * @return the list
	 */
	public void ventaProductos(DetalleVetaProductoVO detalleVetaProductoVO)  {
		try {
			VentaProductoDelegate delegate = new VentaProductoDelegate();
			delegate.setDetalleVetaProductoVO(detalleVetaProductoVO);
			conexionBD();
			delegate.ejecutarData(connProvider);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}finally {
            try {
                if (connProvider != null && !connProvider.isClosed()) {
                	connProvider.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	
	/**
	 * Conexion.
	 *
	 */
	public void conexionBD()  {
		try {
	        try { 
	            Class.forName(JDBC_STRGN);
	            connProvider = DriverManager.getConnection(CNXN_STRGN,USER_BD,PASS_BD);
	            if (connProvider != null) {
	                DatabaseMetaData dm = (DatabaseMetaData) connProvider.getMetaData();
	                System.out.println("Driver name: " + dm.getDriverName());
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}


	public Connection getConnProvider() {
		return connProvider;
	}


	public void setConnProvider(Connection connProvider) {
		this.connProvider = connProvider;
	}
}