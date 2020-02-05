package com.todo1.hulkstore.data.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * The Class DetalleCompraProductoVO.
 *
 * @author Cristhian Hernandez QVision
 */
public class DetalleCompraProductoVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1394895895878210869L;
	
	private Integer  cnsctvoPrdcto;
	private Integer  cntdd;
	private Integer  prcoCmpra;
	private Date     fchaCrcn;
	private String   usroCrcn;
	
	public Integer getCnsctvoPrdcto() {
		return cnsctvoPrdcto;
	}
	public void setCnsctvoPrdcto(Integer cnsctvoPrdcto) {
		this.cnsctvoPrdcto = cnsctvoPrdcto;
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
	public Integer getCntdd() {
		return cntdd;
	}
	public void setCntdd(Integer cntdd) {
		this.cntdd = cntdd;
	}
	public Integer getPrcoCmpra() {
		return prcoCmpra;
	}
	public void setPrcoCmpra(Integer prcoCmpra) {
		this.prcoCmpra = prcoCmpra;
	}

}
