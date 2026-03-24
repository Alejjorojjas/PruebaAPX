package com.bbva.prue;

import com.bbva.elara.transaction.AbstractTransaction;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractPRUETTR101COTransaction extends AbstractTransaction {

	public AbstractPRUETTR101COTransaction(){
	}


	/**
	 * Return value for input parameter identityDocumentTypeId
	 */
	protected String getIdentitydocumenttypeid(){
		return (String)this.getParameter("identityDocumentTypeId");
	}

	/**
	 * Return value for input parameter identityDocumentNumber
	 */
	protected String getIdentitydocumentnumber(){
		return (String)this.getParameter("identityDocumentNumber");
	}

	/**
	 * Set value for String output parameter firstName
	 */
	protected void setFirstname(final String field){
		this.addParameter("firstName", field);
	}

	/**
	 * Set value for String output parameter lastName
	 */
	protected void setLastname(final String field){
		this.addParameter("lastName", field);
	}

	/**
	 * Set value for String output parameter customerId
	 */
	protected void setCustomerid(final String field){
		this.addParameter("customerId", field);
	}

	/**
	 * Set value for String output parameter nationalityId
	 */
	protected void setNationalityid(final String field){
		this.addParameter("nationalityId", field);
	}

	/**
	 * Set value for String output parameter genderId
	 */
	protected void setGenderid(final String field){
		this.addParameter("genderId", field);
	}

	/**
	 * Set value for String output parameter identityDocument
	 */
	protected void setIdentitydocument(final String field){
		this.addParameter("identityDocument", field);
	}

	/**
	 * Set value for String output parameter identityDocumentNumber
	 */
	protected void setIdentitydocumentnumber(final String field){
		this.addParameter("identityDocumentNumber", field);
	}
}
