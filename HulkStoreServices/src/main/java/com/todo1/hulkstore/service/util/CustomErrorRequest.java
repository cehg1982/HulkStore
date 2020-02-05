package com.todo1.hulkstore.service.util;

import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

/**
 * The Class CustomErrorRequest.
 *
 */
public class CustomErrorRequest implements StatusType{
	
	/** The status code. */
	private int statusCode;
	
	/** The reason phrase. */
	private String reasonPhrase;
	
	/**
	 * Instantiates a new custom error request.
	 *
	 * @param statusCode the status code
	 * @param reasonPhrase the reason phrase
	 */
	public CustomErrorRequest(int statusCode, String reasonPhrase) {
		super();
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.core.Response.StatusType#getStatusCode()
	 */
	@Override
	public int getStatusCode() {
		return statusCode;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.core.Response.StatusType#getFamily()
	 */
	@Override
	public Family getFamily() {
		return Family.CLIENT_ERROR;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.core.Response.StatusType#getReasonPhrase()
	 */
	@Override
	public String getReasonPhrase() {
		return reasonPhrase;
	}

}
