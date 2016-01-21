package br.com.easyrouter.engine.api;

import java.util.Date;

public class Person {

	private String firstName;
	private String surName;
	private Date birthdate;
	private ExternalCode externalCode;
	
	/**
	 * @return the firstName
	 */
	String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the surName
	 */
	String getSurName() {
		return surName;
	}
	/**
	 * @param surName the surName to set
	 */
	void setSurName(String surName) {
		this.surName = surName;
	}
	/**
	 * @return the birthdate
	 */
	Date getBirthdate() {
		return birthdate;
	}
	/**
	 * @param birthdate the birthdate to set
	 */
	void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	/**
	 * @return the externalCode
	 */
	ExternalCode getExternalCode() {
		return externalCode;
	}
	/**
	 * @param externalCode the externalCode to set
	 */
	void setExternalCode(ExternalCode externalCode) {
		this.externalCode = externalCode;
	}
	
}
