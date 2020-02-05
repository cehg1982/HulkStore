package com.todo1.hulkstore.data.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * The Class ProductoVO.
 *
 * @author Cristhian Hernandez QVision
 */
public class ProductoVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1394895895878210869L;
	
	private Integer cnsctvoPrdcto;
	private String  nmbrePrdcto;
	private String  dscrpconPrdcto;
	private Integer cntddStck;
	private String  stado;
	private Date    fchaCrcn;
	private String  usroCrcn;
	private Date    fchaUltma_mdfccn;
	private String  usroUltma_mdfccn;
	
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
	public String getStado() {
		return stado;
	}
	public void setStado(String stado) {
		this.stado = stado;
	}
	public Date getFchaCrcn() {
		return fchaCrcn;
	}
	public void setFchaCrcn(Date fchaCrcn) {
		this.fchaCrcn = fchaCrcn;
	}
	public String getUsroCrcn() {
		return usroCrcn;
	}
	public void setUsroCrcn(String usroCrcn) {
		this.usroCrcn = usroCrcn;
	}
	public Date getFchaUltma_mdfccn() {
		return fchaUltma_mdfccn;
	}
	public void setFchaUltma_mdfccn(Date fchaUltma_mdfccn) {
		this.fchaUltma_mdfccn = fchaUltma_mdfccn;
	}
	public String getUsroUltma_mdfccn() {
		return usroUltma_mdfccn;
	}
	public void setUsroUltma_mdfccn(String usroUltma_mdfccn) {
		this.usroUltma_mdfccn = usroUltma_mdfccn;
	}

}
