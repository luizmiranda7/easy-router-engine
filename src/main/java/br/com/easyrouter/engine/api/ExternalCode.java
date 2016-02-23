package br.com.easyrouter.engine.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExternalCode {
	
	private static final Gson gson = new GsonBuilder().create();

	private String origin;
	private String externalCode;
	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * @return the externalCode
	 */
	public String getExternalCode() {
		return externalCode;
	}
	/**
	 * @param externalCode the externalCode to set
	 */
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return gson.toJson(this);
	}
	
	
	
}
