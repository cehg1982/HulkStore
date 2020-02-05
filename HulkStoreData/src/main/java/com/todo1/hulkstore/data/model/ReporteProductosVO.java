package com.todo1.hulkstore.data.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * The Class DiagnosticoVO.
 *
 * @author Cristhian Hernandez QVision
 */
public class ReporteProductosVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1394895895878210869L;
	
	private Integer cnsctvoPrdcto;
	private String  nmbrePrdcto;
	private String  dscrpconPrdcto;
	private Integer cntddStck;
	private Integer cntddCmpra;
	private Integer cntddVnta;
	private Integer prcioCmpra;
	private Integer prcioUntro;
	private Integer totalCmpra;
	private Integer totalVnta;
	private Integer utldd;
	
	public Integer getCnsctvoPrdcto() {
		return cnsctvoPrdcto;
	}
	public void setCnsctvoPrdcto(Integer cnsctvoPrdcto) {
		this.cnsctvoPrdcto = cnsctvoPrdcto;
	}
	public String getNmbrePrdcto() {
		return nmbrePrdcto;
	}
	public void setNmbrePrdcto(String nmbrePrdcto) {
		this.nmbrePrdcto = nmbrePrdcto;
	}
	public String getDscrpconPrdcto() {
		return dscrpconPrdcto;
	}
	public void setDscrpconPrdcto(String dscrpconPrdcto) {
		this.dscrpconPrdcto = dscrpconPrdcto;
	}
	public Integer getCntddStck() {
		return cntddStck;
	}
	public void setCntddStck(Integer cntddStck) {
		this.cntddStck = cntddStck;
	}
	public Integer getCntddCmpra() {
		return cntddCmpra;
	}
	public void setCntddCmpra(Integer cntddCmpra) {
		this.cntddCmpra = cntddCmpra;
	}
	public Integer getCntddVnta() {
		return cntddVnta;
	}
	public void setCntddVnta(Integer cntddVnta) {
		this.cntddVnta = cntddVnta;
	}
	public Integer getPrcioCmpra() {
		return prcioCmpra;
	}
	public void setPrcioCmpra(Integer prcioCmpra) {
		this.prcioCmpra = prcioCmpra;
	}
	public Integer getPrcioUntro() {
		return prcioUntro;
	}
	public void setPrcioUntro(Integer prcioUntro) {
		this.prcioUntro = prcioUntro;
	}
	public Integer getTotalCmpra() {
		return totalCmpra;
	}
	public void setTotalCmpra(Integer totalCmpra) {
		this.totalCmpra = totalCmpra;
	}
	public Integer getTotalVnta() {
		return totalVnta;
	}
	public void setTotalVnta(Integer totalVnta) {
		this.totalVnta = totalVnta;
	}
	public Integer getUtldd() {
		return utldd;
	}
	public void setUtldd(Integer utldd) {
		this.utldd = utldd;
	}

}
