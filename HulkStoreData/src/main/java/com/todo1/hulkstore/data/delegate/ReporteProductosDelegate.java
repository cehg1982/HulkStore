package com.todo1.hulkstore.data.delegate;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.todo1.hulkstore.data.model.ReporteProductosVO;
import com.todo1.hulkstore.data.util.RecursosUtil;

/**
 * The Class ProductosDelegate.
 *
 * @author Cristhian Hernandez - QVision
 */
public class ReporteProductosDelegate implements  Serializable {

	private static final long serialVersionUID = 4372540778425565199L;
	
	private static final String CNSCTVO_PRDCTO = "cnsctvo_prdcto";

	private static final String NMBRE_PRDCTO = "nmbre_prdcto";

	private static final String DSCRPCON_PRDCTO = "dscrpcon_prdcto";

	private static final String CNTDD_STCK = "cntdd_stck";

	private static final String CNTDD_CMPRA = "cntdd_cmpra";

	private static final String CNTDD_VNTA = "cntdd_vnta";

	private static final String PRCIO_CMPRA = "prcio_cmpra";

	private static final String PRCIO_UNTRO = "prcio_untro";

	private static final String TOTAL_CMPRA = "total_cmpra";

	private static final String TOTAL_VNTA = "total_vnta";

	private static final String UTLDD = "utldd";

	/** The Constant SP_REPORTE_PRODUCTOS. */
	private static final String SP_REPORTE_PRODUCTOS = "SP_REPORTE_PRODUCTOS";

	private Integer cnsctvoPrdcto;

	private Date fchaInco;
	
	private Date fchaFin;
	
	/** The resultados. */
	private List<ReporteProductosVO> lResultado;


	public void ejecutarData(Connection conn) throws SQLException  {
		String query =RecursosUtil.getStringStoredprocedures(SP_REPORTE_PRODUCTOS);
		lResultado=new ArrayList<ReporteProductosVO>();
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			
			if( cnsctvoPrdcto  == null ){
		    	stmt.setNull(1, Types.INTEGER);
		    }else{
		    	stmt.setInt(1,cnsctvoPrdcto);        	
		    }
			
			stmt.setDate(2, fchaInco);
			stmt.setDate(3, fchaFin);
		
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					ReporteProductosVO resultado = new ReporteProductosVO();
					resultado.setCnsctvoPrdcto(rs.getInt(CNSCTVO_PRDCTO));
					resultado.setNmbrePrdcto(rs.getString(NMBRE_PRDCTO));
					resultado.setDscrpconPrdcto(rs.getString(DSCRPCON_PRDCTO));
					resultado.setCntddStck(rs.getInt(CNTDD_STCK));
					resultado.setCntddCmpra(rs.getInt(CNTDD_CMPRA));
					resultado.setCntddVnta(rs.getInt(CNTDD_VNTA));
					resultado.setPrcioCmpra(rs.getInt(PRCIO_CMPRA));
					resultado.setPrcioUntro(rs.getInt(PRCIO_UNTRO));
					resultado.setTotalCmpra(rs.getInt(TOTAL_CMPRA));
					resultado.setTotalVnta(rs.getInt(TOTAL_VNTA));
					resultado.setUtldd(rs.getInt(UTLDD));	
					
					lResultado.add(resultado);
				}
			}
		}
	}


	public Integer getCnsctvoPrdcto() {
		return cnsctvoPrdcto;
	}


	public void setCnsctvoPrdcto(Integer cnsctvoPrdcto) {
		this.cnsctvoPrdcto = cnsctvoPrdcto;
	}


	public Date getFchaInco() {
		return fchaInco;
	}


	public void setFchaInco(Date fchaInco) {
		this.fchaInco = fchaInco;
	}


	public Date getFchaFin() {
		return fchaFin;
	}


	public void setFchaFin(Date fchaFin) {
		this.fchaFin = fchaFin;
	}

	public List<ReporteProductosVO> getlResultado() {
		return lResultado;
	}


	public void setlResultado(List<ReporteProductosVO> lResultado) {
		this.lResultado = lResultado;
	}

}
